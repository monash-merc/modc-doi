
package edu.monash.merc.eddy.modc.ws.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import edu.monash.merc.eddy.modc.ws.jaxbadapter.JaxbDateAdapter;


/**
 * <p>Java class for DResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DResource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creators" type="{http://merc.monash.edu/ws/schema/doi}DCreators"/>
 *         &lt;element name="titles" type="{http://merc.monash.edu/ws/schema/doi}DTitles"/>
 *         &lt;element name="publisher" type="{http://merc.monash.edu/ws/schema/doi}NoneEmptyString"/>
 *         &lt;element name="publicationYear" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="subjects" type="{http://merc.monash.edu/ws/schema/doi}DSubjects" minOccurs="0"/>
 *         &lt;element name="contributors" type="{http://merc.monash.edu/ws/schema/doi}DContributors" minOccurs="0"/>
 *         &lt;element name="dates" type="{http://merc.monash.edu/ws/schema/doi}DDates" minOccurs="0"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}language" minOccurs="0"/>
 *         &lt;element name="resourceType" type="{http://merc.monash.edu/ws/schema/doi}DResourceType"/>
 *         &lt;element name="alternateIdentifiers" type="{http://merc.monash.edu/ws/schema/doi}DAlternateIdentifiers" minOccurs="0"/>
 *         &lt;element name="relatedIdentifiers" type="{http://merc.monash.edu/ws/schema/doi}DRelatedIdentifiers" minOccurs="0"/>
 *         &lt;element name="sizes" type="{http://merc.monash.edu/ws/schema/doi}DSizes" minOccurs="0"/>
 *         &lt;element name="formats" type="{http://merc.monash.edu/ws/schema/doi}DFormats" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rights" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptions" type="{http://merc.monash.edu/ws/schema/doi}DDescriptions" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DResource", propOrder = {
    "creators",
    "titles",
    "publisher",
    "publicationYear",
    "subjects",
    "contributors",
    "dates",
    "language",
    "resourceType",
    "alternateIdentifiers",
    "relatedIdentifiers",
    "sizes",
    "formats",
    "version",
    "rights",
    "descriptions"
})
public class DResource {

    @XmlElement(required = true)
    protected DCreators creators;
    @XmlElement(required = true)
    protected DTitles titles;
    @XmlElement(required = true)
    protected String publisher;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date publicationYear;
    protected DSubjects subjects;
    protected DContributors contributors;
    protected DDates dates;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String language;
    @XmlElement(required = true)
    protected DResourceType resourceType;
    protected DAlternateIdentifiers alternateIdentifiers;
    protected DRelatedIdentifiers relatedIdentifiers;
    protected DSizes sizes;
    protected DFormats formats;
    protected String version;
    protected String rights;
    protected DDescriptions descriptions;

    /**
     * Gets the value of the creators property.
     * 
     * @return
     *     possible object is
     *     {@link DCreators }
     *     
     */
    public DCreators getCreators() {
        return creators;
    }

    /**
     * Sets the value of the creators property.
     * 
     * @param value
     *     allowed object is
     *     {@link DCreators }
     *     
     */
    public void setCreators(DCreators value) {
        this.creators = value;
    }

    /**
     * Gets the value of the titles property.
     * 
     * @return
     *     possible object is
     *     {@link DTitles }
     *     
     */
    public DTitles getTitles() {
        return titles;
    }

    /**
     * Sets the value of the titles property.
     * 
     * @param value
     *     allowed object is
     *     {@link DTitles }
     *     
     */
    public void setTitles(DTitles value) {
        this.titles = value;
    }

    /**
     * Gets the value of the publisher property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the value of the publisher property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublisher(String value) {
        this.publisher = value;
    }

    /**
     * Gets the value of the publicationYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPublicationYear() {
        return publicationYear;
    }

    /**
     * Sets the value of the publicationYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicationYear(Date value) {
        this.publicationYear = value;
    }

    /**
     * Gets the value of the subjects property.
     * 
     * @return
     *     possible object is
     *     {@link DSubjects }
     *     
     */
    public DSubjects getSubjects() {
        return subjects;
    }

    /**
     * Sets the value of the subjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link DSubjects }
     *     
     */
    public void setSubjects(DSubjects value) {
        this.subjects = value;
    }

    /**
     * Gets the value of the contributors property.
     * 
     * @return
     *     possible object is
     *     {@link DContributors }
     *     
     */
    public DContributors getContributors() {
        return contributors;
    }

    /**
     * Sets the value of the contributors property.
     * 
     * @param value
     *     allowed object is
     *     {@link DContributors }
     *     
     */
    public void setContributors(DContributors value) {
        this.contributors = value;
    }

    /**
     * Gets the value of the dates property.
     * 
     * @return
     *     possible object is
     *     {@link DDates }
     *     
     */
    public DDates getDates() {
        return dates;
    }

    /**
     * Sets the value of the dates property.
     * 
     * @param value
     *     allowed object is
     *     {@link DDates }
     *     
     */
    public void setDates(DDates value) {
        this.dates = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the resourceType property.
     * 
     * @return
     *     possible object is
     *     {@link DResourceType }
     *     
     */
    public DResourceType getResourceType() {
        return resourceType;
    }

    /**
     * Sets the value of the resourceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DResourceType }
     *     
     */
    public void setResourceType(DResourceType value) {
        this.resourceType = value;
    }

    /**
     * Gets the value of the alternateIdentifiers property.
     * 
     * @return
     *     possible object is
     *     {@link DAlternateIdentifiers }
     *     
     */
    public DAlternateIdentifiers getAlternateIdentifiers() {
        return alternateIdentifiers;
    }

    /**
     * Sets the value of the alternateIdentifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link DAlternateIdentifiers }
     *     
     */
    public void setAlternateIdentifiers(DAlternateIdentifiers value) {
        this.alternateIdentifiers = value;
    }

    /**
     * Gets the value of the relatedIdentifiers property.
     * 
     * @return
     *     possible object is
     *     {@link DRelatedIdentifiers }
     *     
     */
    public DRelatedIdentifiers getRelatedIdentifiers() {
        return relatedIdentifiers;
    }

    /**
     * Sets the value of the relatedIdentifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link DRelatedIdentifiers }
     *     
     */
    public void setRelatedIdentifiers(DRelatedIdentifiers value) {
        this.relatedIdentifiers = value;
    }

    /**
     * Gets the value of the sizes property.
     * 
     * @return
     *     possible object is
     *     {@link DSizes }
     *     
     */
    public DSizes getSizes() {
        return sizes;
    }

    /**
     * Sets the value of the sizes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DSizes }
     *     
     */
    public void setSizes(DSizes value) {
        this.sizes = value;
    }

    /**
     * Gets the value of the formats property.
     * 
     * @return
     *     possible object is
     *     {@link DFormats }
     *     
     */
    public DFormats getFormats() {
        return formats;
    }

    /**
     * Sets the value of the formats property.
     * 
     * @param value
     *     allowed object is
     *     {@link DFormats }
     *     
     */
    public void setFormats(DFormats value) {
        this.formats = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the rights property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRights() {
        return rights;
    }

    /**
     * Sets the value of the rights property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRights(String value) {
        this.rights = value;
    }

    /**
     * Gets the value of the descriptions property.
     * 
     * @return
     *     possible object is
     *     {@link DDescriptions }
     *     
     */
    public DDescriptions getDescriptions() {
        return descriptions;
    }

    /**
     * Sets the value of the descriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link DDescriptions }
     *     
     */
    public void setDescriptions(DDescriptions value) {
        this.descriptions = value;
    }

}
