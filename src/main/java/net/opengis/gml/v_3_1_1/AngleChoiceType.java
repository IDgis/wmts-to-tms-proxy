//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.11 at 11:05:40 AM CET 
//


package net.opengis.gml.v_3_1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Value of an angle quantity provided in either degree-minute-second format or single value format.
 * 
 * <p>Java class for AngleChoiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AngleChoiceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{http://www.opengis.net/gml}angle"/&gt;
 *         &lt;element ref="{http://www.opengis.net/gml}dmsAngle"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AngleChoiceType", propOrder = {
    "angle",
    "dmsAngle"
})
public class AngleChoiceType {

    protected MeasureType angle;
    protected DMSAngleType dmsAngle;

    /**
     * Gets the value of the angle property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureType }
     *     
     */
    public MeasureType getAngle() {
        return angle;
    }

    /**
     * Sets the value of the angle property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureType }
     *     
     */
    public void setAngle(MeasureType value) {
        this.angle = value;
    }

    public boolean isSetAngle() {
        return (this.angle!= null);
    }

    /**
     * Gets the value of the dmsAngle property.
     * 
     * @return
     *     possible object is
     *     {@link DMSAngleType }
     *     
     */
    public DMSAngleType getDmsAngle() {
        return dmsAngle;
    }

    /**
     * Sets the value of the dmsAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link DMSAngleType }
     *     
     */
    public void setDmsAngle(DMSAngleType value) {
        this.dmsAngle = value;
    }

    public boolean isSetDmsAngle() {
        return (this.dmsAngle!= null);
    }

}
