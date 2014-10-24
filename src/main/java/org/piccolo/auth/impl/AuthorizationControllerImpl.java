package org.piccolo.auth.impl;

import org.piccolo.auth.AuthorizationController;

import java.util.HashSet;
import java.util.Set;

/**
 * Controls the authorizations
 */
public class AuthorizationControllerImpl implements AuthorizationController {
    final Set<String> KARHU=new HashSet<String>();
    {
        KARHU.add("4742124002473");
        KARHU.add("260182+069Y");
        KARHU.add("1RR000219988");  // Siim ID
        KARHU.add("AA0825138");  // library" +
        KARHU.add("260182+069Y");  // Driver
        KARHU.add("5449000085757");  //Cola
    }

    public boolean requestAccess(String id){

        return KARHU.contains(id);
    }
}
