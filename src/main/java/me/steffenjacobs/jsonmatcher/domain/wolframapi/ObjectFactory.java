//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.11.23 um 06:16:42 PM CET 
//


package me.steffenjacobs.jsonmatcher.domain.wolframapi;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the me.steffenjacobs.jsonmatcher.domain.wolframapi package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: me.steffenjacobs.jsonmatcher.domain.wolframapi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Queryresult }
     * 
     */
    public Queryresult createQueryresult() {
        return new Queryresult();
    }

    /**
     * Create an instance of {@link Queryresult.Sources }
     * 
     */
    public Queryresult.Sources createQueryresultSources() {
        return new Queryresult.Sources();
    }

    /**
     * Create an instance of {@link Queryresult.Pod }
     * 
     */
    public Queryresult.Pod createQueryresultPod() {
        return new Queryresult.Pod();
    }

    /**
     * Create an instance of {@link Queryresult.Pod.Subpod }
     * 
     */
    public Queryresult.Pod.Subpod createQueryresultPodSubpod() {
        return new Queryresult.Pod.Subpod();
    }

    /**
     * Create an instance of {@link Queryresult.Sources.Source }
     * 
     */
    public Queryresult.Sources.Source createQueryresultSourcesSource() {
        return new Queryresult.Sources.Source();
    }

    /**
     * Create an instance of {@link Queryresult.Pod.Subpod.Img }
     * 
     */
    public Queryresult.Pod.Subpod.Img createQueryresultPodSubpodImg() {
        return new Queryresult.Pod.Subpod.Img();
    }

}
