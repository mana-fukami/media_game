package TypingGame;

import java.awt.*;
import javax.swing.*;

// 待機画面
class StayView extends JFrame {
    private JButton startButton;

    public StayView() {
        setTitle("待機画面");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 背景色設定
        getContentPane().setBackground(Color.decode("#17288b"));

        JLabel welcomeLabel = new JLabel("ゲームを開始するにはEnterキーを押してください", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.WHITE); // テキスト色を白に
        add(welcomeLabel, BorderLayout.CENTER);

        startButton = new JButton("ゲーム開始");
        styleButton(startButton);
        add(startButton, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.decode("#17288b"));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    public JButton getStartButton() {
        return startButton;
    }
}