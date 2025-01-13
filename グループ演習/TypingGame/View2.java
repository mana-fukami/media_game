package TypingGame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        // 背景画像をロード
        backgroundImage = new ImageIcon(imagePath).getImage();
     // レイアウトをBorderLayoutに設定
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // 背景画像を描画
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

class GameView extends JFrame implements Observer{
    protected Model model;
    //protected JLabel sentenceLabel;    // 問題文を表示
    protected JLabel romajiLabel;
    protected JLabel kanjiLabel;
    protected JLabel scoreLabel;       // スコアを表示
    protected JLabel timeLabel;        // 残り時間を表示
    protected JLabel corLabel;         // 正解数を表示
    protected JLabel missLabel;        // ミス数を表示
    //protected JTextField inputField;   // 入力場所←寿司打みたいに真ん中に置きたい
    protected JButton endButton;       // 終了ボタン
    
    public GameView(Model m) {
    	this.model=m;
    	model.addObserver(this);
    	
    	// 背景画像付きのパネルを設定
        BackgroundPanel backgroundPanel = new BackgroundPanel("blackboard.png");
        //backgroundPanel.setLayout(new GridLayout(3, 1));
        setContentPane(backgroundPanel);
        
        setTitle("ゲーム名"); 
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new  GridLayout(3,1));
        
        JPanel topPanel = new JPanel();
        timeLabel = new JLabel("残り時間:  "+model.getTime()+" 秒", SwingConstants.CENTER);
        topPanel.add(timeLabel);
        timeLabel.setFont(new Font("Arial",Font.ITALIC,30));
        //topPanel.setMinimumSize(new Dimension(0,200)); // 幅は自動、高さ..px

        // NORTHに問題文←具体的な配置はまた相談して決めたい
        // ModelでgetSentenceメソッドを作る?
        JPanel sentencePanel = new JPanel(new GridLayout(2,1));
        sentencePanel.setOpaque(false); // 背景を透過
        romajiLabel = new JLabel(""+model.getSentence(), SwingConstants.CENTER);
        kanjiLabel = new JLabel(""+model.getKanji(), SwingConstants.CENTER);
        romajiLabel.setFont(new Font("Arial", Font.BOLD, 40));
        kanjiLabel.setFont(new Font("Arial", Font.BOLD, 50));
        sentencePanel.add(kanjiLabel);
        sentencePanel.add(romajiLabel);
        //this.add(sentencePanel);
        
        // CENTERに入力Field
        //inputField = new JTextField();
        //inputField.setFont(new Font("Arial", Font.PLAIN, 18));
        //add(inputField, BorderLayout.CENTER);

        // SOUTHにスコア、時間、終了ボタン
        // Modelのgetメソッドを使うように変更を加えた
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.setOpaque(false); // 背景を透過
        scoreLabel = new JLabel("スコア: "+model.getPoints(), SwingConstants.CENTER);
        corLabel = new JLabel("正解: "+model.getCorrect()+"個", SwingConstants.CENTER);
        missLabel = new JLabel("ミス: "+model.getMiss()+"個", SwingConstants.CENTER);
        endButton = new JButton("ゲームを終了");
        bottomPanel.add(scoreLabel);
        bottomPanel.add(corLabel);
        bottomPanel.add(missLabel);
        bottomPanel.add(endButton);
        
        backgroundPanel.add(timeLabel, BorderLayout.NORTH);
        backgroundPanel.add(sentencePanel, BorderLayout.CENTER);
        backgroundPanel.add(bottomPanel, BorderLayout.SOUTH);
        
    }
    // View内のsetメソッドらを消した
    /*public JLabel getSentenceLabel() {
        return sentenceLabel;
    }*/
    public JButton getEndButton() {
        return endButton;
    }
    // observer→問題文と制限時間の更新
    // ModelのsetChangedが呼び出されるたびにupdateが呼び出される。
    public void update(Observable o, Object arg) {
        //if (model.getTime() != 0) {
    	    romajiLabel.setText(model.getSentence());
    	    kanjiLabel.setText(model.getKanji());
    	    scoreLabel.setText("スコア: "+model.getPoints());
            corLabel.setText("正解: "+model.getCorrect());
            missLabel.setText("ミス: "+model.getMiss());
        //}
        //sentenceLabel.repaint();
        //timeLabel.setText("残り時間: "+model.getTime());
        //timeLabel.repaint();
    }
}

/*モデルで使いたいので追加してほしいやつ
 * observer→問題文と制限時間の更新
 */
