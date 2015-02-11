
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
 * <p>Java class for DSubject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DSubject">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://merc.monash.edu/ws/schema/doi>NoneEmptyString">
 *       &lt;attribute name="subjectScheme" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DSubject", propOrder = {
    "value"
})
public class DSubject {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "subjectScheme", namespace = "http://merc.monash.edu/ws/schema/doi")
    protected String subjectScheme;

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
     * Gets the value of the subjectScheme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubjectScheme() {
        return subjectScheme;
    }

    /**
     * Sets the value of the subjectScheme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubjectScheme(String value) {
        this.subjectScheme = value;
    }

}
