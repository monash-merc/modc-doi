
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Subject, keywords, classification codes, or key phrases describing the
 *                 resource.
 *             
 * 
 * <p>Java class for DDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DDescription">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://merc.monash.edu/ws/schema/doi>NoneEmptyString">
 *       &lt;attribute name="descriptionType" use="required" type="{http://merc.monash.edu/ws/schema/doi}DDescriptionTypeAtt" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DDescription", propOrder = {
    "value"
})
public class DDescription {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "descriptionType", namespace = "http://merc.monash.edu/ws/schema/doi", required = true)
    protected DDescriptionTypeAtt descriptionType;

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
     * Gets the value of the descriptionType property.
     * 
     * @return
     *     possible object is
     *     {@link DDescriptionTypeAtt }
     *     
     */
    public DDescriptionTypeAtt getDescriptionType() {
        return descriptionType;
    }

    /**
     * Sets the value of the descriptionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DDescriptionTypeAtt }
     *     
     */
    public void setDescriptionType(DDescriptionTypeAtt value) {
        this.descriptionType = value;
    }

}
