package edu.monash.merc.eddy.modc.dao;

import edu.monash.merc.eddy.modc.domain.User;
import edu.monash.merc.eddy.modc.repository.UserRepository;
import edu.monash.merc.eddy.modc.sql.page.Pager;
import edu.monash.merc.eddy.modc.support.QueryHelper;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by simonyu on 8/08/2014.
 */
@Scope("prototype")
@Repository
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "freqRegion")
public class UserDAO extends HibernateGenericDAO<User> implements UserRepository {

    @Override
    public User getUserByEmail(String email) {
        String hql = "FROM " + this.persistClass.getSimpleName() + " AS u WHERE lower(u.email) = :email";
        Map<String, Object> namedParam = QueryHelper.createNamedParam("email", StringUtils.lowerCase(email));
        return this.find(hql, namedParam);
    }

    @Override
    public User getUserByUniqueId(String uniqueId) {
        String hql = "FROM " + this.persistClass.getSimpleName() + " AS u WHERE  u.uniqueId = :uniqueId";
        Map<String, Object> namedParam = QueryHelper.createNamedParam("uniqueId", uniqueId);
        return this.find(hql, namedParam);
    }

    @Override
    public boolean checkExistedName(String displayName) {
        String countHQL = "SELECT COUNT(*) FROM " + this.persistClass.getSimpleName() + " AS u WHERE lower(u.displayName) = :displayName";
        Map<String, Object> namedParam = QueryHelper.createNamedParam("displayName", StringUtils.lowerCase(displayName));
        return this.checkEntityExisted(countHQL, namedParam);
    }

    @Override
    public boolean checkExistedUniqueId(String uniqueId) {
        String countHQL = "SELECT COUNT(*) FROM " + this.persistClass.getSimpleName() + " AS u WHERE u.uniqueId = :uniqueId";
        Map<String, Object> namedParam = QueryHelper.createNamedParam("uniqueId", uniqueId);
        return this.checkEntityExisted(countHQL, namedParam);
    }

    @Override
    public boolean checkExistedEmail(String email) {
        String countHQL = "SELECT COUNT(*) FROM " + this.persistClass.getSimpleName() + " AS u WHERE lower(u.email) = :email";
        Map<String, Object> namedParam = QueryHelper.createNamedParam("email", StringUtils.lowerCase(email));
        return this.checkEntityExisted(countHQL, namedParam);
    }

    @Override
    public User checkUserLogin(String uniqueId, String password) {
        String hql = "FROM " + this.persistClass.getSimpleName() + " AS u WHERE  u.uniqueId = :uniqueId AND u.password = :password";
        Map<String, Object> namedParam = QueryHelper.createNamedParam("uniqueId", uniqueId);
        namedParam = QueryHelper.addNamedParam(namedParam, "password", password);
        return this.find(hql, namedParam);
    }

    @Override
    public Pager<User> getUsers(int startPageNo, int sizePerPage, Order[] orderParams) {
        String hql = "FROM " + this.persistClass.getSimpleName();
        hql = QueryHelper.setOrderByParams(hql, null, orderParams);
        return this.find(hql, null, startPageNo, sizePerPage);
    }
}
