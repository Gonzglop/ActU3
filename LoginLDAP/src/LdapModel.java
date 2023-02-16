import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

public class LdapModel {

    DirContext connection;
    public void newConnection() {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://51.68.45.25:389");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin, dc=dambelen, dc=com");
        env.put(Context.SECURITY_CREDENTIALS, "StrongAdminPassw0rd");
        try {
            connection = new InitialDirContext(env);
            System.out.println("Conexión realizada con éxito.");
        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String getAllUsers() throws NamingException {
        String searchFilter = "(objectClass=inetOrgPerson)";
        String[] reqAtt = {"cn"};
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search("dc=dambelen,dc=com", searchFilter, controls);

        String results = "";
        while (users.hasMore()) {
            SearchResult result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            results += attr.get("cn")+"\n";
            System.out.println(attr.get("cn"));
        }
        return results;
    }

    public String getGroupUsers(String label, String group) throws NamingException {
        String searchFilter = "(objectClass=inetOrgPerson)";
        String[] reqAtt = {"cn"};
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search(label + group + ",dc=dambelen,dc=com", searchFilter, controls);

        String results = "";
        while (users.hasMore()) {
            SearchResult result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            results += attr.get("cn")+"\n";
            System.out.println(attr.get("cn"));
        }
        return results;
    }

    public String searchUsers(String username) throws NamingException {

        String searchFilter = "(cn="+ username +")";
        String[] reqAtt = {"cn"};
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search("dc=dambelen,dc=com", searchFilter, controls);
        String results= "";
        while (users.hasMore()) {
            SearchResult result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            results = "Usuario encontrado: " + attr.get("cn");
            System.out.println("Usuario encontrado: " + attr.get("cn"));
        }

        return results;
    }

    public static String authUser(String username, String password) {
        String results;
        try {
            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://51.68.45.25:389");
            env.put(Context.SECURITY_PRINCIPAL, "cn="+username+", ou=segundo, dc=dambelen, dc=com");
            env.put(Context.SECURITY_CREDENTIALS, password);
            DirContext con = new InitialDirContext(env);

            results = "Inicio de sesión realizado con éxito.";
            System.out.println("Inicio de sesión realizado con éxito.");
            con.close();
        }catch (Exception e) {
            results = "Algo ha fallado: "+e.getMessage();
            System.out.println("Algo ha fallado: "+e.getMessage());
        }
        return results;
    }
}