package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/UE")
public class UEResources {
    private UniteEnseignementBusiness UE=new UniteEnseignementBusiness();
    @Path("/UE")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUE(UniteEnseignement u)
    {
        boolean result=UE.addUniteEnseignement(u);
        if(result)
        {
            return Response.status(201).entity("UE Created").build();
        }
        else {
            return Response.status(404).entity("UE Not Found").build();
        }

    }
    @Path("/UE")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListUE()
    {

       return Response.status(200).entity(this.UE.getListeUE()).build();


    }
    /*
    @Path("/Update/{id}")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(@PathParam("id") int id)
    {
        UniteEnseignement u=UE.getUEByCode(id);

    }*/

}
