package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/modules")
public class ModulesResources {
    private static final ModuleBusiness MB=new ModuleBusiness();
    private static final UniteEnseignementBusiness  ue=new UniteEnseignementBusiness();
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response creatModule(Module m) {
        boolean res=MB.addModule(m);
        if (res) {
            return Response.status(201).entity("Module created").build();
        }
        return Response.status(400).entity("Module already exists").build();
    }
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listModules() {
        return Response.status(200).entity(MB.getAllModules()).build();
    }
    @Path("/list/{mat}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listModulesByMat(@PathParam("mat") String mat) {
        if(mat!=null)
        {
            return Response.status(200).entity(MB.getModuleByMatricule(mat)).build();
        }
        return Response.status(404).entity("Module not found").build();
    }
    @Path("/update/{mat}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(@PathParam("mat") String mat, Module m) {
        if(m==null)
        {
            return Response.status(404).entity("Module not found").build();
        }
        else {
            m.setMatricule(mat);
            boolean res= MB.updateModule(mat,m);
            if (res) {
                return Response.status(200).entity("Module updated").build();
            }
            return Response.status(400).entity("Module already exists").build();
        }
    }
    @Path("/delete/{mat}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(@PathParam("mat") String mat) {
        boolean res= MB.deleteModule(mat);
        if (res) {
            return Response.status(200).entity("Module deleted").build();
        }
        return Response.status(404).entity("Module not found").build();
    }
    @Path("/listByUE")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listModulesByUE(@QueryParam("codeUE") Integer codeUE) {
        if(codeUE==null)
        {
            return Response.status(400).entity("code is required").build();
        }
        else
        {
            UniteEnseignement u=ue.getUEByCode(codeUE);
            if(u!=null)
            {
                return Response.status(200).entity(MB.getModulesByUE(u)).build();
            }
            return Response.status(404).entity("UE " + codeUE + " not found").build();
        }
    }
}
