import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class QuizFrame extends JFrame {
    int userId;
    int quizId;  // ✅ Now dynamic
    int score = 0;
    int timeLeft = 60; // seconds
    Timer timer;
    ArrayList<Question> questions;
    int currentQuestionIndex = 0;

    JLabel questionLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup group = new ButtonGroup();
    JButton nextBtn;
    JLabel timerLabel;

    // ✅ Modified constructor to accept quizId
    public QuizFrame(int userId, int quizId) {
        this.userId = userId;
        this.quizId = quizId;

        setTitle("Quiz");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questions = loadQuestions();
        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No questions available.");
            dispose();
            return;
        }

        setupUI();
        startTimer();
        showQuestion();

        setVisible(true);
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        questionLabel = new JLabel("", JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            optionsPanel.add(options[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        timerLabel = new JLabel("Time: " + timeLeft + " sec");
        bottom.add(timerLabel);

        nextBtn = new JButton("Next");
        nextBtn.addActionListener(e -> checkAnswer());
        bottom.add(nextBtn);

        add(bottom, BorderLayout.SOUTH);
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft + " sec");
            if (timeLeft <= 0) {
                timer.stop();
                endQuiz();
            }
        });
        timer.start();
    }

    private ArrayList<Question> loadQuestions() {
        ArrayList<Question> qList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM questions WHERE quiz_id = ? ORDER BY RAND() LIMIT 5";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                qList.add(new Question(
                    rs.getInt("quiz_id"),
                    rs.getString("question"), // ✅ use correct column name
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("correct_option")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qList;
    }

    private void showQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            endQuiz();
            return;
        }

        Question q = questions.get(currentQuestionIndex);
        questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + q.question);
        options[0].setText(q.optionA);
        options[1].setText(q.optionB);
        options[2].setText(q.optionC);
        options[3].setText(q.optionD);
        group.clearSelection();
    }

    private void checkAnswer() {
        String selected = null;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selected = "ABCD".charAt(i) + "";
                break;
            }
        }

        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select an answer before proceeding.");
            return;
        }

        if (selected.equalsIgnoreCase(questions.get(currentQuestionIndex).correctOption)) {
            score++;
        }

        currentQuestionIndex++;
        showQuestion();
    }

    private void endQuiz() {
        if (timer != null) timer.stop();
        saveResult();
        dispose();
        new ResultFrame(score);
    }

    private void saveResult() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO results (user_id, quiz_id, score) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, quizId);
            ps.setInt(3, score);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
