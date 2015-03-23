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

import edu.monash.merc.eddy.modc.dao.AuthorizedAppDAO;
import edu.monash.merc.eddy.modc.domain.AuthorizedApp;
import edu.monash.merc.eddy.modc.service.AuthorizedAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simonyu on 19/03/15.
 */
@Service
@Transactional
public class AuthorizedAppServiceImpl implements AuthorizedAppService {

    @Autowired
    private AuthorizedAppDAO authorizedAppDao;

    public void setAuthorizedAppDao(AuthorizedAppDAO authorizedAppDao) {
        this.authorizedAppDao = authorizedAppDao;
    }

    @Override
    public AuthorizedApp getAuthorizedAppById(long id) {
        return this.authorizedAppDao.get(id);
    }

    @Override
    public void saveAuthorizedApp(AuthorizedApp authorizedApp) {
        this.authorizedAppDao.add(authorizedApp);
    }

    @Override
    public void updateAuthorizedApp(AuthorizedApp authorizedApp) {
        this.authorizedAppDao.update(authorizedApp);
    }

    @Override
    public void deleteAuthorizedApp(AuthorizedApp authorizedApp) {
        this.authorizedAppDao.remove(authorizedApp);
    }

    @Override
    public void deleteAuthorizedAppById(long authorizedAppId) {
        this.authorizedAppDao.delete(authorizedAppId);
    }

    @Override
    public List<AuthorizedApp> listAuthorizedApps() {
        return this.authorizedAppDao.listAuthorizedApps();
    }

    @Override
    public Map<Long, String> listAuthorizedAppNames() {
        Map<Long, String> authorizedNameMap = new LinkedHashMap<>();
        List<AuthorizedApp> authorizedApps = this.listAuthorizedApps();
        for (AuthorizedApp authorizedApp : authorizedApps) {
            long id = authorizedApp.getId();
            String appName = authorizedApp.getAppName();
            authorizedNameMap.put(id, appName);
        }
        return authorizedNameMap;
    }
}
