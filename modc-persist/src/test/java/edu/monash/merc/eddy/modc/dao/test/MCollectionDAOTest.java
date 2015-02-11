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

package edu.monash.merc.eddy.modc.dao.test;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import edu.monash.merc.eddy.modc.dao.MCollectionDAO;
import edu.monash.merc.eddy.modc.domain.MCollection;
import edu.monash.merc.eddy.modc.sql.condition.SqlOrderBy;
import edu.monash.merc.eddy.modc.sql.page.Pager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by simonyu on 1/09/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/test-dao.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@Transactional
public class MCollectionDAOTest {

    @Autowired
    private MCollectionDAO mCollectionDAO;


    @Test
    @DatabaseSetup(value = "test-collection.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-collection.xml", type = DatabaseOperation.DELETE_ALL)
    public void testGetCollectionByRefKeyAndProject() {
        MCollection collection = this.mCollectionDAO.getCollectionByRefKeyAndServiceAppId("101", 1);
        assertNotNull(collection);
        assertEquals("101", collection.getRefKey());
        assertEquals(1, collection.getServiceApp().getId());
    }

    @Test
    @DatabaseSetup(value = "test-collection.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-collection.xml", type = DatabaseOperation.DELETE_ALL)
    public void testGetCollectionByNameAndProject() {
        MCollection collection = this.mCollectionDAO.getCollectionByNameAndServiceAppId("test-collection3", 16);
        assertNotNull(collection);
        assertEquals("test-collection3", collection.getName());
        assertEquals(16, collection.getServiceApp().getId());
    }

    @Test
    @DatabaseSetup(value = "test-collection.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-collection.xml", type = DatabaseOperation.DELETE_ALL)
    public void testListCollectionsByProject() {
        SqlOrderBy myOrders = new SqlOrderBy().desc("name");
        List<MCollection> collections = this.mCollectionDAO.listCollectionsByServiceApp(16, myOrders.orders());
        assertEquals(2, collections.size());
        assertEquals("test-collection3", collections.get(0).getName());
    }

    @Test
    @DatabaseSetup(value = "test-collection.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-collection.xml", type = DatabaseOperation.DELETE_ALL)
    public void testGetCollectionsByProject() {
        SqlOrderBy myOrders = new SqlOrderBy().desc("name");
        Pager<MCollection> pagedCollections = this.mCollectionDAO.getCollectionsByServiceApp(1, 0, 9, myOrders.orders());
        assertEquals(8, pagedCollections.getPageResults().size());
        assertEquals("test-collection1", pagedCollections.getPageResults().get(0).getName());
    }

    @Test
    @DatabaseSetup(value = "test-collection-party.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-collection-party.xml", type = DatabaseOperation.DELETE_ALL)
    public void testListCollectionsByParty() {
        List<MCollection> collections = this.mCollectionDAO.listCollectionByParty(1);
        System.out.println("==========> found collection size : " + collections.size());
        for (MCollection collection : collections) {
            System.out.println("==== collection name : " + collection.getName());
        }
        assertEquals(4, collections.size());
    }

    @Test
    @DatabaseSetup(value = "test-collection-party.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-collection-party.xml", type = DatabaseOperation.DELETE_ALL)
    public void testGetCollectionsByParty() {
        SqlOrderBy myOrders = new SqlOrderBy().desc("name");
        Pager<MCollection> pagedCollections = this.mCollectionDAO.getCollectionsByParty(1, 0, 3, myOrders.orders());
        assertEquals(3, pagedCollections.getPageResults().size());

        for (MCollection collection : pagedCollections.getPageResults()) {
            System.out.println("==== paged collection name : " + collection.getName());
        }
    }
}
