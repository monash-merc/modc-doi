/*
 * Copyright (c) 2010-2011, Monash e-Research Centre
 * (Monash University, Australia)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 	* Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 	* Redistributions in binary form must reproduce the above copyright
 * 	  notice, this list of conditions and the following disclaimer in the
 * 	  documentation and/or other materials provided with the distribution.
 * 	* Neither the name of the Monash University nor the names of its
 * 	  contributors may be used to endorse or promote products derived from
 * 	  this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package edu.monash.merc.eddy.modc.repository;

import edu.monash.merc.eddy.modc.sql.page.Pager;

import java.util.List;
import java.util.Map;

/**
 * Interface IRepository - Generic class for persisting a Domain entity object.
 *
 * @param <T> a parametrised type of a domain entity object which will be replaced by the actual type argument.
 */
public interface IRepository<T> {

    T get(long id);

    void add(T entity);

    void remove(T entity);

    void delete(long id);

    void update(T entity);

    /**
     * generic method - merge a domain entity in the database
     *
     * @param entity a parameterized type of a domain entity object which will be replaced by the actual type argument.
     */
    void merge(T entity);

    int saveAll(List<T> entities);

    /**
     * Find pageable entities based on a HQL condition, the HQL string must be in named parameters style.
     *
     * @param hql         Hibernate HQL string without order by part.
     * @param namedParams Any Named parameters
     * @return A paged Entities
     */
    Pager<T> find(String hql, Map<String, Object> namedParams, int startPageNo, int sizePerPage);

    /**
     * Find all entities based on HQL condition, the HQL string must be in named parameters style.
     *
     * @param hql         A named parameters style HQL
     * @param namedParams a list of named parameters
     * @return a list of entities
     */
    List<T> list(String hql, Map<String, Object> namedParams);

    /**
     * Find an entity based on HQL sql string, the HQL string must be in a named parameters sytle.
     *
     * @param hql         A named parameters style HQL
     * @param namedParams
     * @return
     */
    T find(String hql, Map<String, Object> namedParams);

    /**
     * Check an entity existed or not.
     *
     * @param hql         A named parameters style count HQL
     * @param namedParams a list of named parameters
     * @return true if exists.
     */
    boolean checkEntityExisted(String hql, Map<String, Object> namedParams);
}
