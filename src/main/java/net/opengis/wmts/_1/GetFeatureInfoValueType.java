//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.27 at 02:31:50 PM CET 
//


package net.opengis.wmts._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetFeatureInfoValueType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GetFeatureInfoValueType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="GetFeatureInfo"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "GetFeatureInfoValueType")
@XmlEnum
public enum GetFeatureInfoValueType {

    @XmlEnumValue("GetFeatureInfo")
    GET_FEATURE_INFO("GetFeatureInfo");
    private final String value;

    GetFeatureInfoValueType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GetFeatureInfoValueType fromValue(String v) {
        for (GetFeatureInfoValueType c: GetFeatureInfoValueType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
