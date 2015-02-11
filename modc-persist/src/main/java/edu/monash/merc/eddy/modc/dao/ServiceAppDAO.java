/*
 * Copyright (c) 2014, Monash e-Research Centre
 *  (Monash University, Australia)
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  	* Redistributions of source code must retain the above copyright
 *  	  notice, this list of conditions and the following disclaimer.
 *  	* Redistributions in binary form must reproduce the above copyright
 *  	  notice, this list of conditions and the following disclaimer in the
 *  	  documentation and/or other materials provided with the distribution.
 *  	* Neither the name of the Monash University nor the names of its
 *  	  contributors may be used to endorse or promote products derived from
 *  	  this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.monash.merc.eddy.modc.dao;

import edu.monash.merc.eddy.modc.domain.ServiceApp;
import edu.monash.merc.eddy.modc.repository.ServiceAppRepository;
import edu.monash.merc.eddy.modc.sql.page.Pager;
import edu.monash.merc.eddy.modc.support.QueryHelper;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 31/10/14
 */
@Scope("prototype")
@Repository
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "fixedRegion")
public class ServiceAppDAO extends HibernateGenericDAO<ServiceApp> implements ServiceAppRepository {


    @Override
    public ServiceApp getServiceAppByUniqueId(String uniqueId) {
        String hql = "FROM " + this.persistClass.getSimpleName() + " AS sa WHERE sa.uniqueId =:uniqueId";
        Map<String, Object> namedParams = QueryHelper.createNamedParam("uniqueId", uniqueId);
        return this.find(hql, namedParams);
    }

    @Override
    public ServiceApp getServiceAppByName(String name) {
        String hql = "FROM " + this.persistClass.getSimpleName() + " AS sa WHERE lower(sa.name) =:name";
        Map<String, Object> namedParams = QueryHelper.createNamedParam("name", StringUtils.lowerCase(name));
        return this.find(hql, namedParams);
    }

    @Override
    public List<ServiceApp> listServiceApps(String serviceType, Order[] orderParams) {
        String hql = "FROM " + this.persistClass.getSimpleName() + " AS sa WHERE sa.serviceType = :serviceType";
        Map<String, Object> namedParam = QueryHelper.createNamedParam("serviceType", serviceType);
        QueryHelper.setOrderByParams(hql, "sa", orderParams);
        return this.list(hql, namedParam);
    }

    @Override
    public Pager<ServiceApp> getPagedServiceApps(String serviceType, int startPageNo, int sizePerPage, Order[] orderParams) {
        Map<String, Object> namedParam = null;
        String hql = "FROM " + this.persistClass.getSimpleName() + " AS sa WHERE sa.serviceType = :serviceType";
        if(StringUtils.isBlank(serviceType)){
            hql = "FROM " + this.persistClass.getSimpleName() + " AS sa";
        }else{
            namedParam = QueryHelper.createNamedParam("serviceType", serviceType);
        }
        QueryHelper.setOrderByParams(hql, "sa", orderParams);
        return this.find(hql, namedParam, startPageNo, sizePerPage);
    }
}
