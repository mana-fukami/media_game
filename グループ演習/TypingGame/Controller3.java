package TypingGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// 結果画面
class Controller3 implements ActionListener {
    private ResultView resultView;
    private Model model;

    public Controller3(ResultView resultView, Model model) {
        this.resultView = resultView;
        this.model = model;

        resultView.getRetryButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resultView.getRetryButton()) {
            model.Stay(); // 待機画面へ切り替える
        }
    }
}