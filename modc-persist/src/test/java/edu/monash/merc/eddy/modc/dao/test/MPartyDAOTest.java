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
import edu.monash.merc.eddy.modc.dao.MPartyDAO;
import edu.monash.merc.eddy.modc.domain.MGroup;
import edu.monash.merc.eddy.modc.domain.MParty;
import edu.monash.merc.eddy.modc.domain.MPerson;
import org.apache.commons.lang.StringUtils;
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
public class MPartyDAOTest {
    @Autowired
    private MPartyDAO partyDAO;

    @Test
    @DatabaseSetup(value = "test-party-person.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-party-person.xml", type = DatabaseOperation.DELETE_ALL)
    public void testGetPartyByEmail() {
        MParty party = this.partyDAO.getPartyByEmail("test2.dev2@gmail.com");
        String type = party.getType();
        assertNotNull(party);
        assertEquals("person", type);

        if (StringUtils.equals("person", type)) {
            String partyEmail = ((MPerson) party).getEmail();
            assertEquals("test2.dev2@gmail.com", partyEmail);
        }
    }

    @Test
    @DatabaseSetup(value = "test-party-person.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-party-person.xml", type = DatabaseOperation.DELETE_ALL)
    public void testGetPartyByRefKey() {
        MParty party = this.partyDAO.getPartyByRefKey("10");
        assertEquals("10", party.getRefKey());
    }

    @Test
    @DatabaseSetup(value = "test-party-person.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-party-person.xml", type = DatabaseOperation.DELETE_ALL)
    public void testGetPartyByIdentifier() {
        MParty party = this.partyDAO.getPartyByIdentifier("MON23000010");
        assertEquals("MON23000010", party.getIdentifier());
    }

    @Test
    @DatabaseSetup(value = "test-party-person.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-party-person.xml", type = DatabaseOperation.DELETE_ALL)
    public void testListPartiesByUserName() {
        List<MParty> parties = this.partyDAO.listPartiesByUserName("test1", "dev1");
        assertEquals(2, parties.size());
    }

    @Test
    @DatabaseSetup(value = "test-party-group.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-party-group.xml", type = DatabaseOperation.DELETE_ALL)
    public void testGetPartyByName() {
        MParty party = this.partyDAO.getPartyByName("groupParty1");
        assertNotNull(party);
        String type = party.getType();
        assertEquals("group", type);
        if (StringUtils.equals(type, "group")) {
            String name = ((MGroup) party).getName();
            assertEquals("groupParty1", name);
        }
    }

    @Test
    @DatabaseSetup(value = "test-collection-party.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "test-collection-party.xml", type = DatabaseOperation.DELETE_ALL)
    public void testListPartiesByCollection() {
        List<MParty> parties = this.partyDAO.listPartiesByCollection(1);
        assertNotNull(parties);
        assertEquals(2, parties.size());
    }
}
