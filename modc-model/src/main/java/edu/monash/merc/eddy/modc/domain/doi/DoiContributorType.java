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

package edu.monash.merc.eddy.modc.domain.doi;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 16/09/2014
 */
public enum DoiContributorType {
    CONTACT_PERSON("ContactPerson"),
    DATA_COLLECTOR("DataCollector"),
    DATA_MANAGER("DataManager"),
    DISTRIBUTOR("Distributor"),
    EDITOR("Editor"),
    FUNDER("Funder"),
    HOSTING_INSTITUTION("HostingInstitution"),
    PRODUCER("Producer"),
    PROJECT_LEADER("ProjectLeader"),
    PROJECT_MEMBER("ProjectMember"),
    REGISTRATION_AGENCY("RegistrationAgency"),
    REGISTRATION_AUTHORITY("RegistrationAuthority"),
    RELATED_PERSON("RelatedPerson"),
    RIGHTS_HOLDER("RightsHolder"),
    RESEARCHER("Researcher"),
    SPONSOR("Sponsor"),
    SUPERVISOR("Supervisor"),
    WORK_PACKAGE_LEADER("WorkPackageLeader");

    private final String value;

    DoiContributorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DoiContributorType fromValue(String v) {
        for (DoiContributorType c : DoiContributorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        //defalut
        return RELATED_PERSON;
    }
}
