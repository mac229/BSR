package bsr;

import bsr.server.soap.BankService;

/**
 * Created by Maciej on 2017-01-06.
 */
public class Constants {

    public static final String BANK_ENPDOINT = "http://localhost:8888/ws/bank";
    public static final String BANK_ENDPOINT_WSDL = BANK_ENPDOINT + "?wsdl";

    public static final String LOCALHOST = "http://localhost/";
    public static final int REST_PORT = 8088;

    public static final String NAMESPACE_URI = "http://soap.server.bsr/";
    public static final String PERSON_SERVICE_NAME = BankService.class.getSimpleName() + "Service";

    public static final String BANK_CREDENTIALS = "admin:admin";
    public static final long UNDEFINED = -1;
}
