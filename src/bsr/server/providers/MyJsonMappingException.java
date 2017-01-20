package bsr.server.providers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class MyJsonMappingException implements ExceptionMapper<JsonMappingException> {
    @Override
    public Response toResponse(JsonMappingException e) {
        Error error = new Error("Bad field: " + e.getPath().get(0).getFieldName());
        String json = new Gson().toJson(error);
        return Response.status(Response.Status.BAD_REQUEST).entity(json).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
