//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.11 at 11:05:40 AM CET 
//


package net.opengis.gml.v_3_1_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A prime meridian defines the origin from which longitude values are determined.
 * 
 * <p>Java class for PrimeMeridianType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrimeMeridianType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/gml}PrimeMeridianBaseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/gml}meridianID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/gml}remarks" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/gml}greenwichLongitude"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrimeMeridianType", propOrder = {
    "meridianID",
    "remarks",
    "greenwichLongitude"
})
public class PrimeMeridianType
    extends PrimeMeridianBaseType
{

    protected List<IdentifierType> meridianID;
    protected StringOrRefType remarks;
    @XmlElement(required = true)
    protected AngleChoiceType greenwichLongitude;

    /**
     * Set of alternative identifications of this prime meridian. The first meridianID, if any, is normally the primary identification code, and any others are aliases. Gets the value of the meridianID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the meridianID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeridianID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifierType }
     * 
     * 
     */
    public List<IdentifierType> getMeridianID() {
        if (meridianID == null) {
            meridianID = new ArrayList<IdentifierType>();
        }
        return this.meridianID;
    }

    public boolean isSetMeridianID() {
        return ((this.meridianID!= null)&&(!this.meridianID.isEmpty()));
    }

    public void unsetMeridianID() {
        this.meridianID = null;
    }

    /**
     * Comments on or information about this prime meridian, including source information. 
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setRemarks(StringOrRefType value) {
        this.remarks = value;
    }

    public boolean isSetRemarks() {
        return (this.remarks!= null);
    }

    /**
     * Gets the value of the greenwichLongitude property.
     * 
     * @return
     *     possible object is
     *     {@link AngleChoiceType }
     *     
     */
    public AngleChoiceType getGreenwichLongitude() {
        return greenwichLongitude;
    }

    /**
     * Sets the value of the greenwichLongitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link AngleChoiceType }
     *     
     */
    public void setGreenwichLongitude(AngleChoiceType value) {
        this.greenwichLongitude = value;
    }

    public boolean isSetGreenwichLongitude() {
        return (this.greenwichLongitude!= null);
    }

}
