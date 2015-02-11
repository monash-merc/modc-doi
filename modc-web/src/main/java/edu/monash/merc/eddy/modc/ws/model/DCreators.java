
package edu.monash.merc.eddy.modc.ws.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DCreators complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DCreators">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creator" type="{http://merc.monash.edu/ws/schema/doi}DCreator" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DCreators", propOrder = {
    "creator"
})
public class DCreators {

    @XmlElement(required = true)
    protected List<DCreator> creator;

    /**
     * Gets the value of the creator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DCreator }
     * 
     * 
     */
    public List<DCreator> getCreator() {
        if (creator == null) {
            creator = new ArrayList<DCreator>();
        }
        return this.creator;
    }

}
