package bsr.server.providers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyJsonProcessingException implements ExceptionMapper<JsonProcessingException> {
    @Override
    public Response toResponse(JsonProcessingException e) {
        Error error = new Error("Bad JSON");
        String json = new Gson().toJson(error);
        return Response.status(Response.Status.BAD_REQUEST).entity(json).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
