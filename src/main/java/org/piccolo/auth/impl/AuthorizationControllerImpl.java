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
import org.piccolo.auth.AuthorizationController;

import com.owlike.genson.Genson;

/**
 * Controls the authorizations
 */
public class AuthorizationControllerImpl implements AuthorizationController {
	private static final URI SERVICE_BASE_URI = UriBuilder.fromUri("http://172.16.68.50:8080/piccoloapi").build();
	private static final int TIMEOUT = 3000;
	
    public boolean requestAccess(String id){
    	try {
	    	Client client = ClientBuilder.newClient();
	    	client.property(ClientProperties.CONNECT_TIMEOUT, TIMEOUT);
	    	client.property(ClientProperties.READ_TIMEOUT, TIMEOUT);
	    	WebTarget webTarget = client.target(SERVICE_BASE_URI);
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
    
    public static void main(String... args) {
    	AuthorizationController authorizationController= new AuthorizationControllerImpl();
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
