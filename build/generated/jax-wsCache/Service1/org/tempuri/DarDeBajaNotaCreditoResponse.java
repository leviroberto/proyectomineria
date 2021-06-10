
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="darDeBajaNotaCreditoResult" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "darDeBajaNotaCreditoResult"
})
@XmlRootElement(name = "darDeBajaNotaCreditoResponse")
public class DarDeBajaNotaCreditoResponse {

    protected Boolean darDeBajaNotaCreditoResult;

    /**
     * Obtiene el valor de la propiedad darDeBajaNotaCreditoResult.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDarDeBajaNotaCreditoResult() {
        return darDeBajaNotaCreditoResult;
    }

    /**
     * Define el valor de la propiedad darDeBajaNotaCreditoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDarDeBajaNotaCreditoResult(Boolean value) {
        this.darDeBajaNotaCreditoResult = value;
    }

}
