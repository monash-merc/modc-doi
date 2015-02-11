
package edu.monash.merc.eddy.modc.ws.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WCollecionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WCollecionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="identifier" type="{http://merc.monash.edu/ws/schema/mds}WIdentifier" maxOccurs="unbounded"/>
 *         &lt;element name="party" type="{http://merc.monash.edu/ws/schema/mds}WPublishPartyResponse" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WCollecionResponse", namespace = "http://merc.monash.edu/ws/schema/mds", propOrder = {
    "key",
    "identifier",
    "party"
})
public class WCollecionResponse {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected List<WIdentifier> identifier;
    @XmlElement(required = true)
    protected List<WPublishPartyResponse> party;

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
     * Gets the value of the party property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the party property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WPublishPartyResponse }
     * 
     * 
     */
    public List<WPublishPartyResponse> getParty() {
        if (party == null) {
            party = new ArrayList<WPublishPartyResponse>();
        }
        return this.party;
    }

}
