
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Format: Family, Given.
 * 
 * <p>Java class for DCreator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DCreator">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creatorName" type="{http://merc.monash.edu/ws/schema/doi}NoneEmptyString"/>
 *         &lt;element name="nameIdentifier" type="{http://merc.monash.edu/ws/schema/doi}DNameIdentifier" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DCreator", propOrder = {
    "creatorName",
    "nameIdentifier"
})
public class DCreator {

    @XmlElement(required = true)
    protected String creatorName;
    protected DNameIdentifier nameIdentifier;

    /**
     * Gets the value of the creatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * Sets the value of the creatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorName(String value) {
        this.creatorName = value;
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

}
