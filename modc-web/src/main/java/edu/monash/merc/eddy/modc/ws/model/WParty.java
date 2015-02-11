
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WParty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WParty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="identifier" type="{http://merc.monash.edu/ws/schema/mds}WIdentifier" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="person" type="{http://merc.monash.edu/ws/schema/mds}WPerson"/>
 *           &lt;element name="group" type="{http://merc.monash.edu/ws/schema/mds}WGroup"/>
 *         &lt;/choice>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://merc.monash.edu/ws/schema/mds}WDescType" minOccurs="0"/>
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
@XmlType(name = "WParty", namespace = "http://merc.monash.edu/ws/schema/mds", propOrder = {
    "key",
    "identifier",
    "person",
    "group",
    "url",
    "postalAddress",
    "description"
})
public class WParty {

    @XmlElement(required = true)
    protected String key;
    protected WIdentifier identifier;
    protected WPerson person;
    protected WGroup group;
    protected String url;
    protected String postalAddress;
    protected String description;
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
     * @return
     *     possible object is
     *     {@link WIdentifier }
     *     
     */
    public WIdentifier getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link WIdentifier }
     *     
     */
    public void setIdentifier(WIdentifier value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link WPerson }
     *     
     */
    public WPerson getPerson() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPerson }
     *     
     */
    public void setPerson(WPerson value) {
        this.person = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link WGroup }
     *     
     */
    public WGroup getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link WGroup }
     *     
     */
    public void setGroup(WGroup value) {
        this.group = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the postalAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalAddress() {
        return postalAddress;
    }

    /**
     * Sets the value of the postalAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalAddress(String value) {
        this.postalAddress = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
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
