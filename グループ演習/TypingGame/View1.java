package TypingGame;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

//待機画面
class StayView extends JFrame {
    private JButton startButton;

    public StayView() {
        setTitle("待機画面");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("ゲームを開始するにはEnterキーを押してください", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.CENTER);

        startButton = new JButton("ゲーム開始");
        add(startButton, BorderLayout.SOUTH);
    }

    public JButton getStartButton() {
        return startButton;
    }
}