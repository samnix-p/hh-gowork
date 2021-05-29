package ru.gowork.resource;


import ru.gowork.service.AnswerService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AnswerResource {
    private final AnswerService service;

    public AnswerResource(AnswerService service) {
        this.service = service;
    }

    @Path("/session/{session}/answer/")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response saveAnswer(@PathParam("session") String session,
                              String answer) {
        //return service.saveAnswerGetExplanation(session, answer);
        return service.saveAnswerGetExplanation(session, answer)
                .map(explanation -> Response.ok(explanation).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }
}
