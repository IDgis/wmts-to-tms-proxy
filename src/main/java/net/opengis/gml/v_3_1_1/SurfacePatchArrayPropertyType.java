//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.11 at 11:05:40 AM CET 
//


package net.opengis.gml.v_3_1_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A container for an array of surface patches.
 * 
 * <p>Java class for SurfacePatchArrayPropertyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SurfacePatchArrayPropertyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *         &lt;element ref="{http://www.opengis.net/gml}_SurfacePatch"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SurfacePatchArrayPropertyType", propOrder = {
    "surfacePatch"
})
public class SurfacePatchArrayPropertyType {

    @XmlElementRef(name = "_SurfacePatch", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected List<JAXBElement<? extends AbstractSurfacePatchType>> surfacePatch;

    /**
     * Gets the value of the surfacePatch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the surfacePatch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSurfacePatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link SphereType }{@code >}
     * {@link JAXBElement }{@code <}{@link CylinderType }{@code >}
     * {@link JAXBElement }{@code <}{@link RectangleType }{@code >}
     * {@link JAXBElement }{@code <}{@link PolygonPatchType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractSurfacePatchType }{@code >}
     * {@link JAXBElement }{@code <}{@link TriangleType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractParametricCurveSurfaceType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractGriddedSurfaceType }{@code >}
     * {@link JAXBElement }{@code <}{@link ConeType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends AbstractSurfacePatchType>> getSurfacePatch() {
        if (surfacePatch == null) {
            surfacePatch = new ArrayList<JAXBElement<? extends AbstractSurfacePatchType>>();
        }
        return this.surfacePatch;
    }

    public boolean isSetSurfacePatch() {
        return ((this.surfacePatch!= null)&&(!this.surfacePatch.isEmpty()));
    }

    public void unsetSurfacePatch() {
        this.surfacePatch = null;
    }

}
