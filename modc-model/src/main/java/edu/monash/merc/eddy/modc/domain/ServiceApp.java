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

package edu.monash.merc.eddy.modc.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 29/10/14
 */
@Entity
@Table(name = "service", indexes = {@Index(name = "idx_sa_unique_id", columnList = "unique_id"), @Index(name = "idx_name", columnList = "name"),
        @Index(name = "idx_auth_code", columnList = "auth_code"), @Index(name = "idx_path", columnList = "path"),
        @Index(name = "idx_description", columnList = "description"), @Index(name = "idx_service_type", columnList = "service_type")})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "freqRegion")
public class ServiceApp extends Domain {
    @Id
    @GeneratedValue(generator = "pk_generator")
    @GenericGenerator(name = "pk_generator", strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "table_name", value = "pk_gen_tab"),
                    @org.hibernate.annotations.Parameter(name = "value_column_name ", value = "pk_next_val"),
                    @org.hibernate.annotations.Parameter(name = "segment_column_name", value = "pk_name"),
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "service_id"),
                    @org.hibernate.annotations.Parameter(name = "increment_size  ", value = "5"),
                    @org.hibernate.annotations.Parameter(name = "optimizer ", value = "hilo")
            })
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "unique_id", length = 200)
    private String uniqueId;

    @Basic
    @Column(name = "auth_code")
    private String authCode;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "path")
    private String path;

    @Basic
    @Column(name = "description")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Basic
    @Column(name = "created_date")
    private Date createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Basic
    @Column(name = "last_modified")
    private Date lastModified;

    @Basic
    @Column(name = "service_type")
    private String serviceType;

    @Basic
    @Column(name = "auto_publish")
    private boolean autoPublish;

    @OneToMany(mappedBy = "serviceApp", targetEntity = MCollection.class, fetch = FetchType.LAZY)
    @Cascade({CascadeType.DELETE})
    private List<MCollection> collections;

    @ManyToOne(targetEntity = AuthorizedApp.class)
    @JoinColumn(name = "app_id", referencedColumnName = "id", nullable = false)
    private AuthorizedApp authorizedApp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public boolean isAutoPublish() {
        return autoPublish;
    }

    public void setAutoPublish(boolean autoPublish) {
        this.autoPublish = autoPublish;
    }

    public List<MCollection> getCollections() {
        return collections;
    }

    public void setCollections(List<MCollection> collections) {
        this.collections = collections;
    }

    public AuthorizedApp getAuthorizedApp() {
        return authorizedApp;
    }

    public void setAuthorizedApp(AuthorizedApp authorizedApp) {
        this.authorizedApp = authorizedApp;
    }
}
