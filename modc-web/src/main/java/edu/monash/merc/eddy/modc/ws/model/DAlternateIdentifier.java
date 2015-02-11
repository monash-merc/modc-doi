
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * An identifier other than the primary identifier applied to the resource being registered. This may be any alphanumeric string which is unique within its domain of issue. The
 *                 format is open
 *             
 * 
 * <p>Java class for DAlternateIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DAlternateIdentifier">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="alternateIdentifierType" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DAlternateIdentifier", propOrder = {
    "value"
})
public class DAlternateIdentifier {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "alternateIdentifierType", namespace = "http://merc.monash.edu/ws/schema/doi", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String alternateIdentifierType;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the alternateIdentifierType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternateIdentifierType() {
        return alternateIdentifierType;
    }

    /**
     * Sets the value of the alternateIdentifierType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternateIdentifierType(String value) {
        this.alternateIdentifierType = value;
    }

}
