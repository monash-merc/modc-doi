
package edu.monash.merc.eddy.modc.ws.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DRelatedIdentifiers complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DRelatedIdentifiers">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="relatedIdentifier" type="{http://merc.monash.edu/ws/schema/doi}DRelatedIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DRelatedIdentifiers", propOrder = {
    "relatedIdentifier"
})
public class DRelatedIdentifiers {

    protected List<DRelatedIdentifier> relatedIdentifier;

    /**
     * Gets the value of the relatedIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DRelatedIdentifier }
     * 
     * 
     */
    public List<DRelatedIdentifier> getRelatedIdentifier() {
        if (relatedIdentifier == null) {
            relatedIdentifier = new ArrayList<DRelatedIdentifier>();
        }
        return this.relatedIdentifier;
    }

}
