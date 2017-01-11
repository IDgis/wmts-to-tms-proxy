//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.27 at 02:31:50 PM CET 
//


package net.opengis.gml.v_3_1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A textual description and/or a set of parameters identifying a particular reference level surface used as a zero-height surface, including its position with respect to the Earth for any of the height types recognized by this standard. There are several types of Vertical Datums, and each may place constraints on the Coordinate Axis with which it is combined to create a Vertical CRS. 
 * 
 * <p>Java class for VerticalDatumType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VerticalDatumType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractDatumType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/gml}verticalDatumType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerticalDatumType", propOrder = {
    "verticalDatumType"
})
public class VerticalDatumType
    extends AbstractDatumType
{

    protected VerticalDatumTypeType verticalDatumType;

    /**
     * Gets the value of the verticalDatumType property.
     * 
     * @return
     *     possible object is
     *     {@link VerticalDatumTypeType }
     *     
     */
    public VerticalDatumTypeType getVerticalDatumType() {
        return verticalDatumType;
    }

    /**
     * Sets the value of the verticalDatumType property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerticalDatumTypeType }
     *     
     */
    public void setVerticalDatumType(VerticalDatumTypeType value) {
        this.verticalDatumType = value;
    }

    public boolean isSetVerticalDatumType() {
        return (this.verticalDatumType!= null);
    }

}
