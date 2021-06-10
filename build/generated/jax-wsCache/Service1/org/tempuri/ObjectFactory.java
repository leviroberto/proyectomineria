
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.datacontract.schemas._2004._07.proyectowebservice.CompositeType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri package. 
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

    private final static QName _GenerarNotaCreditoEmpresa_QNAME = new QName("http://tempuri.org/", "empresa");
    private final static QName _DarDeBajaListaMotivoBaj_QNAME = new QName("http://tempuri.org/", "listaMotivoBaj");
    private final static QName _DarDeBajaListaIdFactura_QNAME = new QName("http://tempuri.org/", "listaIdFactura");
    private final static QName _ConvertiToLetterResponseConvertiToLetterResult_QNAME = new QName("http://tempuri.org/", "convertiToLetterResult");
    private final static QName _GetDataUsingDataContractResponseGetDataUsingDataContractResult_QNAME = new QName("http://tempuri.org/", "GetDataUsingDataContractResult");
    private final static QName _GetDataResponseGetDataResult_QNAME = new QName("http://tempuri.org/", "GetDataResult");
    private final static QName _GetDataUsingDataContractComposite_QNAME = new QName("http://tempuri.org/", "composite");
    private final static QName _ConvertiToLetterNumero_QNAME = new QName("http://tempuri.org/", "numero");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DarDeBaja }
     * 
     */
    public DarDeBaja createDarDeBaja() {
        return new DarDeBaja();
    }

    /**
     * Create an instance of {@link DarDeBajaResponse }
     * 
     */
    public DarDeBajaResponse createDarDeBajaResponse() {
        return new DarDeBajaResponse();
    }

    /**
     * Create an instance of {@link GenerarFactura }
     * 
     */
    public GenerarFactura createGenerarFactura() {
        return new GenerarFactura();
    }

    /**
     * Create an instance of {@link GenerarNotaCredito }
     * 
     */
    public GenerarNotaCredito createGenerarNotaCredito() {
        return new GenerarNotaCredito();
    }

    /**
     * Create an instance of {@link ConvertiToLetter }
     * 
     */
    public ConvertiToLetter createConvertiToLetter() {
        return new ConvertiToLetter();
    }

    /**
     * Create an instance of {@link GetDataUsingDataContractResponse }
     * 
     */
    public GetDataUsingDataContractResponse createGetDataUsingDataContractResponse() {
        return new GetDataUsingDataContractResponse();
    }

    /**
     * Create an instance of {@link DarDeBajaNotaCreditoResponse }
     * 
     */
    public DarDeBajaNotaCreditoResponse createDarDeBajaNotaCreditoResponse() {
        return new DarDeBajaNotaCreditoResponse();
    }

    /**
     * Create an instance of {@link DarDeBajaNotaCredito }
     * 
     */
    public DarDeBajaNotaCredito createDarDeBajaNotaCredito() {
        return new DarDeBajaNotaCredito();
    }

    /**
     * Create an instance of {@link GenerarNotaCreditoResponse }
     * 
     */
    public GenerarNotaCreditoResponse createGenerarNotaCreditoResponse() {
        return new GenerarNotaCreditoResponse();
    }

    /**
     * Create an instance of {@link GenerarNotaDebitoResponse }
     * 
     */
    public GenerarNotaDebitoResponse createGenerarNotaDebitoResponse() {
        return new GenerarNotaDebitoResponse();
    }

    /**
     * Create an instance of {@link GetDataUsingDataContract }
     * 
     */
    public GetDataUsingDataContract createGetDataUsingDataContract() {
        return new GetDataUsingDataContract();
    }

    /**
     * Create an instance of {@link GenerarFacturaResponse }
     * 
     */
    public GenerarFacturaResponse createGenerarFacturaResponse() {
        return new GenerarFacturaResponse();
    }

    /**
     * Create an instance of {@link GetData }
     * 
     */
    public GetData createGetData() {
        return new GetData();
    }

    /**
     * Create an instance of {@link DarDeBajaNotaDebito }
     * 
     */
    public DarDeBajaNotaDebito createDarDeBajaNotaDebito() {
        return new DarDeBajaNotaDebito();
    }

    /**
     * Create an instance of {@link DarDeBajaNotaDebitoResponse }
     * 
     */
    public DarDeBajaNotaDebitoResponse createDarDeBajaNotaDebitoResponse() {
        return new DarDeBajaNotaDebitoResponse();
    }

    /**
     * Create an instance of {@link ConvertiToLetterResponse }
     * 
     */
    public ConvertiToLetterResponse createConvertiToLetterResponse() {
        return new ConvertiToLetterResponse();
    }

    /**
     * Create an instance of {@link GetDataResponse }
     * 
     */
    public GetDataResponse createGetDataResponse() {
        return new GetDataResponse();
    }

    /**
     * Create an instance of {@link GenerarNotaDebito }
     * 
     */
    public GenerarNotaDebito createGenerarNotaDebito() {
        return new GenerarNotaDebito();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "empresa", scope = GenerarNotaCredito.class)
    public JAXBElement<String> createGenerarNotaCreditoEmpresa(String value) {
        return new JAXBElement<String>(_GenerarNotaCreditoEmpresa_QNAME, String.class, GenerarNotaCredito.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "empresa", scope = GenerarNotaDebito.class)
    public JAXBElement<String> createGenerarNotaDebitoEmpresa(String value) {
        return new JAXBElement<String>(_GenerarNotaCreditoEmpresa_QNAME, String.class, GenerarNotaDebito.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "listaMotivoBaj", scope = DarDeBaja.class)
    public JAXBElement<String> createDarDeBajaListaMotivoBaj(String value) {
        return new JAXBElement<String>(_DarDeBajaListaMotivoBaj_QNAME, String.class, DarDeBaja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "listaIdFactura", scope = DarDeBaja.class)
    public JAXBElement<String> createDarDeBajaListaIdFactura(String value) {
        return new JAXBElement<String>(_DarDeBajaListaIdFactura_QNAME, String.class, DarDeBaja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "empresa", scope = DarDeBaja.class)
    public JAXBElement<String> createDarDeBajaEmpresa(String value) {
        return new JAXBElement<String>(_GenerarNotaCreditoEmpresa_QNAME, String.class, DarDeBaja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "convertiToLetterResult", scope = ConvertiToLetterResponse.class)
    public JAXBElement<String> createConvertiToLetterResponseConvertiToLetterResult(String value) {
        return new JAXBElement<String>(_ConvertiToLetterResponseConvertiToLetterResult_QNAME, String.class, ConvertiToLetterResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "empresa", scope = GenerarFactura.class)
    public JAXBElement<String> createGenerarFacturaEmpresa(String value) {
        return new JAXBElement<String>(_GenerarNotaCreditoEmpresa_QNAME, String.class, GenerarFactura.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompositeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetDataUsingDataContractResult", scope = GetDataUsingDataContractResponse.class)
    public JAXBElement<CompositeType> createGetDataUsingDataContractResponseGetDataUsingDataContractResult(CompositeType value) {
        return new JAXBElement<CompositeType>(_GetDataUsingDataContractResponseGetDataUsingDataContractResult_QNAME, CompositeType.class, GetDataUsingDataContractResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "listaMotivoBaj", scope = DarDeBajaNotaDebito.class)
    public JAXBElement<String> createDarDeBajaNotaDebitoListaMotivoBaj(String value) {
        return new JAXBElement<String>(_DarDeBajaListaMotivoBaj_QNAME, String.class, DarDeBajaNotaDebito.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "listaIdFactura", scope = DarDeBajaNotaDebito.class)
    public JAXBElement<String> createDarDeBajaNotaDebitoListaIdFactura(String value) {
        return new JAXBElement<String>(_DarDeBajaListaIdFactura_QNAME, String.class, DarDeBajaNotaDebito.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "empresa", scope = DarDeBajaNotaDebito.class)
    public JAXBElement<String> createDarDeBajaNotaDebitoEmpresa(String value) {
        return new JAXBElement<String>(_GenerarNotaCreditoEmpresa_QNAME, String.class, DarDeBajaNotaDebito.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetDataResult", scope = GetDataResponse.class)
    public JAXBElement<String> createGetDataResponseGetDataResult(String value) {
        return new JAXBElement<String>(_GetDataResponseGetDataResult_QNAME, String.class, GetDataResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompositeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "composite", scope = GetDataUsingDataContract.class)
    public JAXBElement<CompositeType> createGetDataUsingDataContractComposite(CompositeType value) {
        return new JAXBElement<CompositeType>(_GetDataUsingDataContractComposite_QNAME, CompositeType.class, GetDataUsingDataContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "listaMotivoBaj", scope = DarDeBajaNotaCredito.class)
    public JAXBElement<String> createDarDeBajaNotaCreditoListaMotivoBaj(String value) {
        return new JAXBElement<String>(_DarDeBajaListaMotivoBaj_QNAME, String.class, DarDeBajaNotaCredito.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "listaIdFactura", scope = DarDeBajaNotaCredito.class)
    public JAXBElement<String> createDarDeBajaNotaCreditoListaIdFactura(String value) {
        return new JAXBElement<String>(_DarDeBajaListaIdFactura_QNAME, String.class, DarDeBajaNotaCredito.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "empresa", scope = DarDeBajaNotaCredito.class)
    public JAXBElement<String> createDarDeBajaNotaCreditoEmpresa(String value) {
        return new JAXBElement<String>(_GenerarNotaCreditoEmpresa_QNAME, String.class, DarDeBajaNotaCredito.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "numero", scope = ConvertiToLetter.class)
    public JAXBElement<String> createConvertiToLetterNumero(String value) {
        return new JAXBElement<String>(_ConvertiToLetterNumero_QNAME, String.class, ConvertiToLetter.class, value);
    }

}
