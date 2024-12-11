package TypingGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Controller2 implements KeyListener,ActionListener{
    private GameView gameView;
    private Model model;

    public Controller2(GameView gameView, Model model) {
        this.gameView = gameView;
        this.model = model;

        // イベントリスナーを登録
        gameView.getInputField().addKeyListener(this);
        gameView.getEndButton().addActionListener(this);
    }

    // キー入力
    @Override
    public void keyTyped(KeyEvent e) {
        char typedChar = e.getKeyChar(); 
        model.Check(typedChar); // 文字が入力されたらCheckを呼び出す
    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    // 終了ボタン
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameView.getEndButton()) {
            model.Result(); // 結果画面へ切り替える 
        }
    }
}
