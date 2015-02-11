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

import edu.monash.merc.eddy.modc.domain.Domain;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 16/09/2014
 */
public class DoiResource extends Domain {

    private long id;

    private String doi;

    private String url;

    @DateTimeFormat(pattern = "yyyy")
    private Date publicationYear;

    private String language;

    private DoiResourceType resourceType;

    private String resourceVersion;

    private String rights;

    private DoiPublisher publisher;

    private List<DoiCreator> doiCreators;

    private List<DoiTitle> titles;

    private List<DoiSubject> subjects;

    private List<DoiContributor> contributors;

    private List<DoiRelevantDate> relevantDates;

    private List<DoiAlternateIdentifier> alternateIdentifiers;

    private List<DoiRelatedIdentifier> relatedIdentifiers;

    private List<DoiResourceSize> sizes;

    private List<DoiResourceFormat> formats;

    private List<DoiDescription> descriptions;

    public DoiResource(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Date publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public DoiResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(DoiResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public DoiPublisher getPublisher() {
        return publisher;
    }

    public void setPublisher(DoiPublisher publisher) {
        this.publisher = publisher;
    }

    public List<DoiCreator> getDoiCreators() {
        return doiCreators;
    }

    public void setDoiCreators(List<DoiCreator> doiCreators) {
        this.doiCreators = doiCreators;
    }

    public List<DoiTitle> getTitles() {
        return titles;
    }

    public void setTitles(List<DoiTitle> titles) {
        this.titles = titles;
    }

    public List<DoiSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<DoiSubject> subjects) {
        this.subjects = subjects;
    }

    public List<DoiContributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<DoiContributor> contributors) {
        this.contributors = contributors;
    }

    public List<DoiRelevantDate> getRelevantDates() {
        return relevantDates;
    }

    public void setRelevantDates(List<DoiRelevantDate> relevantDates) {
        this.relevantDates = relevantDates;
    }

    public List<DoiAlternateIdentifier> getAlternateIdentifiers() {
        return alternateIdentifiers;
    }

    public void setAlternateIdentifiers(List<DoiAlternateIdentifier> alternateIdentifiers) {
        this.alternateIdentifiers = alternateIdentifiers;
    }

    public List<DoiRelatedIdentifier> getRelatedIdentifiers() {
        return relatedIdentifiers;
    }

    public void setRelatedIdentifiers(List<DoiRelatedIdentifier> relatedIdentifiers) {
        this.relatedIdentifiers = relatedIdentifiers;
    }

    public List<DoiResourceSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<DoiResourceSize> sizes) {
        this.sizes = sizes;
    }

    public List<DoiResourceFormat> getFormats() {
        return formats;
    }

    public void setFormats(List<DoiResourceFormat> formats) {
        this.formats = formats;
    }

    public List<DoiDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<DoiDescription> descriptions) {
        this.descriptions = descriptions;
    }
}
