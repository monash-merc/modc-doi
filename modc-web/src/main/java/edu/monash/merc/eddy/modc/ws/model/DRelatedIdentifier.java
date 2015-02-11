
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Identifiers of related resources. Use this property to indicate subsets of properties, as appropriate.
 * 
 * <p>Java class for DRelatedIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DRelatedIdentifier">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="relatedIdentifierType" use="required" type="{http://merc.monash.edu/ws/schema/doi}DRelatedIdentifierTypeAtt" />
 *       &lt;attribute name="relationType" use="required" type="{http://merc.monash.edu/ws/schema/doi}DRelationTypeAtt" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DRelatedIdentifier", propOrder = {
    "value"
})
public class DRelatedIdentifier {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "relatedIdentifierType", namespace = "http://merc.monash.edu/ws/schema/doi", required = true)
    protected DRelatedIdentifierTypeAtt relatedIdentifierType;
    @XmlAttribute(name = "relationType", namespace = "http://merc.monash.edu/ws/schema/doi", required = true)
    protected DRelationTypeAtt relationType;

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
     * Gets the value of the relatedIdentifierType property.
     * 
     * @return
     *     possible object is
     *     {@link DRelatedIdentifierTypeAtt }
     *     
     */
    public DRelatedIdentifierTypeAtt getRelatedIdentifierType() {
        return relatedIdentifierType;
    }

    /**
     * Sets the value of the relatedIdentifierType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DRelatedIdentifierTypeAtt }
     *     
     */
    public void setRelatedIdentifierType(DRelatedIdentifierTypeAtt value) {
        this.relatedIdentifierType = value;
    }

    /**
     * Gets the value of the relationType property.
     * 
     * @return
     *     possible object is
     *     {@link DRelationTypeAtt }
     *     
     */
    public DRelationTypeAtt getRelationType() {
        return relationType;
    }

    /**
     * Sets the value of the relationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DRelationTypeAtt }
     *     
     */
    public void setRelationType(DRelationTypeAtt value) {
        this.relationType = value;
    }

}
