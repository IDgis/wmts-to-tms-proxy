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
 * A Geodesic consists of two distinct
 *    positions joined by a geodesic curve. The control points of
 *    a Geodesic shall lie on the geodesic between its start
 *    point and end points. Between these two points, a geodesic
 *    curve defined from ellipsoid or geoid model used by the
 *    co-ordinate reference systems may be used to interpolate
 *    other positions. Any other point in the controlPoint array
 *    must fall on this geodesic.
 * 
 * <p>Java class for GeodesicType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GeodesicType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/gml}GeodesicStringType"&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeodesicType")
public class GeodesicType
    extends GeodesicStringType
{


}
