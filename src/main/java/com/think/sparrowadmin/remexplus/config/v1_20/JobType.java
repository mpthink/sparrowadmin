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
 * <p>Java class for jobType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="jobType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configPath" type="{http://www.dell.com/storage/test/remex/submit/config/v1_20}filePathType" minOccurs="0"/>
 *         &lt;element name="testRunsPath" type="{http://www.dell.com/storage/test/remex/submit/config/v1_20}filePathType" minOccurs="0"/>
 *         &lt;element name="pomPath" type="{http://www.dell.com/storage/test/remex/submit/config/v1_20}filePathType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="class" use="required" type="{http://www.dell.com/storage/test/remex/submit/config/v1_20}qualifiedIdentifierType" />
 *       &lt;attribute name="failAndContinue" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="submitToAll" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="priority" type="{http://www.dell.com/storage/test/remex/submit/config/v1_20}jobPriorityType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "jobType", propOrder = {
    "configPath",
    "testRunsPath",
    "pomPath"
})
public class JobType extends com.think.sparrowadmin.remexplus.config.v1_10.JobType {

    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String configPath;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String testRunsPath;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pomPath;
    @XmlAttribute(name = "class", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String clazz;
    @XmlAttribute(name = "failAndContinue")
    protected Boolean failAndContinue;
    @XmlAttribute(name = "submitToAll")
    protected Boolean submitToAll;
    @XmlAttribute(name = "priority")
    protected Integer priority;

    /**
     * Gets the value of the configPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigPath() {
        return configPath;
    }

    /**
     * Sets the value of the configPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigPath(String value) {
        this.configPath = value;
    }

    /**
     * Gets the value of the testRunsPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestRunsPath() {
        return testRunsPath;
    }

    /**
     * Sets the value of the testRunsPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestRunsPath(String value) {
        this.testRunsPath = value;
    }

    /**
     * Gets the value of the pomPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPomPath() {
        return pomPath;
    }

    /**
     * Sets the value of the pomPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPomPath(String value) {
        this.pomPath = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the failAndContinue property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFailAndContinue() {
        return failAndContinue;
    }

    /**
     * Sets the value of the failAndContinue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFailAndContinue(Boolean value) {
        this.failAndContinue = value;
    }

    /**
     * Gets the value of the submitToAll property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSubmitToAll() {
        return submitToAll;
    }

    /**
     * Sets the value of the submitToAll property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSubmitToAll(Boolean value) {
        this.submitToAll = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPriority(Integer value) {
        this.priority = value;
    }

}
