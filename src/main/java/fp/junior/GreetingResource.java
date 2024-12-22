package fp.junior;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/hello2")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(@QueryParam("name") String name) {
        String newName = "Fernando " + name;
        return "Hello " + newName;
    }
    
}
