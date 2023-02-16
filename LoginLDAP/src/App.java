public class App {
    public static void main(String[] args) {
        LdapView ldapView = new LdapView();
        LdapModel ldapModel = new LdapModel();
        LdapController ldapController = new LdapController(ldapView,ldapModel);

        ldapModel.newConnection();
    }
}
