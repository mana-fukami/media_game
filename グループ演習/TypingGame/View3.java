package TypingGame;

import java.awt.*;
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

        // 背景色設定
        getContentPane().setBackground(Color.decode("#17288b"));

        scoreLabel = createStyledLabel("スコア: 0");
        add(scoreLabel);

        correctLabel = createStyledLabel("正解数: 0");
        add(correctLabel);

        missLabel = createStyledLabel("ミス数: 0");
        add(missLabel);

        retryButton = new JButton("もう一度プレイ");
        styleButton(retryButton);
        add(retryButton);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE); // テキスト色を白に
        return label;
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.decode("#17288b"));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
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
