
package edu.monash.merc.eddy.modc.ws.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DContributors complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DContributors">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contributor" type="{http://merc.monash.edu/ws/schema/doi}DContributor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DContributors", propOrder = {
    "contributor"
})
public class DContributors {

    protected List<DContributor> contributor;

    /**
     * Gets the value of the contributor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contributor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContributor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DContributor }
     * 
     * 
     */
    public List<DContributor> getContributor() {
        if (contributor == null) {
            contributor = new ArrayList<DContributor>();
        }
        return this.contributor;
    }

}
