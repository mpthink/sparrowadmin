//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.10 at 03:10:08 PM CST 
//


package com.think.sparrowadmin.remexplus.config.v1_10;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for jobGroupsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="jobGroupsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="jobGroup" type="{http://www.dell.com/storage/test/remex/submit/config/v1_10}jobGroupType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="groupId" type="{http://www.dell.com/storage/test/remex/submit/config/v1_10}identifierType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "jobGroupsType", propOrder = {
    "jobGroup"
})
public class JobGroupsType {

    @XmlElement(required = true)
    protected List<JobGroupType> jobGroup;
    @XmlAttribute(name = "groupId")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String groupId;

    /**
     * Gets the value of the jobGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jobGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJobGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JobGroupType }
     * 
     * 
     */
    public List<JobGroupType> getJobGroup() {
        if (jobGroup == null) {
            jobGroup = new ArrayList<JobGroupType>();
        }
        return this.jobGroup;
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
