//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.15 at 09:22:55 AM CDT 
//


package com.think.sparrowadmin.remexplus.config.v1_20;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for submitGroupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="submitGroupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="poolGroupRef" use="required" type="{http://www.dell.com/storage/test/remex/submit/config/v1_20}qualifiedIdentifierType" />
 *       &lt;attribute name="jobGroupRef" use="required" type="{http://www.dell.com/storage/test/remex/submit/config/v1_20}qualifiedIdentifierType" />
 *       &lt;attribute name="groupId" type="{http://www.dell.com/storage/test/remex/submit/config/v1_20}identifierType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "submitGroupType")
public class SubmitGroupType {

    @XmlAttribute(name = "poolGroupRef", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String poolGroupRef;
    @XmlAttribute(name = "jobGroupRef", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String jobGroupRef;
    @XmlAttribute(name = "groupId")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String groupId;

    /**
     * Gets the value of the poolGroupRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoolGroupRef() {
        return poolGroupRef;
    }

    /**
     * Sets the value of the poolGroupRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoolGroupRef(String value) {
        this.poolGroupRef = value;
    }

    /**
     * Gets the value of the jobGroupRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobGroupRef() {
        return jobGroupRef;
    }

    /**
     * Sets the value of the jobGroupRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobGroupRef(String value) {
        this.jobGroupRef = value;
    }

    /**
     * Gets the value of the groupId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets the value of the groupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupId(String value) {
        this.groupId = value;
    }

}
