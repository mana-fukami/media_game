package TypingGame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class GameView extends JFrame implements Observer{
    protected Model model;
    protected JLabel sentenceLabel;    // 問題文を表示
    protected JLabel scoreLabel;       // スコアを表示
    protected JLabel timeLabel;        // 残り時間を表示
    protected JLabel corLabel;         // 正解数を表示
    protected JLabel missLabel;        // ミス数を表示
    protected JTextField inputField;   // 入力場所←寿司打みたいに真ん中に置きたい
    protected JButton endButton;       // 終了ボタン

    public GameView(Model model) {
    	this.model=model;
    	this.model.addObserver(this);
        setTitle("ゲーム名"); 
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // NORTHに問題文←具体的な配置はまた相談して決めたい
        // ModelでgetSentenceメソッドを作る？
        sentenceLabel = new JLabel(""+model.getSentence(), SwingConstants.CENTER);
        sentenceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(sentenceLabel, BorderLayout.NORTH);

        // CENTERに入力Field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(inputField, BorderLayout.CENTER);

        // SOUTHにスコア、時間、終了ボタン
        // Modelのgetメソッドを使うように変更を加えた
        JPanel bottomPanel = new JPanel(new GridLayout(1, 5));
        scoreLabel = new JLabel("スコア: "+model.getPoints(), SwingConstants.CENTER);
        corLabel = new JLabel("正解: "+model.getCorrect()+"個", SwingConstants.CENTER);
        missLabel = new JLabel("ミス: "+model.getMiss()+"個", SwingConstants.CENTER);
        timeLabel = new JLabel("残り時間: "+model.getTime()+"秒", SwingConstants.CENTER);
        endButton = new JButton("ゲームを終了");
        bottomPanel.add(scoreLabel);
        bottomPanel.add(corLabel);
        bottomPanel.add(missLabel);
        bottomPanel.add(timeLabel);
        bottomPanel.add(endButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    // View内のsetメソッドらを消した
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
