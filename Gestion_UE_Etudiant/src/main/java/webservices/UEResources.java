package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/UE")
public class UEResources {
    private static final UniteEnseignementBusiness UE=new UniteEnseignementBusiness();
    @Path("/add")
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
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListUE()
    {

       return Response.status(200).entity(UE.getListeUE()).build();


    }
    @Path("/listUEBySemester")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListUEBySemester(@QueryParam("semestre") Integer semestre)
    {
        if(semestre!=null)
        {
            return Response.status(200).entity(UE.getUEBySemestre(semestre)).build();
        }
        else return Response.status(400).entity("Semestre Not Found").build();
    }

    @Path("/listUEByCode")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListUEByCode(@QueryParam("code") Integer code)
    {
        if(code!=null)
        {
            return Response.status(200).entity(UE.getUEByCode(code)).build();
        }
        else return Response.status(400).entity("Code Not Found").build();
    }
    @Path("/Update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(@PathParam("id") int id,UniteEnseignement u)
    {
        if(u==null)
        {
            return Response.status(404).entity("UE Not Found").build();
        }
        else
        {
            u.setCode(id);
            boolean result =UE.updateUniteEnseignement(id,u);
            if(result)
            {
                return Response.status(200).entity("UE Updated").build();
            }
            else
            {
                return Response.status(404).entity("UE Not Found").build();
            }
        }

    }
    @Path("/Delete/{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") int id)
    {
        boolean result =UE.deleteUniteEnseignement(id);
        if (result)
        {
            return Response.status(200).entity("UE Deleted").build();
        }
        else
        {
            return Response.status(404).entity("UE Not Found").build();
        }
    }

}
