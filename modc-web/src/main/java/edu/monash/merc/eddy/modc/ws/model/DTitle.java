
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * A name or title by which a resource is known.
 * 
 * <p>Java class for DTitle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DTitle">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://merc.monash.edu/ws/schema/doi>NoneEmptyString">
 *       &lt;attribute name="titleType" type="{http://merc.monash.edu/ws/schema/doi}DTitleTypeAtt" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DTitle", propOrder = {
    "value"
})
public class DTitle {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "titleType", namespace = "http://merc.monash.edu/ws/schema/doi")
    protected DTitleTypeAtt titleType;

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
     * Gets the value of the titleType property.
     * 
     * @return
     *     possible object is
     *     {@link DTitleTypeAtt }
     *     
     */
    public DTitleTypeAtt getTitleType() {
        return titleType;
    }

    /**
     * Sets the value of the titleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DTitleTypeAtt }
     *     
     */
    public void setTitleType(DTitleTypeAtt value) {
        this.titleType = value;
    }

}
