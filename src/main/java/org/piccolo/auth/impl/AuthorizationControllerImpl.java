package org.piccolo.auth.impl;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientProperties;
import org.piccolo.Configuration;
import org.piccolo.Configuration.Setting;
import org.piccolo.auth.AuthorizationController;

import com.owlike.genson.Genson;

/**
 * Controls the authorizations
 */
public class AuthorizationControllerImpl implements AuthorizationController {
	private int timeout;
	private URI serviceBaseUri;
	
	public AuthorizationControllerImpl(Configuration configuration) {
		serviceBaseUri = UriBuilder.fromUri(configuration.getSetting(Setting.AUTH_SERVICE_URI)).build();
		timeout = Integer.parseInt(configuration.getSetting(Setting.AUTH_SERVICE_TIMEOUT));
	}
	
    public boolean requestAccess(String id){
    	try {
	    	Client client = ClientBuilder.newClient();
	    	client.property(ClientProperties.CONNECT_TIMEOUT, timeout);
	    	client.property(ClientProperties.READ_TIMEOUT, timeout);
	    	WebTarget webTarget = client.target(serviceBaseUri);
	    	Builder builder = webTarget.path("piccoloUser").path("canAccess").path(id).request().accept(MediaType.APPLICATION_JSON);
			Response response = builder.get();
			String responseString = response.readEntity(String.class);
			ServiceResponse serviceResponse = new Genson.Builder().create().deserialize(responseString, ServiceResponse.class);
			return serviceResponse.isIsallowed();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    /**
     * Tests accessing the service.
     * @param args required by API, not used.
     */
    public static void main(String... args) {
    	AuthorizationController authorizationController= new AuthorizationControllerImpl(new Configuration());
    	System.out.println(authorizationController.requestAccess("1234"));
    }
    
    public static class ServiceResponse {
    	private boolean isallowed;
    	
    	public boolean isIsallowed() {
    		return isallowed;
    	}
    	public void setIsallowed(boolean isallowed) {
    		this.isallowed = isallowed;
    	}
    }
}
