package pl.agh.edu.nao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.agh.edu.nao.actionImplementations.base.ActionImplementation;
import pl.agh.edu.nao.actions.AbstractAction;
import pl.agh.edu.nao.actions.AngleInterpolation;
import pl.agh.edu.nao.actions.ComplexAction;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("get")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("getList")
    @Produces(MediaType.APPLICATION_JSON)
    public ListWrapper getList() {
    	List<String> l = new ArrayList<String>();
    	List<Class<?>> classes = ClassFinder.find("pl.agh.edu.nao.actionImplementations");
    	for(Class c : classes){
    		l.add(c.getSimpleName().toLowerCase());
    	}
    	return new ListWrapper(l);
    }
    
    @GET
    @Path("getAction/{action}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAction(@PathParam("action") String action) {
    	System.out.println("Action sent: " + action);
    	try {
			Class c = Class.forName("pl.agh.edu.nao.actionImplementations." + action.substring(0,1).toUpperCase() + action.substring(1).toLowerCase());
			ActionImplementation a = (ActionImplementation) c.newInstance();
			return a.getAction().toString();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "";
    }
}
