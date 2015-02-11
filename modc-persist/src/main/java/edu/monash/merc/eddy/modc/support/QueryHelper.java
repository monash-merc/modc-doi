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

package edu.monash.merc.eddy.modc.support;

import org.hibernate.Query;
import org.hibernate.criterion.Order;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by simonyu on 29/08/2014.
 */
public abstract class QueryHelper {

    static final String COMMA_SPACE_DELIM = ", ";

    //set the order by parameters
    public static String setOrderByParams(String hql, String aliasName, Order[] ordersParams) {
        if (ordersParams != null && ordersParams.length > 0) {
            hql += " ORDER BY ";
            int i = 0;
            for (Order order : ordersParams) {
                String fieldName = (aliasName != null ? aliasName + "." + order.getPropertyName() : order.getPropertyName());

                if (order.isAscending()) {
                    fieldName += " ASC";
                } else {
                    fieldName += " DESC";
                }
                if (i < ordersParams.length - 1) {
                    fieldName += COMMA_SPACE_DELIM;
                }
                i++;
                hql += fieldName;
            }
        }
        return hql;
    }

    //set the named parameters
    public static void setNamedParams(Query query, Map<String, Object> namedParams) {
        if (namedParams != null) {
            Set<String> keys = namedParams.keySet();
            for (String key : keys) {
                Object namedParam = namedParams.get(key);
                if (namedParam instanceof Collection) {
                    //if named parameters is a list
                    query.setParameterList(key, (Collection) namedParam);
                } else {
                    query.setParameter(key, namedParam);
                }
            }
        }
    }

    //set the positional parameters
    public static void setPositionalParams(Query query, Map<Integer, Object> positionalParams) {
        if (positionalParams != null) {
            Set<Integer> keys = positionalParams.keySet();
            for (Integer key : keys) {
                Object numberedParam = positionalParams.get(key);
                query.setParameter(key.intValue(), numberedParam);
            }
        }
    }

    //remove the 'order by' clause if any.
    public static String removeOrderByString(String hql) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static String removeSelectFromStr(String hql) {
        int beginPos = hql.toLowerCase().indexOf("from");
        return hql.substring(beginPos);
    }

    public static Map<String, Object> createNamedParam(String field, Object paramValue) {
        Map<String, Object> namedParams = new LinkedHashMap<String, Object>();
        namedParams.put(field, paramValue);
        return namedParams;
    }

    public static Map<String, Object> addNamedParam(Map<String, Object> namedParams, String field, Object value) {
        if (namedParams == null) {
            return createNamedParam(field, value);
        } else {
            //just add the named parameter into map, and return it back.
            namedParams.put(field, value);
            return namedParams;
        }
    }

    public static boolean isCountHQL(String countHql) {
        Pattern p = Pattern.compile("select\\s*count\\s*[(]\\s*[*]\\s*[)]\\s+[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(countHql);
        while (m.find()) {
            return true;
        }
        return false;
    }
}
