
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * The type of a resource. You may enter an additional free text
 *                 description.
 *             
 * 
 * <p>Java class for DResourceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DResourceType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="resourceTypeGeneral" use="required" type="{http://merc.monash.edu/ws/schema/doi}DResourceTypeGeneralAtt" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DResourceType", propOrder = {
    "value"
})
public class DResourceType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "resourceTypeGeneral", namespace = "http://merc.monash.edu/ws/schema/doi", required = true)
    protected DResourceTypeGeneralAtt resourceTypeGeneral;

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
     * Gets the value of the resourceTypeGeneral property.
     * 
     * @return
     *     possible object is
     *     {@link DResourceTypeGeneralAtt }
     *     
     */
    public DResourceTypeGeneralAtt getResourceTypeGeneral() {
        return resourceTypeGeneral;
    }

    /**
     * Sets the value of the resourceTypeGeneral property.
     * 
     * @param value
     *     allowed object is
     *     {@link DResourceTypeGeneralAtt }
     *     
     */
    public void setResourceTypeGeneral(DResourceTypeGeneralAtt value) {
        this.resourceTypeGeneral = value;
    }

}
