
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WPublishResponseResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WPublishResponseResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="collection" type="{http://merc.monash.edu/ws/schema/mds}WCollecionResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WPublishResponseResult", namespace = "http://merc.monash.edu/ws/schema/mds", propOrder = {
    "collection"
})
public class WPublishResponseResult {

    protected WCollecionResponse collection;

    /**
     * Gets the value of the collection property.
     * 
     * @return
     *     possible object is
     *     {@link WCollecionResponse }
     *     
     */
    public WCollecionResponse getCollection() {
        return collection;
    }

    /**
     * Sets the value of the collection property.
     * 
     * @param value
     *     allowed object is
     *     {@link WCollecionResponse }
     *     
     */
    public void setCollection(WCollecionResponse value) {
        this.collection = value;
    }

}
