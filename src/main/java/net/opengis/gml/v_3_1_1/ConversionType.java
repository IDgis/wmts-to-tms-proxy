//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.27 at 02:31:50 PM CET 
//


package net.opengis.gml.v_3_1_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A concrete operation on coordinates that does not include any change of Datum. The best-known example of a coordinate conversion is a map projection. The parameters describing coordinate conversions are defined rather than empirically derived. Note that some conversions have no parameters.
 * 
 * This concrete complexType can be used with all operation methods, without using an Application Schema that defines operation-method-specialized element names and contents, especially for methods with only one Conversion instance. 
 * 
 * <p>Java class for ConversionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConversionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeneralConversionType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/gml}usesMethod"/&gt;
 *         &lt;element ref="{http://www.opengis.net/gml}usesValue" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConversionType", propOrder = {
    "usesMethod",
    "usesValue"
})
public class ConversionType
    extends AbstractGeneralConversionType
{

    @XmlElement(required = true)
    protected OperationMethodRefType usesMethod;
    protected List<ParameterValueType> usesValue;

    /**
     * Gets the value of the usesMethod property.
     * 
     * @return
     *     possible object is
     *     {@link OperationMethodRefType }
     *     
     */
    public OperationMethodRefType getUsesMethod() {
        return usesMethod;
    }

    /**
     * Sets the value of the usesMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationMethodRefType }
     *     
     */
    public void setUsesMethod(OperationMethodRefType value) {
        this.usesMethod = value;
    }

    public boolean isSetUsesMethod() {
        return (this.usesMethod!= null);
    }

    /**
     * Unordered list of composition associations to the set of parameter values used by this conversion operation. Gets the value of the usesValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usesValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsesValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterValueType }
     * 
     * 
     */
    public List<ParameterValueType> getUsesValue() {
        if (usesValue == null) {
            usesValue = new ArrayList<ParameterValueType>();
        }
        return this.usesValue;
    }

    public boolean isSetUsesValue() {
        return ((this.usesValue!= null)&&(!this.usesValue.isEmpty()));
    }

    public void unsetUsesValue() {
        this.usesValue = null;
    }

}
