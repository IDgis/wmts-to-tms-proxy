//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.11 at 11:05:40 AM CET 
//


package net.opengis.ows._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="unbounded"&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}Value"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}Range"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "valueOrRange"
})
@XmlRootElement(name = "AllowedValues")
public class AllowedValues {

    @XmlElements({
        @XmlElement(name = "Value", type = ValueType.class),
        @XmlElement(name = "Range", type = RangeType.class)
    })
    protected List<Object> valueOrRange;

    /**
     * Gets the value of the valueOrRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueOrRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueOrRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValueType }
     * {@link RangeType }
     * 
     * 
     */
    public List<Object> getValueOrRange() {
        if (valueOrRange == null) {
            valueOrRange = new ArrayList<Object>();
        }
        return this.valueOrRange;
    }

    public boolean isSetValueOrRange() {
        return ((this.valueOrRange!= null)&&(!this.valueOrRange.isEmpty()));
    }

    public void unsetValueOrRange() {
        this.valueOrRange = null;
    }

}
