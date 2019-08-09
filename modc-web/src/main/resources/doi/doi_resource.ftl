<?xml version="1.0" encoding="UTF-8"?>
<resource xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://datacite.org/schema/kernel-${schemaVersion}"
          xsi:schemaLocation="http://datacite.org/schema/kernel-${schemaVersion} http://schema.datacite.org/meta/kernel-${schemaVersion}/metadata.xsd">
<#if doi??>
    <identifier identifierType="DOI">${doi}</identifier>
</#if>
<#if doiCreators??>
    <creators>
        <#list doiCreators as doiCreator>
            <creator>
                <creatorName>${doiCreator.creatorName}</creatorName>
                <#if doiCreator.nameIdentifier??>
                    <nameIdentifier
                            nameIdentifierScheme="${doiCreator.nameIdentifier.nameIdentifierScheme}">${doiCreator.nameIdentifier.identifier}</nameIdentifier>
                </#if>
            </creator>
        </#list>
    </creators>
</#if>
<#if doiTitles??>
    <titles>
        <#list doiTitles as doiTitle>
            <#if doiTitle.titleType??>
                <title titleType="${doiTitle.titleType}">${doiTitle.title}</title>
            <#else>
                <title>${doiTitle.title}</title>
            </#if>
        </#list>
    </titles>
</#if>
<#if doiPublisher??>
    <publisher>${doiPublisher.publisher}</publisher>
</#if>
<#if doiPublicationYear??>
    <publicationYear>${doiPublicationYear?string("yyyy")}</publicationYear>
</#if>
<#if doiSubjects??>
    <subjects>
        <#list doiSubjects as doiSubject>
            <#if doiSubject.subjectScheme??>
                <subject subjectScheme="${doiSubject.subjectScheme}">${doiSubject.subject}</subject>
            <#else>
                <subject>${doiSubject.subject}</subject>
            </#if>
        </#list>
    </subjects>
</#if>
<#if doiContributors??>
    <contributors>
        <#list doiContributors as doiContributor>
            <contributor contributorType="${doiContributor.contributorType}">
                <contributorName>${doiContributor.contributorName}</contributorName>
                <#if doiContributor.nameIdentifier??>
                    <nameIdentifier
                            nameIdentifierScheme="${doiContributor.nameIdentifier.nameIdentifierScheme}">${doiContributor.nameIdentifier.identifier}</nameIdentifier>
                </#if>
            </contributor>
        </#list>
    </contributors>
</#if>
<#if doiRelevantDates??>
    <dates>
        <#list doiRelevantDates as doiRelevantDate>
            <date dateType="${doiRelevantDate.dateType}">${doiRelevantDate.relevantDate?string("yyyy-MM-dd")}</date>
        </#list>
    </dates>
</#if>
<#if doiLanguage??>
    <language>${doiLanguage}</language>
</#if>
<#if doiResourceType??>
    <resourceType resourceTypeGeneral="${doiResourceType.resourceTypeGeneral}">${doiResourceType.type}</resourceType>
</#if>
<#if doiAlternateIdentifiers??>
    <alternateIdentifiers>
        <#list doiAlternateIdentifiers as doiAltId>
            <alternateIdentifier
                    alternateIdentifierType="${doiAltId.alternateIdentifierType}">${doiAltId.alternateIdentifier}</alternateIdentifier>
        </#list>
    </alternateIdentifiers>
</#if>
<#if doiRelatedIdentifiers??>
    <relatedIdentifiers>
        <#list doiRelatedIdentifiers as doiRelId>
            <relatedIdentifier relatedIdentifierType="${doiRelId.relatedIdentifierType}"
                               relationType="${doiRelId.relationType}">${doiRelId.relatedIdentifier}</relatedIdentifier>
        </#list>
    </relatedIdentifiers>
</#if>
<#if doiResourceSizes??>
    <sizes>
        <#list doiResourceSizes as doiResourceSize>
            <size>${doiResourceSize.size}</size>
        </#list>
    </sizes>
</#if>
<#if doiResourceFormats??>
    <formats>
        <#list doiResourceFormats as doiResourceFormat>
            <format>${doiResourceFormat.format}</format>
        </#list>
    </formats>
</#if>
<#if resourceVersion??>
    <version>${resourceVersion}</version>
</#if>
<#if resourceRights??>
    <rights>${resourceRights}</rights>
</#if>
<#if doiDescriptions??>
    <descriptions>
        <#list doiDescriptions as doiDesc>
            <description descriptionType="${doiDesc.descriptionType}">${doiDesc.description}</description>
        </#list>
    </descriptions>
</#if>
</resource>