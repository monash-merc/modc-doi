
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://merc.monash.edu/ws/schema/mds}WPublishResponseCode"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="result" type="{http://merc.monash.edu/ws/schema/mds}WPublishResponseResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "code",
    "message",
    "result"
})
@XmlRootElement(name = "WPublishResponse", namespace = "http://merc.monash.edu/ws/schema/mds")
public class WPublishResponse {

    @XmlElement(namespace = "http://merc.monash.edu/ws/schema/mds", required = true)
    protected WPublishResponseCode code;
    @XmlElement(namespace = "http://merc.monash.edu/ws/schema/mds", required = true)
    protected String message;
    @XmlElement(namespace = "http://merc.monash.edu/ws/schema/mds", required = true)
    protected WPublishResponseResult result;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link WPublishResponseCode }
     *     
     */
    public WPublishResponseCode getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPublishResponseCode }
     *     
     */
    public void setCode(WPublishResponseCode value) {
        this.code = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link WPublishResponseResult }
     *     
     */
    public WPublishResponseResult getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPublishResponseResult }
     *     
     */
    public void setResult(WPublishResponseResult value) {
        this.result = value;
    }

}
