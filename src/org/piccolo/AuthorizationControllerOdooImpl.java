package org.piccolo;

/**
 * Class for connecting to ODOO and asking form permissions for an ID.
 */
public class AuthorizationControllerOdooImpl implements AuthorizationController {
    public boolean requestAccess(String id) {
        return false;
    }
    // aci.setupConnectionToODOO();
    // return aci.requestAccess(id):
}
