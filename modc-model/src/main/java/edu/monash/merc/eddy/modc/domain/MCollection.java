package edu.monash.merc.eddy.modc.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by simonyu on 1/08/2014.
 */
@Entity
@Table(name = "collection")
public class MCollection extends Domain {
    @Id
    @GeneratedValue(generator = "pk_generator")
    @GenericGenerator(name = "pk_generator", strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "table_name", value = "pk_gen_tab"),
                    @org.hibernate.annotations.Parameter(name = "value_column_name ", value = "pk_next_val"),
                    @org.hibernate.annotations.Parameter(name = "segment_column_name", value = "pk_name"),
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "collection_id"),
                    @org.hibernate.annotations.Parameter(name = "increment_size  ", value = "5"),
                    @org.hibernate.annotations.Parameter(name = "optimizer ", value = "hilo")
            })
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "ref_key")
    private String refKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MCollectionType type;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 4000)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    private Date endDate;

    @Column(name = "access_rights", length = 2000)
    private String accessRights;

    @OneToOne(mappedBy = "collection", targetEntity = MAddress.class, fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private MAddress address;

    @OneToOne(mappedBy = "collection", targetEntity = MCoverage.class, fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private MCoverage coverage;

    @OneToMany(mappedBy = "collection", targetEntity = MPublication.class, fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<MPublication> publications;

    @OneToMany(mappedBy = "collection", targetEntity = MCitation.class, fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<MCitation> citations;

    @OneToMany(mappedBy = "collection", targetEntity = MIdentifier.class, fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<MIdentifier> identifiers;

    @ManyToMany(targetEntity = MParty.class, fetch = FetchType.LAZY)
    @JoinTable(name = "collection_party", joinColumns = {@JoinColumn(name = "collection_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "party_id", referencedColumnName = "id")}, uniqueConstraints = {@UniqueConstraint(columnNames = {
            "collection_id", "party_id"})})
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<MParty> parties = new ArrayList<>();

    @ManyToMany(targetEntity = MKeyword.class, fetch = FetchType.LAZY)
    @JoinTable(name = "collection_keyword", joinColumns = {@JoinColumn(name = "collection_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "keyword_id", referencedColumnName = "id")}, uniqueConstraints = {@UniqueConstraint(columnNames = {
            "collection_id", "keyword_id"})})
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<MKeyword> keywords = new ArrayList<>();

    @OneToMany(mappedBy = "collection", targetEntity = MLicence.class, fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<MLicence> licences;

    @ManyToOne(targetEntity = ServiceApp.class)
    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false)
    private ServiceApp serviceApp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRefKey() {
        return refKey;
    }

    public void setRefKey(String refKey) {
        this.refKey = refKey;
    }

    public MCollectionType getType() {
        return type;
    }

    public void setType(MCollectionType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public MAddress getAddress() {
        return address;
    }

    public void setAddress(MAddress address) {
        this.address = address;
    }

    public MCoverage getCoverage() {
        return coverage;
    }

    public void setCoverage(MCoverage coverage) {
        this.coverage = coverage;
    }

    public List<MPublication> getPublications() {
        return publications;
    }

    public void setPublications(List<MPublication> publications) {
        this.publications = publications;
    }

    public List<MCitation> getCitations() {
        return citations;
    }

    public void setCitations(List<MCitation> citations) {
        this.citations = citations;
    }

    public List<MIdentifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<MIdentifier> identifiers) {
        this.identifiers = identifiers;
    }

    public List<MParty> getParties() {
        return parties;
    }

    public void setParties(List<MParty> parties) {
        this.parties = parties;
    }

    public List<MKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<MKeyword> keywords) {
        this.keywords = keywords;
    }

    public List<MLicence> getLicences() {
        return licences;
    }

    public void setLicences(List<MLicence> licences) {
        this.licences = licences;
    }

    public ServiceApp getServiceApp() {
        return serviceApp;
    }

    public void setServiceApp(ServiceApp serviceApp) {
        this.serviceApp = serviceApp;
    }
}
