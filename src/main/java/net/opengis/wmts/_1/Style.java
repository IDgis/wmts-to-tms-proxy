//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.27 at 02:31:50 PM CET 
//


package net.opengis.wmts._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import net.opengis.ows._1.CodeType;
import net.opengis.ows._1.DescriptionType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/ows/1.1}DescriptionType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}Identifier"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wmts/1.0}LegendURL" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="isDefault" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "identifier",
    "legendURL"
})
@XmlRootElement(name = "Style")
public class Style
    extends DescriptionType
{

    @XmlElement(name = "Identifier", namespace = "http://www.opengis.net/ows/1.1", required = true)
    protected CodeType identifier;
    @XmlElement(name = "LegendURL")
    protected List<LegendURL> legendURL;
    @XmlAttribute(name = "isDefault")
    protected Boolean isDefault;

    /**
     * 
     * 									An unambiguous reference to this style, identifying 
     * 									a specific version when needed, normally used by software
     * 								
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setIdentifier(CodeType value) {
        this.identifier = value;
    }

    public boolean isSetIdentifier() {
        return (this.identifier!= null);
    }

    /**
     * Description of an image that represents 
     * 								the legend of the map Gets the value of the legendURL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the legendURL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLegendURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LegendURL }
     * 
     * 
     */
    public List<LegendURL> getLegendURL() {
        if (legendURL == null) {
            legendURL = new ArrayList<LegendURL>();
        }
        return this.legendURL;
    }

    public boolean isSetLegendURL() {
        return ((this.legendURL!= null)&&(!this.legendURL.isEmpty()));
    }

    public void unsetLegendURL() {
        this.legendURL = null;
    }

    /**
     * Gets the value of the isDefault property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsDefault() {
        return isDefault;
    }

    /**
     * Sets the value of the isDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDefault(boolean value) {
        this.isDefault = value;
    }

    public boolean isSetIsDefault() {
        return (this.isDefault!= null);
    }

    public void unsetIsDefault() {
        this.isDefault = null;
    }

}
