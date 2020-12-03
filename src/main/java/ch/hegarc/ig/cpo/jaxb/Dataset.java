
package ch.hegarc.ig.cpo.jaxb;

import ch.hegarc.ig.business.Donateur;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="record" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="projet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="donateurs" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="langue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ville" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="monnaie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="somme" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                             &lt;element name="paye" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                             &lt;element name="annule" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                             &lt;element name="dateDon" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                             &lt;element name="dateVersement" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "record"
})
@XmlRootElement(name = "dataset")
public class Dataset {

    protected List<Dataset.Record> record;

    /**
     * Gets the value of the record property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the record property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dataset.Record }
     * 
     * 
     */
    public List<Dataset.Record> getRecord() {
        if (record == null) {
            record = new ArrayList<Dataset.Record>();
        }
        return this.record;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="projet" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="donateurs" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="langue" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ville" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="monnaie" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="somme" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *                   &lt;element name="paye" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                   &lt;element name="annule" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                   &lt;element name="dateDon" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *                   &lt;element name="dateVersement" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "id",
        "projet",
        "donateurs"
    })
    public static class Record {

        protected int id;
        @XmlElement(required = true)
        protected String projet;
        protected List<Donateur> donateurs;

        /**
         * Obtient la valeur de la propri�t� id.
         * 
         */
        public int getId() {
            return id;
        }

        /**
         * D�finit la valeur de la propri�t� id.
         * 
         */
        public void setId(int value) {
            this.id = value;
        }

        /**
         * Obtient la valeur de la propri�t� projet.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProjet() {
            return projet;
        }

        /**
         * D�finit la valeur de la propri�t� projet.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProjet(String value) {
            this.projet = value;
        }

        /**
         * Gets the value of the donateurs property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the donateurs property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDonateurs().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Dataset.Record.Donateurs }
         * 
         * 
         */
        public List<Donateur> getDonateurs() {
            if (donateurs == null) {
                donateurs = new ArrayList<Donateur>();
            }
            return this.donateurs;
        }


        /**
         * <p>Classe Java pour anonymous complex type.
         * 
         * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="langue" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ville" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="monnaie" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="somme" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
         *         &lt;element name="paye" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *         &lt;element name="annule" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *         &lt;element name="dateDon" type="{http://www.w3.org/2001/XMLSchema}date"/>
         *         &lt;element name="dateVersement" type="{http://www.w3.org/2001/XMLSchema}date"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "id",
            "prenom",
            "nom",
            "email",
            "langue",
            "adresse",
            "ville",
            "monnaie",
            "somme",
            "paye",
            "annule",
            "dateDon",
            "dateVersement"
        })
        public static class Donateurs {

            protected int id;
            @XmlElement(required = true)
            protected String prenom;
            @XmlElement(required = true)
            protected String nom;
            @XmlElement(required = true)
            protected String email;
            @XmlElement(required = true)
            protected String langue;
            @XmlElement(required = true)
            protected String adresse;
            @XmlElement(required = true)
            protected String ville;
            @XmlElement(required = true)
            protected String monnaie;
            @XmlElement(required = true)
            protected BigDecimal somme;
            protected boolean paye;
            protected boolean annule;
            @XmlElement(required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar dateDon;
            @XmlElement(required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar dateVersement;

            /**
             * Obtient la valeur de la propri�t� id.
             * 
             */
            public int getId() {
                return id;
            }

            /**
             * D�finit la valeur de la propri�t� id.
             * 
             */
            public void setId(int value) {
                this.id = value;
            }

            /**
             * Obtient la valeur de la propri�t� prenom.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrenom() {
                return prenom;
            }

            /**
             * D�finit la valeur de la propri�t� prenom.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrenom(String value) {
                this.prenom = value;
            }

            /**
             * Obtient la valeur de la propri�t� nom.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNom() {
                return nom;
            }

            /**
             * D�finit la valeur de la propri�t� nom.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNom(String value) {
                this.nom = value;
            }

            /**
             * Obtient la valeur de la propri�t� email.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEmail() {
                return email;
            }

            /**
             * D�finit la valeur de la propri�t� email.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEmail(String value) {
                this.email = value;
            }

            /**
             * Obtient la valeur de la propri�t� langue.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLangue() {
                return langue;
            }

            /**
             * D�finit la valeur de la propri�t� langue.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLangue(String value) {
                this.langue = value;
            }

            /**
             * Obtient la valeur de la propri�t� adresse.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAdresse() {
                return adresse;
            }

            /**
             * D�finit la valeur de la propri�t� adresse.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAdresse(String value) {
                this.adresse = value;
            }

            /**
             * Obtient la valeur de la propri�t� ville.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVille() {
                return ville;
            }

            /**
             * D�finit la valeur de la propri�t� ville.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVille(String value) {
                this.ville = value;
            }

            /**
             * Obtient la valeur de la propri�t� monnaie.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMonnaie() {
                return monnaie;
            }

            /**
             * D�finit la valeur de la propri�t� monnaie.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMonnaie(String value) {
                this.monnaie = value;
            }

            /**
             * Obtient la valeur de la propri�t� somme.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSomme() {
                return somme;
            }

            /**
             * D�finit la valeur de la propri�t� somme.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSomme(BigDecimal value) {
                this.somme = value;
            }

            /**
             * Obtient la valeur de la propri�t� paye.
             * 
             */
            public boolean isPaye() {
                return paye;
            }

            /**
             * D�finit la valeur de la propri�t� paye.
             * 
             */
            public void setPaye(boolean value) {
                this.paye = value;
            }

            /**
             * Obtient la valeur de la propri�t� annule.
             * 
             */
            public boolean isAnnule() {
                return annule;
            }

            /**
             * D�finit la valeur de la propri�t� annule.
             * 
             */
            public void setAnnule(boolean value) {
                this.annule = value;
            }

            /**
             * Obtient la valeur de la propri�t� dateDon.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDateDon() {
                return dateDon;
            }

            /**
             * D�finit la valeur de la propri�t� dateDon.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDateDon(XMLGregorianCalendar value) {
                this.dateDon = value;
            }

            /**
             * Obtient la valeur de la propri�t� dateVersement.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDateVersement() {
                return dateVersement;
            }

            /**
             * D�finit la valeur de la propri�t� dateVersement.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDateVersement(XMLGregorianCalendar value) {
                this.dateVersement = value;
            }

        }

    }

}
