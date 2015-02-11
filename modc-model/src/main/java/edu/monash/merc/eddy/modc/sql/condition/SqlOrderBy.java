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

package edu.monash.merc.eddy.modc.sql.condition;

import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simonyu on 27/08/2014.
 */
public class SqlOrderBy {

    private Map<String, Object> orderByMaps = new LinkedHashMap<>();

    public SqlOrderBy() {

    }

    public SqlOrderBy asc(String fieldName) {
        orderByMaps.put(fieldName, OrderByType.ASC);
        return this;
    }

    public SqlOrderBy desc(String fieldName) {
        orderByMaps.put(fieldName, OrderByType.DESC);
        return this;
    }


    public Order[] orders() {
        List<Order> orders = new ArrayList<Order>();

        for (Map.Entry<String, Object> entry : orderByMaps.entrySet()) {
            String fieldName = entry.getKey();
            OrderByType orderByType = (OrderByType) entry.getValue();
            Order order = null;
            if (OrderByType.ASC == orderByType) {
                order = Order.asc(fieldName);
            } else if (OrderByType.DESC == orderByType) {
                order = Order.desc(fieldName);
            }
            if (order != null) {
                orders.add(order);
            }
        }
        return orders.toArray(new Order[orders.size()]);
    }

    public static void main(String[] args) {
        SqlOrderBy allOrderBys = new SqlOrderBy();
        allOrderBys.asc("test1").desc("test1").desc("ok1").asc("test2").desc("ok2").asc("ok1");

        Order[] orders = allOrderBys.orders();

        for (Order order : orders) {
            System.out.println("===== order : " + order);

        }
    }

}
