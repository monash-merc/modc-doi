
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The personal name format should be: Family, Given.
 * 
 * <p>Java class for DContributor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DContributor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contributorName" type="{http://merc.monash.edu/ws/schema/doi}NoneEmptyString"/>
 *         &lt;element name="nameIdentifier" type="{http://merc.monash.edu/ws/schema/doi}DNameIdentifier" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="contributorType" use="required" type="{http://merc.monash.edu/ws/schema/doi}DContributorTypeAtt" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DContributor", propOrder = {
    "contributorName",
    "nameIdentifier"
})
public class DContributor {

    @XmlElement(required = true)
    protected String contributorName;
    protected DNameIdentifier nameIdentifier;
    @XmlAttribute(name = "contributorType", namespace = "http://merc.monash.edu/ws/schema/doi", required = true)
    protected DContributorTypeAtt contributorType;

    /**
     * Gets the value of the contributorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContributorName() {
        return contributorName;
    }

    /**
     * Sets the value of the contributorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContributorName(String value) {
        this.contributorName = value;
    }

    /**
     * Gets the value of the nameIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link DNameIdentifier }
     *     
     */
    public DNameIdentifier getNameIdentifier() {
        return nameIdentifier;
    }

    /**
     * Sets the value of the nameIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link DNameIdentifier }
     *     
     */
    public void setNameIdentifier(DNameIdentifier value) {
        this.nameIdentifier = value;
    }

    /**
     * Gets the value of the contributorType property.
     * 
     * @return
     *     possible object is
     *     {@link DContributorTypeAtt }
     *     
     */
    public DContributorTypeAtt getContributorType() {
        return contributorType;
    }

    /**
     * Sets the value of the contributorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DContributorTypeAtt }
     *     
     */
    public void setContributorType(DContributorTypeAtt value) {
        this.contributorType = value;
    }

}
