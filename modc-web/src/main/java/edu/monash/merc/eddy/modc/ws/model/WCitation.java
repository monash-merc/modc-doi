
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for WCitation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WCitation">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="style" use="required" type="{http://merc.monash.edu/ws/schema/mds}WCitationType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WCitation", namespace = "http://merc.monash.edu/ws/schema/mds", propOrder = {
    "value"
})
public class WCitation {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "style", namespace = "http://merc.monash.edu/ws/schema/mds", required = true)
    protected WCitationType style;

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
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link WCitationType }
     *     
     */
    public WCitationType getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link WCitationType }
     *     
     */
    public void setStyle(WCitationType value) {
        this.style = value;
    }

}
