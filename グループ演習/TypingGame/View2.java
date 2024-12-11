package TypingGame;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

class GameView extends JFrame implements Observer{
    protected Model model;
    protected JLabel sentenceLabel;    // 問題文を表示
    protected JLabel scoreLabel;       // スコアを表示
    protected JLabel timeLabel;        // 残り時間を表示
    protected JLabel corLabel;         // 正解数を表示
    protected JLabel missLabel;        // ミス数を表示
    protected JTextField inputField;   // 入力場所←寿司打みたいに真ん中に置きたい
    protected JButton endButton;       // 終了ボタン

    public GameView() {
        setTitle("ゲーム名"); 
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // NORTHに問題文←具体的な配置はまた相談して決めたい
        sentenceLabel = new JLabel("問題文", SwingConstants.CENTER);
        sentenceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(sentenceLabel, BorderLayout.NORTH);

        // CENTERに入力Field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(inputField, BorderLayout.CENTER);

        // SOUTHにスコア、時間、終了ボタン
        JPanel bottomPanel = new JPanel(new GridLayout(1, 5));
        scoreLabel = new JLabel("スコア: 0", SwingConstants.CENTER);
        corLabel = new JLabel("正解: 0個", SwingConstants.CENTER);
        missLabel = new JLabel("ミス: 0個", SwingConstants.CENTER);
        timeLabel = new JLabel("残り時間: 60秒", SwingConstants.CENTER);
        endButton = new JButton("ゲームを終了");
        bottomPanel.add(scoreLabel);
        bottomPanel.add(corLabel);
        bottomPanel.add(missLabel);
        bottomPanel.add(timeLabel);
        bottomPanel.add(endButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    
    public void setSentence(String sentence) {
        sentenceLabel.setText(sentence);
    }
    public void setScore(int score) {
        scoreLabel.setText("スコア: " + score);
    }
    public void setTime(int time) {
        timeLabel.setText("残り時間: " + time + "秒");
    }
    public void setCorrect(int correct) {
        corLabel.setText("正解: " + correct + "個");
    }
    public void setMiss(int miss) {
        missLabel.setText("ミス: " + miss + "個");
    }
    public JTextField getInputField() {
        return inputField;
    }
    public JButton getEndButton() {
        return endButton;
    }
    // observer→問題文と制限時間の更新
    public void update(Observable o, Object arg) {
        sentenceLabel.repaint();
        timeLabel.repaint();
    }
}

/*モデルで使いたいので追加してほしいやつ
 * observer→問題文と制限時間の更新
 */
