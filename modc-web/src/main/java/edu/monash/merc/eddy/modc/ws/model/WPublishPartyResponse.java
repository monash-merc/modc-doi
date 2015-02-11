
package edu.monash.merc.eddy.modc.ws.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WPublishPartyResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WPublishPartyResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="identifier" type="{http://merc.monash.edu/ws/schema/mds}WIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="relation" use="required" type="{http://merc.monash.edu/ws/schema/mds}WPartyRelationType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WPublishPartyResponse", namespace = "http://merc.monash.edu/ws/schema/mds", propOrder = {
    "key",
    "identifier"
})
public class WPublishPartyResponse {

    @XmlElement(required = true)
    protected String key;
    protected List<WIdentifier> identifier;
    @XmlAttribute(name = "relation", namespace = "http://merc.monash.edu/ws/schema/mds", required = true)
    protected WPartyRelationType relation;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WIdentifier }
     * 
     * 
     */
    public List<WIdentifier> getIdentifier() {
        if (identifier == null) {
            identifier = new ArrayList<WIdentifier>();
        }
        return this.identifier;
    }

    /**
     * Gets the value of the relation property.
     * 
     * @return
     *     possible object is
     *     {@link WPartyRelationType }
     *     
     */
    public WPartyRelationType getRelation() {
        return relation;
    }

    /**
     * Sets the value of the relation property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPartyRelationType }
     *     
     */
    public void setRelation(WPartyRelationType value) {
        this.relation = value;
    }

}
