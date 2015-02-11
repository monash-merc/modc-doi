
package edu.monash.merc.eddy.modc.ws.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import edu.monash.merc.eddy.modc.ws.jaxbadapter.JaxbDateTimeAdapter;


/**
 * YYYY or YYYY-MM-DD or any other format described in W3CDTF
 *                 (http://www.w3.org/TR/NOTE-datetime)
 *             
 * 
 * <p>Java class for DDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DDate">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>dateTime">
 *       &lt;attribute name="dateType" use="required" type="{http://merc.monash.edu/ws/schema/doi}DDateTypeAtt" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DDate", propOrder = {
    "value"
})
public class DDate {

    @XmlValue
    @XmlJavaTypeAdapter(JaxbDateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date value;
    @XmlAttribute(name = "dateType", namespace = "http://merc.monash.edu/ws/schema/doi", required = true)
    protected DDateTypeAtt dateType;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getValue() {
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
    public void setValue(Date value) {
        this.value = value;
    }

    /**
     * Gets the value of the dateType property.
     * 
     * @return
     *     possible object is
     *     {@link DDateTypeAtt }
     *     
     */
    public DDateTypeAtt getDateType() {
        return dateType;
    }

    /**
     * Sets the value of the dateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DDateTypeAtt }
     *     
     */
    public void setDateType(DDateTypeAtt value) {
        this.dateType = value;
    }

}
