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

package edu.monash.merc.eddy.modc.service.impl;

import edu.monash.merc.eddy.modc.dao.MPartyDAO;
import edu.monash.merc.eddy.modc.domain.MParty;
import edu.monash.merc.eddy.modc.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 4/09/2014
 */
@Service
@Transactional
public class PartyServiceImpl implements PartyService {

    @Autowired
    private MPartyDAO partyDao;

    public void setPartyDao(MPartyDAO partyDao) {
        this.partyDao = partyDao;
    }

    @Override
    public void saveParty(MParty party) {
        this.partyDao.add(party);
    }

    @Override
    public MParty getPartyById(long id) {
        return this.partyDao.get(id);
    }

    @Override
    public void updateParty(MParty party) {
        this.partyDao.update(party);
    }

    @Override
    public void deleteParty(MParty party) {
        this.partyDao.remove(party);
    }

    @Override
    public void deletePartyById(long partyId) {
        this.partyDao.delete(partyId);
    }

    @Override
    public MParty getPartyByRefKey(String refKey) {
        return this.partyDao.getPartyByRefKey(refKey);
    }

    @Override
    public MParty getPartyByIdentifier(String identifier) {
        return this.partyDao.getPartyByIdentifier(identifier);
    }

    @Override
    public MParty getPartyByEmail(String email) {
        return this.partyDao.getPartyByEmail(email);
    }

    @Override
    public MParty getPartyByName(String name) {
        return this.partyDao.getPartyByName(name);
    }

    @Override
    public List<MParty> listPartiesByUserName(String firstName, String lastName) {
        return this.partyDao.listPartiesByUserName(firstName, lastName);
    }

    @Override
    public List<MParty> listPartiesByCollection(long collectionId) {
        return this.partyDao.listPartiesByCollection(collectionId);
    }
}
