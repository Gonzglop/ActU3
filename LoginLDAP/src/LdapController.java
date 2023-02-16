import javax.naming.NamingException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LdapController implements ActionListener, WindowListener {
    private LdapView view;
    private LdapModel model;


    public LdapController(LdapView view, LdapModel model) {
        this.model = model;
        this.view = view;

        addActionListeners(this);
        addWindowListeners(this);

    }

    public void addActionListeners(ActionListener listener){
        view.authButton.addActionListener(listener);
        view.allUsersButton.addActionListener(listener);
        view.searchUsersButton.addActionListener(listener);
        view.GroupUsersButton.addActionListener(listener);
    }

    private void addWindowListeners(WindowListener listener){
        view.frame.addWindowListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        switch (command){
            case "Iniciar":
                view.conexResultLabel.setText(
                        model.authUser(
                                view.usernameField.getText(),
                                view.passwordField.getText()
                        ));

            case "Todos":
                try {
                    view.resultArea.setText(model.getAllUsers());
                } catch (NamingException ex) {
                    ex.printStackTrace();
                }
                break;

            case "Buscar":
                try {
                    view.resultArea.setText(
                            model.searchUsers(
                                    view.searchUserField.getText()
                            ));
                } catch (NamingException ex) {
                    ex.printStackTrace();
                }
                break;

            case "Grupos":
                try {
                    view.resultArea.setText(
                            model.getGroupUsers(
                                    String.valueOf(view.GroupComboBox.getSelectedItem()),
                                    view.GroupNameField.getText()
                            ));
                } catch (NamingException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
