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

import edu.monash.merc.eddy.modc.domain.MCollection;
import edu.monash.merc.eddy.modc.domain.MKeyword;
import edu.monash.merc.eddy.modc.support.QueryHelper;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 2/09/2014
 */

@Scope("prototype")
@Repository
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "freqRegion")
public class SearchDAO extends HibernateGenericDAO<MCollection> {

    @Autowired
    private MKeywordDAO keywordDao;

    public void setKeywordDao(MKeywordDAO keywordDao) {
        this.keywordDao = keywordDao;
    }

    public List<MCollection> listCollectionsByKeyword(String keyword) {
        List<MKeyword> foundKeywords = this.keywordDao.listKeywordsLikeSearchName(keyword);
        String hql = "SELECT DISTINCT c FROM " + this.persistClass.getSimpleName() + " AS c INNER JOIN c.keywords AS k";
        //found key words, then we search any collections with keyword and also search any collections' names contain keyword
        if (foundKeywords != null && foundKeywords.size() > 0) {
            hql += " WHERE k in (:keywords) or lower(c.name) like '%" + StringUtils.lowerCase(keyword) + "%'";
            Map<String, Object> namedParam = QueryHelper.createNamedParam("keywords", foundKeywords);
            return this.list(hql, namedParam);
        } else {
            //here we only search collections which contain the keyword.
            hql += " WHERE lower(c.name) like '%" + StringUtils.lowerCase(keyword) + "%'";
            return this.list(hql, null);
        }
    }
}
