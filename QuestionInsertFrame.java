import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class QuestionInsertFrame extends JFrame {
    JTextField quizIdField = new JTextField(5);
    JTextArea questionArea = new JTextArea(3, 30);
    JTextField optA = new JTextField(20);
    JTextField optB = new JTextField(20);
    JTextField optC = new JTextField(20);
    JTextField optD = new JTextField(20);
    JComboBox<String> correctOption = new JComboBox<>(new String[]{"A", "B", "C", "D"});
    JButton insertBtn = new JButton("Insert Question");

    public QuestionInsertFrame() {
        setTitle("Insert Question");
        setLayout(new GridLayout(9, 2, 5, 5));

        add(new JLabel("Quiz ID:"));
        add(quizIdField);

        add(new JLabel("Question:"));
        add(new JScrollPane(questionArea));

        add(new JLabel("Option A:"));
        add(optA);

        add(new JLabel("Option B:"));
        add(optB);

        add(new JLabel("Option C:"));
        add(optC);

        add(new JLabel("Option D:"));
        add(optD);

        add(new JLabel("Correct Option:"));
        add(correctOption);

        add(insertBtn);

        insertBtn.addActionListener(e -> insertQuestion());

        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void insertQuestion() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO questions (quiz_id, question, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(quizIdField.getText()));
            ps.setString(2, questionArea.getText());
            ps.setString(3, optA.getText());
            ps.setString(4, optB.getText());
            ps.setString(5, optC.getText());
            ps.setString(6, optD.getText());
            ps.setString(7, correctOption.getSelectedItem().toString());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Question inserted successfully!");
            clearFields();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting question.");
        }
    }

    void clearFields() {
        questionArea.setText("");
        optA.setText("");
        optB.setText("");
        optC.setText("");
        optD.setText("");
        correctOption.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuestionInsertFrame());
    }
}
