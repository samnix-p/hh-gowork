package ru.gowork.resource;


import ru.gowork.config.AnonymousAllowed;
import ru.gowork.service.AnswerService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AnswerResource {
    private final AnswerService service;

    public AnswerResource(AnswerService service) {
        this.service = service;
    }

    @Path("/session/{session}/answer/{answer}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @AnonymousAllowed
    @POST
    public Object saveAnswer(@PathParam("session") String session,
                             @PathParam("answer") String answer) {
        return service.saveAnswerGetExplanation(session, answer);
    }
}
