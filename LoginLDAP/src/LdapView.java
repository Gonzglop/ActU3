import javax.swing.*;

public class LdapView extends JFrame {
    private JPanel panel1;
    JLabel title;
    JTextField usernameField;
    JTextField passwordField;
    JButton authButton;
    JButton allUsersButton;
    JButton searchUsersButton;
    JButton GroupUsersButton;
    JComboBox GroupComboBox;
    JTextField GroupNameField;
    JTextField searchUserField;
    JTextArea resultArea;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel conexResultLabel;
    final JFrame frame;

    public LdapView() {
        frame = new JFrame("LDAP App");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 500);
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
    }
}
