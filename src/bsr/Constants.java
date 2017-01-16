package bsr;

import bsr.server.soap.BankService;

/**
 * Created by Maciej on 2017-01-06.
 */
public class Constants {

    public static final String EXTERNAL_BANK_ADDRESS = "8088";

    public static final String SOAP_PORT = "8081";
    public static final int REST_PORT = 8080;

    public static final String BANK_ENPDOINT = "http://localhost:" + SOAP_PORT + "/ws/bank";
    public static final String BANK_ENDPOINT_WSDL = BANK_ENPDOINT + "?wsdl";

    public static final String EXTERNAL = "http://0.0.0.0/";

    public static final String NAMESPACE_URI = "http://soap.server.bsr/";
    public static final String PERSON_SERVICE_NAME = BankService.class.getSimpleName() + "Service";

    public static final String BANK_CREDENTIALS = "admin:admin";
    public static final long UNDEFINED = -1;
    public static final String UTF_8 = "UTF-8";
}
