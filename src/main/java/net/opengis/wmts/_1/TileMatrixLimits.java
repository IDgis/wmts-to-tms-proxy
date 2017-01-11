//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.27 at 02:31:50 PM CET 
//


package net.opengis.wmts._1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="TileMatrix" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="MinTileRow" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="MaxTileRow" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="MinTileCol" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="MaxTileCol" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
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
    "tileMatrix",
    "minTileRow",
    "maxTileRow",
    "minTileCol",
    "maxTileCol"
})
@XmlRootElement(name = "TileMatrixLimits")
public class TileMatrixLimits {

    @XmlElement(name = "TileMatrix", required = true)
    protected String tileMatrix;
    @XmlElement(name = "MinTileRow", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger minTileRow;
    @XmlElement(name = "MaxTileRow", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxTileRow;
    @XmlElement(name = "MinTileCol", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger minTileCol;
    @XmlElement(name = "MaxTileCol", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxTileCol;

    /**
     * Gets the value of the tileMatrix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTileMatrix() {
        return tileMatrix;
    }

    /**
     * Sets the value of the tileMatrix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTileMatrix(String value) {
        this.tileMatrix = value;
    }

    public boolean isSetTileMatrix() {
        return (this.tileMatrix!= null);
    }

    /**
     * Gets the value of the minTileRow property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinTileRow() {
        return minTileRow;
    }

    /**
     * Sets the value of the minTileRow property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinTileRow(BigInteger value) {
        this.minTileRow = value;
    }

    public boolean isSetMinTileRow() {
        return (this.minTileRow!= null);
    }

    /**
     * Gets the value of the maxTileRow property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxTileRow() {
        return maxTileRow;
    }

    /**
     * Sets the value of the maxTileRow property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxTileRow(BigInteger value) {
        this.maxTileRow = value;
    }

    public boolean isSetMaxTileRow() {
        return (this.maxTileRow!= null);
    }

    /**
     * Gets the value of the minTileCol property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinTileCol() {
        return minTileCol;
    }

    /**
     * Sets the value of the minTileCol property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinTileCol(BigInteger value) {
        this.minTileCol = value;
    }

    public boolean isSetMinTileCol() {
        return (this.minTileCol!= null);
    }

    /**
     * Gets the value of the maxTileCol property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxTileCol() {
        return maxTileCol;
    }

    /**
     * Sets the value of the maxTileCol property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxTileCol(BigInteger value) {
        this.maxTileCol = value;
    }

    public boolean isSetMaxTileCol() {
        return (this.maxTileCol!= null);
    }

}
