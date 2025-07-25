import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerBtn, backBtn;

    public RegisterFrame() {
        setTitle("Create Account");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        registerBtn = new JButton("Register");
        backBtn = new JButton("Back to Login");

        panel.add(registerBtn);
        panel.add(backBtn);

        add(panel);

        // Register button action
        registerBtn.addActionListener(e -> registerUser());

        // Back button action
        backBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        setVisible(true);
    }

    private void registerUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            // Check if user already exists
            String checkSql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setString(1, username);
            ResultSet rs = checkPs.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Username already taken!");
                return;
            }

            // Insert new user
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password); // ⚠️ store hashed password in real apps!
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Account created successfully!");
            dispose();
            new LoginFrame();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error creating account");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterFrame());
    }
}
