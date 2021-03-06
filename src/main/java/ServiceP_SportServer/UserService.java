 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceP_SportServer;

import JaxB_SportServer.ListXML;
import JaxB_SportServer.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Johannes Eifert
 */
public class UserService {
    public String BASE_URI = "http://diufvm31.unifr.ch:8090/";
    ClientConfig config = new DefaultClientConfig();
    Client client = Client.create(config);
    
    public ListXML getUserXML(){
        System.out.println("Accessing user list..");
        WebResource r = client.resource(BASE_URI+"CyberCoachServer/resources/users/?start=0&size=1000");
        ListXML list = r.accept(MediaType.APPLICATION_XML).get(ListXML.class);
        System.out.println("User list accessed.");
        return list;
    }
    
    public void createUserXML(User user){
        System.out.print("Creating user: ");
        System.out.println(user.getUri());
        WebResource r = client.resource(BASE_URI+
                "CyberCoachServer/resources/users/"+user.getUsername());
        ClientResponse resp= r.type(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML).put(ClientResponse.class, user);
        if("200".equals(resp.getClientResponseStatus().toString())){
            System.out.println("There has been an error, "+
                    "your user could not have been created");
        } else {
            System.out.println("User "+user.getUsername()+" created :-)");
        }
        
    }
    
}
