//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.11.23 um 06:16:42 PM CET 
//


package me.steffenjacobs.jsonmatcher.domain.wolframapi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pod" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="subpod" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="img">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="alt" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                                     &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="plaintext" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="scanner" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="position" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="error" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="numsubpods" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="primary" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="sources">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="source">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="text" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="count" type="{http://www.w3.org/2001/XMLSchema}int" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="success" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="error" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="numpods" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="datatypes" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="timedout" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="timedoutpods" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="timing" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="parsetiming" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="parsetimedout" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="recalculate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="host" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="server" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="related" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pod",
    "sources"
})
@XmlRootElement(name = "queryresult")
public class Queryresult {

    @XmlElement(required = true)
    protected List<Queryresult.Pod> pod;
    @XmlElement(required = true)
    protected Queryresult.Sources sources;
    @XmlAttribute(name = "success")
    protected Boolean success;
    @XmlAttribute(name = "error")
    protected Boolean error;
    @XmlAttribute(name = "numpods")
    protected Integer numpods;
    @XmlAttribute(name = "datatypes")
    protected String datatypes;
    @XmlAttribute(name = "timedout")
    protected String timedout;
    @XmlAttribute(name = "timedoutpods")
    protected String timedoutpods;
    @XmlAttribute(name = "timing")
    protected BigDecimal timing;
    @XmlAttribute(name = "parsetiming")
    protected BigDecimal parsetiming;
    @XmlAttribute(name = "parsetimedout")
    protected Boolean parsetimedout;
    @XmlAttribute(name = "recalculate")
    protected String recalculate;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "host")
    protected String host;
    @XmlAttribute(name = "server")
    protected Integer server;
    @XmlAttribute(name = "related")
    protected String related;
    @XmlAttribute(name = "version")
    protected BigDecimal version;

    /**
     * Gets the value of the pod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Queryresult.Pod }
     * 
     * 
     */
    public List<Queryresult.Pod> getPod() {
        if (pod == null) {
            pod = new ArrayList<Queryresult.Pod>();
        }
        return this.pod;
    }

    /**
     * Ruft den Wert der sources-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Queryresult.Sources }
     *     
     */
    public Queryresult.Sources getSources() {
        return sources;
    }

    /**
     * Legt den Wert der sources-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Queryresult.Sources }
     *     
     */
    public void setSources(Queryresult.Sources value) {
        this.sources = value;
    }

    /**
     * Ruft den Wert der success-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSuccess() {
        return success;
    }

    /**
     * Legt den Wert der success-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSuccess(Boolean value) {
        this.success = value;
    }

    /**
     * Ruft den Wert der error-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isError() {
        return error;
    }

    /**
     * Legt den Wert der error-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setError(Boolean value) {
        this.error = value;
    }

    /**
     * Ruft den Wert der numpods-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumpods() {
        return numpods;
    }

    /**
     * Legt den Wert der numpods-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumpods(Integer value) {
        this.numpods = value;
    }

    /**
     * Ruft den Wert der datatypes-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatatypes() {
        return datatypes;
    }

    /**
     * Legt den Wert der datatypes-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatatypes(String value) {
        this.datatypes = value;
    }

    /**
     * Ruft den Wert der timedout-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimedout() {
        return timedout;
    }

    /**
     * Legt den Wert der timedout-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimedout(String value) {
        this.timedout = value;
    }

    /**
     * Ruft den Wert der timedoutpods-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimedoutpods() {
        return timedoutpods;
    }

    /**
     * Legt den Wert der timedoutpods-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimedoutpods(String value) {
        this.timedoutpods = value;
    }

    /**
     * Ruft den Wert der timing-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTiming() {
        return timing;
    }

    /**
     * Legt den Wert der timing-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTiming(BigDecimal value) {
        this.timing = value;
    }

    /**
     * Ruft den Wert der parsetiming-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getParsetiming() {
        return parsetiming;
    }

    /**
     * Legt den Wert der parsetiming-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setParsetiming(BigDecimal value) {
        this.parsetiming = value;
    }

    /**
     * Ruft den Wert der parsetimedout-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isParsetimedout() {
        return parsetimedout;
    }

    /**
     * Legt den Wert der parsetimedout-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setParsetimedout(Boolean value) {
        this.parsetimedout = value;
    }

    /**
     * Ruft den Wert der recalculate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecalculate() {
        return recalculate;
    }

    /**
     * Legt den Wert der recalculate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecalculate(String value) {
        this.recalculate = value;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der host-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * Legt den Wert der host-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Ruft den Wert der server-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getServer() {
        return server;
    }

    /**
     * Legt den Wert der server-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setServer(Integer value) {
        this.server = value;
    }

    /**
     * Ruft den Wert der related-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelated() {
        return related;
    }

    /**
     * Legt den Wert der related-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelated(String value) {
        this.related = value;
    }

    /**
     * Ruft den Wert der version-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVersion() {
        return version;
    }

    /**
     * Legt den Wert der version-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVersion(BigDecimal value) {
        this.version = value;
    }


    /**
     * <p>Java-Klasse f�r anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="subpod" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="img">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="alt" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                           &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="plaintext" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="scanner" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="position" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="error" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="numsubpods" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="primary" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "subpod"
    })
    public static class Pod {

        protected List<Queryresult.Pod.Subpod> subpod;
        @XmlAttribute(name = "title")
        protected String title;
        @XmlAttribute(name = "scanner")
        protected String scanner;
        @XmlAttribute(name = "id")
        protected String id;
        @XmlAttribute(name = "position")
        protected Integer position;
        @XmlAttribute(name = "error")
        protected Boolean error;
        @XmlAttribute(name = "numsubpods")
        protected Integer numsubpods;
        @XmlAttribute(name = "primary")
        protected String primary;

        /**
         * Gets the value of the subpod property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the subpod property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSubpod().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Queryresult.Pod.Subpod }
         * 
         * 
         */
        public List<Queryresult.Pod.Subpod> getSubpod() {
            if (subpod == null) {
                subpod = new ArrayList<Queryresult.Pod.Subpod>();
            }
            return this.subpod;
        }

        /**
         * Ruft den Wert der title-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTitle() {
            return title;
        }

        /**
         * Legt den Wert der title-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTitle(String value) {
            this.title = value;
        }

        /**
         * Ruft den Wert der scanner-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getScanner() {
            return scanner;
        }

        /**
         * Legt den Wert der scanner-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setScanner(String value) {
            this.scanner = value;
        }

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

        /**
         * Ruft den Wert der position-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getPosition() {
            return position;
        }

        /**
         * Legt den Wert der position-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setPosition(Integer value) {
            this.position = value;
        }

        /**
         * Ruft den Wert der error-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isError() {
            return error;
        }

        /**
         * Legt den Wert der error-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setError(Boolean value) {
            this.error = value;
        }

        /**
         * Ruft den Wert der numsubpods-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getNumsubpods() {
            return numsubpods;
        }

        /**
         * Legt den Wert der numsubpods-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setNumsubpods(Integer value) {
            this.numsubpods = value;
        }

        /**
         * Ruft den Wert der primary-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrimary() {
            return primary;
        }

        /**
         * Legt den Wert der primary-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrimary(String value) {
            this.primary = value;
        }


        /**
         * <p>Java-Klasse f�r anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="img">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="alt" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}int" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="plaintext" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "img",
            "plaintext"
        })
        public static class Subpod {

            @XmlElement(required = true)
            protected Queryresult.Pod.Subpod.Img img;
            @XmlElement(required = true)
            protected String plaintext;
            @XmlAttribute(name = "title")
            protected String title;

            /**
             * Ruft den Wert der img-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link Queryresult.Pod.Subpod.Img }
             *     
             */
            public Queryresult.Pod.Subpod.Img getImg() {
                return img;
            }

            /**
             * Legt den Wert der img-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Queryresult.Pod.Subpod.Img }
             *     
             */
            public void setImg(Queryresult.Pod.Subpod.Img value) {
                this.img = value;
            }

            /**
             * Ruft den Wert der plaintext-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPlaintext() {
                return plaintext;
            }

            /**
             * Legt den Wert der plaintext-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPlaintext(String value) {
                this.plaintext = value;
            }

            /**
             * Ruft den Wert der title-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTitle() {
                return title;
            }

            /**
             * Legt den Wert der title-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTitle(String value) {
                this.title = value;
            }


            /**
             * <p>Java-Klasse f�r anonymous complex type.
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="alt" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}int" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Img {

                @XmlAttribute(name = "src")
                protected String src;
                @XmlAttribute(name = "alt")
                protected String alt;
                @XmlAttribute(name = "title")
                protected String title;
                @XmlAttribute(name = "width")
                protected Integer width;
                @XmlAttribute(name = "height")
                protected Integer height;

                /**
                 * Ruft den Wert der src-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSrc() {
                    return src;
                }

                /**
                 * Legt den Wert der src-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSrc(String value) {
                    this.src = value;
                }

                /**
                 * Ruft den Wert der alt-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getAlt() {
                    return alt;
                }

                /**
                 * Legt den Wert der alt-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setAlt(String value) {
                    this.alt = value;
                }

                /**
                 * Ruft den Wert der title-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTitle() {
                    return title;
                }

                /**
                 * Legt den Wert der title-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTitle(String value) {
                    this.title = value;
                }

                /**
                 * Ruft den Wert der width-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getWidth() {
                    return width;
                }

                /**
                 * Legt den Wert der width-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setWidth(Integer value) {
                    this.width = value;
                }

                /**
                 * Ruft den Wert der height-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getHeight() {
                    return height;
                }

                /**
                 * Legt den Wert der height-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setHeight(Integer value) {
                    this.height = value;
                }

            }

        }

    }


    /**
     * <p>Java-Klasse f�r anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="source">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="text" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="count" type="{http://www.w3.org/2001/XMLSchema}int" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "source"
    })
    public static class Sources {

        @XmlElement(required = true)
        protected Queryresult.Sources.Source source;
        @XmlAttribute(name = "count")
        protected Integer count;

        /**
         * Ruft den Wert der source-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Queryresult.Sources.Source }
         *     
         */
        public Queryresult.Sources.Source getSource() {
            return source;
        }

        /**
         * Legt den Wert der source-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Queryresult.Sources.Source }
         *     
         */
        public void setSource(Queryresult.Sources.Source value) {
            this.source = value;
        }

        /**
         * Ruft den Wert der count-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getCount() {
            return count;
        }

        /**
         * Legt den Wert der count-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setCount(Integer value) {
            this.count = value;
        }


        /**
         * <p>Java-Klasse f�r anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="text" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Source {

            @XmlAttribute(name = "url")
            protected String url;
            @XmlAttribute(name = "text")
            protected String text;

            /**
             * Ruft den Wert der url-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUrl() {
                return url;
            }

            /**
             * Legt den Wert der url-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUrl(String value) {
                this.url = value;
            }

            /**
             * Ruft den Wert der text-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getText() {
                return text;
            }

            /**
             * Legt den Wert der text-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setText(String value) {
                this.text = value;
            }

        }

    }

}
