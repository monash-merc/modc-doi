
package edu.monash.merc.eddy.modc.ws.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import edu.monash.merc.eddy.modc.ws.jaxbadapter.JaxbDateTimeAdapter;


/**
 * <p>Java class for WCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="identifier" type="{http://merc.monash.edu/ws/schema/mds}WIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://merc.monash.edu/ws/schema/mds}WDescType" minOccurs="0"/>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="postalAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="electronicAddress" type="{http://merc.monash.edu/ws/schema/mds}WElectronicAddress" minOccurs="0"/>
 *         &lt;element name="spatialCoverage" type="{http://merc.monash.edu/ws/schema/mds}WSpatialCoverage" minOccurs="0"/>
 *         &lt;element name="licence" type="{http://merc.monash.edu/ws/schema/mds}WLicence" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="accessRights" type="{http://merc.monash.edu/ws/schema/mds}WDescType" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://merc.monash.edu/ws/schema/mds}WSubject" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="publication" type="{http://merc.monash.edu/ws/schema/mds}WPublication" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="citation" type="{http://merc.monash.edu/ws/schema/mds}WCitation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="party" type="{http://merc.monash.edu/ws/schema/mds}WParty" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{http://merc.monash.edu/ws/schema/mds}WCollectionType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WCollection", namespace = "http://merc.monash.edu/ws/schema/mds", propOrder = {
    "key",
    "identifier",
    "name",
    "description",
    "createdDate",
    "endDate",
    "postalAddress",
    "electronicAddress",
    "spatialCoverage",
    "licence",
    "accessRights",
    "subject",
    "publication",
    "citation",
    "party"
})
public class WCollection {

    @XmlElement(required = true)
    protected String key;
    protected List<WIdentifier> identifier;
    @XmlElement(required = true)
    protected String name;
    protected String description;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(JaxbDateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date createdDate;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(JaxbDateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date endDate;
    protected String postalAddress;
    protected WElectronicAddress electronicAddress;
    protected WSpatialCoverage spatialCoverage;
    protected List<String> licence;
    protected String accessRights;
    protected List<WSubject> subject;
    protected List<WPublication> publication;
    protected List<WCitation> citation;
    @XmlElement(required = true)
    protected List<WParty> party;
    @XmlAttribute(name = "type", namespace = "http://merc.monash.edu/ws/schema/mds", required = true)
    protected WCollectionType type;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WIdentifier }
     * 
     * 
     */
    public List<WIdentifier> getIdentifier() {
        if (identifier == null) {
            identifier = new ArrayList<WIdentifier>();
        }
        return this.identifier;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedDate(Date value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(Date value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the postalAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalAddress() {
        return postalAddress;
    }

    /**
     * Sets the value of the postalAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalAddress(String value) {
        this.postalAddress = value;
    }

    /**
     * Gets the value of the electronicAddress property.
     * 
     * @return
     *     possible object is
     *     {@link WElectronicAddress }
     *     
     */
    public WElectronicAddress getElectronicAddress() {
        return electronicAddress;
    }

    /**
     * Sets the value of the electronicAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link WElectronicAddress }
     *     
     */
    public void setElectronicAddress(WElectronicAddress value) {
        this.electronicAddress = value;
    }

    /**
     * Gets the value of the spatialCoverage property.
     * 
     * @return
     *     possible object is
     *     {@link WSpatialCoverage }
     *     
     */
    public WSpatialCoverage getSpatialCoverage() {
        return spatialCoverage;
    }

    /**
     * Sets the value of the spatialCoverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSpatialCoverage }
     *     
     */
    public void setSpatialCoverage(WSpatialCoverage value) {
        this.spatialCoverage = value;
    }

    /**
     * Gets the value of the licence property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the licence property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLicence().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLicence() {
        if (licence == null) {
            licence = new ArrayList<String>();
        }
        return this.licence;
    }

    /**
     * Gets the value of the accessRights property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessRights() {
        return accessRights;
    }

    /**
     * Sets the value of the accessRights property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessRights(String value) {
        this.accessRights = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subject property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSubject }
     * 
     * 
     */
    public List<WSubject> getSubject() {
        if (subject == null) {
            subject = new ArrayList<WSubject>();
        }
        return this.subject;
    }

    /**
     * Gets the value of the publication property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the publication property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPublication().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WPublication }
     * 
     * 
     */
    public List<WPublication> getPublication() {
        if (publication == null) {
            publication = new ArrayList<WPublication>();
        }
        return this.publication;
    }

    /**
     * Gets the value of the citation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the citation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCitation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WCitation }
     * 
     * 
     */
    public List<WCitation> getCitation() {
        if (citation == null) {
            citation = new ArrayList<WCitation>();
        }
        return this.citation;
    }

    /**
     * Gets the value of the party property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the party property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WParty }
     * 
     * 
     */
    public List<WParty> getParty() {
        if (party == null) {
            party = new ArrayList<WParty>();
        }
        return this.party;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link WCollectionType }
     *     
     */
    public WCollectionType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link WCollectionType }
     *     
     */
    public void setType(WCollectionType value) {
        this.type = value;
    }

}
