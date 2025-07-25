import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class QuizSelectorFrame extends JFrame {
    JComboBox<QuizItem> quizComboBox;
    JButton startBtn;
    int userId;

    public QuizSelectorFrame(int userId) {
        this.userId = userId;

        setTitle("Select Quiz");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        quizComboBox = new JComboBox<>();
        startBtn = new JButton("Start Quiz");

        loadQuizzes();

        setLayout(new GridLayout(3, 1));
        add(new JLabel("Select a quiz:", JLabel.CENTER));
        add(quizComboBox);
        add(startBtn);

        startBtn.addActionListener(e -> {
            QuizItem selected = (QuizItem) quizComboBox.getSelectedItem();
            if (selected != null) {
                dispose();
                new QuizFrame(userId, selected.id);  // You must modify QuizFrame to accept quizId
            }
        });

        setVisible(true);
    }

    private void loadQuizzes() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT id, title FROM quizzes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                quizComboBox.addItem(new QuizItem(rs.getInt("id"), rs.getString("title")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class QuizItem {
        int id;
        String title;
        QuizItem(int id, String title) {
            this.id = id;
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
