package edu.monash.merc.eddy.modc.dao;

import edu.monash.merc.eddy.modc.common.exceptions.MInvalidSQLException;
import edu.monash.merc.eddy.modc.repository.IRepository;
import edu.monash.merc.eddy.modc.sql.page.Pager;
import edu.monash.merc.eddy.modc.support.QueryHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by simonyu on 4/08/2014.
 */
@Scope("prototype")
@Repository
public class HibernateGenericDAO<T> implements IRepository<T> {

    static int DEFAULT_START_PAGE_NO = 1;

    static int DEFAULT_SIZE_PER_PAGE = 20;

    protected Class<T> persistClass;

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public HibernateGenericDAO() {
        //get first actual type arguments -- T an entity object class.
        this.persistClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public HibernateGenericDAO(SessionFactory sessionFactory) {
        //call default constructor.
        this();
        this.sessionFactory = sessionFactory;
    }

    public Session session() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(long id) {
        return (T) this.session().get(this.persistClass, id);
    }

    @Override
    public void add(T entity) {
        this.session().save(entity);
    }

    @Override
    public void remove(T entity) {
        this.session().delete(entity);
    }

    @Override
    public void delete(long id) {
        //get an entity object first, the call the session to cascaded delete the object
        String queryStr = "FROM " + this.persistClass.getSimpleName() + " AS ent WHERE ent.id = :id";
        Query query = this.session().createQuery(queryStr);

        query.setParameter("id", id);
        Object foundObj = query.uniqueResult();
        this.session().delete(foundObj);
    }

    @Override
    public void update(T entity) {
        this.session().update(entity);
    }

    @Override
    public void merge(T entity) {
        this.session().merge(entity);
    }

    @Override
    public int saveAll(List<T> entities) {
        int insertedCount = 0;
        for (int i = 0; i < entities.size(); i++) {
            add(entities.get(i));
            if (++insertedCount % 20 == 0) {
                flushAndClear();
            }
        }
        flushAndClear();
        return insertedCount;
    }

    protected void flushAndClear() {
        if (this.session().isDirty()) {
            this.session().flush();
            this.session().clear();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pager<T> find(String hql, Map<String, Object> namedParams, int startPageNo, int sizePerPage) {

        //create a find query
        Query findQuery = createQuery(hql);
        //create a count query
        Query countQuery = createCountQuery(hql);

        //set parameters for find query
        QueryHelper.setNamedParams(findQuery, namedParams);

        //set parameters for count query
        QueryHelper.setNamedParams(countQuery, namedParams);

        int totalSize = ((Long) countQuery.uniqueResult()).intValue();
        Pager<T> pagination = initPagination(startPageNo, sizePerPage, totalSize);

        //if found result size is 0 or less 0, just return an empty pagination object
        if (totalSize <= 0) {
            return pagination;
        }

        findQuery.setFirstResult(pagination.getFirstResult());
        findQuery.setMaxResults(pagination.getSizePerPage());
        List results = findQuery.list();
        pagination.setPageResults(results);
        return pagination;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> list(String hql, Map<String, Object> namedParams) {
        Query query = createQuery(hql, true);
        QueryHelper.setNamedParams(query, namedParams);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T find(String hql, Map<String, Object> namedParams) {
        Query query = createQuery(hql, true);
        QueryHelper.setNamedParams(query, namedParams);
        return (T) query.uniqueResult();
    }

    @Override
    public boolean checkEntityExisted(String countHql, Map<String, Object> namedParams) {
        if (QueryHelper.isCountHQL(countHql)) {
            Query query = createQuery(countHql, true);
            QueryHelper.setNamedParams(query, namedParams);
            long num = (Long) query.uniqueResult();
            return num == 1;
        } else {
            throw new MInvalidSQLException("invalid counting hql");
        }
    }


    /**
     * create a Query based on hql string
     *
     * @param hql a HQL query string
     * @return a Query object
     */
    protected Query createQuery(String hql, boolean cacheable) {
        Query query = createQuery(hql);
        if (cacheable) {
            query.setCacheable(cacheable);
        }
        return query;
    }

    protected Query createQuery(String hql) {
        return this.session().createQuery(hql);
    }

    /**
     * create a count query based on hql string
     *
     * @param hql a HQL query string
     * @return a Query object for counting
     */
    private Query createCountQuery(String hql) {
        String nonOderByHqlStr = QueryHelper.removeOrderByString(hql);
        String countHqlStr = "select count (*) " + QueryHelper.removeSelectFromStr(nonOderByHqlStr);
        //remove the from
        return this.session().createQuery(countHqlStr);
    }

    /**
     * init a pagination
     *
     * @param startPageNo start page number
     * @param sizePerPage the size of a page
     * @param totalSize   a total of size records in db
     * @return a pagination Pager
     */
    private Pager<T> initPagination(int startPageNo, int sizePerPage, int totalSize) {
        //make sure the start page number is great than 1 if start page no. is zero or less zero
        if (startPageNo <= 0) {
            startPageNo = DEFAULT_START_PAGE_NO;
        }
        //make sure the size per page to set 20 as default if sizePerPage is zero or less zero
        if (sizePerPage <= 0) {
            sizePerPage = DEFAULT_SIZE_PER_PAGE;
        }
        Pager<T> pagination = new Pager<>(startPageNo, sizePerPage, totalSize);
        List<T> results = new ArrayList<>();
        pagination.setPageResults(results);
        return pagination;
    }
}
