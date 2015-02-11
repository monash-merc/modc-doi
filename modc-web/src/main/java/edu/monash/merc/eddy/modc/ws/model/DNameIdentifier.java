
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for DNameIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DNameIdentifier">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://merc.monash.edu/ws/schema/doi>NoneEmptyString">
 *       &lt;attribute name="nameIdentifierScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DNameIdentifier", propOrder = {
    "value"
})
public class DNameIdentifier {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "nameIdentifierScheme", namespace = "http://merc.monash.edu/ws/schema/doi", required = true)
    protected String nameIdentifierScheme;

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
     * Gets the value of the nameIdentifierScheme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameIdentifierScheme() {
        return nameIdentifierScheme;
    }

    /**
     * Sets the value of the nameIdentifierScheme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameIdentifierScheme(String value) {
        this.nameIdentifierScheme = value;
    }

}
