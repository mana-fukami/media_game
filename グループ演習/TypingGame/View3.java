package TypingGame;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

// 結果画面
class ResultView extends JFrame {
    private JLabel scoreLabel;
    private JLabel correctLabel;
    private JLabel missLabel;
    private JButton retryButton;

    public ResultView() {
        setTitle("結果画面");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        scoreLabel = new JLabel("スコア: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scoreLabel);

        correctLabel = new JLabel("正解数: 0", SwingConstants.CENTER);
        add(correctLabel);

        missLabel = new JLabel("ミス数: 0", SwingConstants.CENTER);
        add(missLabel);

        retryButton = new JButton("もう一度プレイ");
        add(retryButton);
    }

    public void updateResults(int score, int correct, int miss) {
        scoreLabel.setText("スコア: " + score);
        correctLabel.setText("正解数: " + correct);
        missLabel.setText("ミス数: " + miss);
    }

    public JButton getRetryButton() {
        return retryButton;
    }
}