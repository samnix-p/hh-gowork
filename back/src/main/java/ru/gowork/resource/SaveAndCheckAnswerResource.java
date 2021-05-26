package ru.gowork.resource;

import ru.gowork.service.SaveAndCheckAnswerService;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// 24.05.2021   delete a !!!!!!!!!
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class SaveAndCheckAnswerResource {
    private final SaveAndCheckAnswerService service;

    public SaveAndCheckAnswerResource(SaveAndCheckAnswerService service) {
        this.service = service;
    }

    @Path("/session/{session_token}/ans/{ans}")
    @PUT
    public Object saveAndGetAnswer(@PathParam("session_token") String session,
                                   @PathParam("ans") Object ans) {
        System.out.println("Session ========================================================== " + session);

        return service.getThis(session, ans);
    }
}
