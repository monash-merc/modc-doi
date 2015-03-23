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
import edu.monash.merc.eddy.modc.dao.AuthorizedAppDAO;
import edu.monash.merc.eddy.modc.dao.ServiceAppDAO;
import edu.monash.merc.eddy.modc.domain.AuthorizedApp;
import edu.monash.merc.eddy.modc.domain.ServiceApp;
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

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by simonyu on 26/08/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/test-dao.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@Transactional
public class AuthorizedAppDAOTest {

    @Autowired
    public AuthorizedAppDAO authorizedAppDAO;

    public void setAuthorizedAppDAO(AuthorizedAppDAO authorizedAppDAO) {
        this.authorizedAppDAO = authorizedAppDAO;
    }

    @Test
    @DatabaseSetup(value = "test-service.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-service.xml", type = DatabaseOperation.DELETE_ALL)
    public void testGetAuthorizedAppById() {
        AuthorizedApp authorizedApp = this.authorizedAppDAO.get(1);
        assertEquals("4fb08353943adf1f733c528c14293db711a5b03d", authorizedApp.getAppId());
    }

    @Test
    @DatabaseSetup(value = "test-service.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-service.xml", type = DatabaseOperation.DELETE_ALL)
    public void testListAuthorizedApps() {
        List<AuthorizedApp> authorizedApps = this.authorizedAppDAO.listAuthorizedApps();
        assertEquals(2, authorizedApps.size());
    }
}
