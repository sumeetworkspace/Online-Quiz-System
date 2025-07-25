import javax.swing.*;
// import java.awt.*;

public class ResultFrame extends JFrame {
    public ResultFrame(int score) {
        setTitle("Result");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Your score: " + score, JLabel.CENTER);
        add(label);

        setVisible(true);
    }
}
