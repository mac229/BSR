package bsr;

import bsr.server.PersonServiceImpl;

/**
 * Created by Maciej on 2017-01-06.
 */
public class Constants {

    public static final String PERSON_ENPDOINT = "http://localhost:8888/ws/person";
    public static final String PERSON_ENDPOINT_WSDL = PERSON_ENPDOINT + "?wsdl";

    public static final String NAMESPACE_URI = "http://server.bsr/";
    public static final String PERSON_SERVICE_NAME = PersonServiceImpl.class.getSimpleName() + "Service";
}
