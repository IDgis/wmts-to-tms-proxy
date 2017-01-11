//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.11 at 11:05:40 AM CET 
//


package net.opengis.wmts._1;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TileMatrixSet" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wmts/1.0}TileMatrixSetLimits" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tileMatrixSet",
    "tileMatrixSetLimits"
})
@XmlRootElement(name = "TileMatrixSetLink")
public class TileMatrixSetLink {

    @XmlElement(name = "TileMatrixSet", required = true)
    protected String tileMatrixSet;
    @XmlElement(name = "TileMatrixSetLimits")
    protected TileMatrixSetLimits tileMatrixSetLimits;

    /**
     * Gets the value of the tileMatrixSet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTileMatrixSet() {
        return tileMatrixSet;
    }

    /**
     * Sets the value of the tileMatrixSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTileMatrixSet(String value) {
        this.tileMatrixSet = value;
    }

    public boolean isSetTileMatrixSet() {
        return (this.tileMatrixSet!= null);
    }

    /**
     * Indices limits for this tileMatrixSet. The absence of this 
     * 						element means that tile row and tile col indices are only limited by 0 
     * 						and the corresponding tileMatrixSet maximum definitions.
     * 
     * @return
     *     possible object is
     *     {@link TileMatrixSetLimits }
     *     
     */
    public TileMatrixSetLimits getTileMatrixSetLimits() {
        return tileMatrixSetLimits;
    }

    /**
     * Sets the value of the tileMatrixSetLimits property.
     * 
     * @param value
     *     allowed object is
     *     {@link TileMatrixSetLimits }
     *     
     */
    public void setTileMatrixSetLimits(TileMatrixSetLimits value) {
        this.tileMatrixSetLimits = value;
    }

    public boolean isSetTileMatrixSetLimits() {
        return (this.tileMatrixSetLimits!= null);
    }

}
