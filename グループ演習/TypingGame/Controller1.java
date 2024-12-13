package TypingGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// 待機画面
class Controller1 implements ActionListener {
    private StayView stayView;
    private Model model;

    public Controller1(StayView stayView, Model model) {
        this.stayView = stayView;
        this.model = model;

        stayView.getStartButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stayView.getStartButton()) {
            model.Game(); // ゲーム画面へ切り替える
        }
    }
}
