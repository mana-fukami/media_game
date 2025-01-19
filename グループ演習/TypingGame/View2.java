package TypingGame;

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
        setLayout(new GridLayout(3,1));
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
    protected JLabel romajiLabel;
    protected JLabel kanjiLabel;
    protected JLabel scoreLabel;       // スコアを表示
    protected JLabel timeLabel;        // 残り時間を表示
    protected JLabel corLabel;         // 正解数を表示
    protected JLabel missLabel;        // ミス数を表示
    protected JButton endButton;       // 終了ボタン
    
    public GameView(Model m) {
    	this.model=m;
    	model.addObserver(this);
    	
    	// 背景画像付きのパネルを設定
        BackgroundPanel backgroundPanel = new BackgroundPanel("bb.png");
        setContentPane(backgroundPanel);
        
        setTitle("ゲーム名"); 
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel topPanel = new JPanel();
        timeLabel = new JLabel(model.getTime()+"”00 秒", SwingConstants.CENTER);
        topPanel.add(timeLabel);
        timeLabel.setFont(new Font("Impact",Font.PLAIN, 30));

        JPanel sentencePanel = new JPanel(new GridLayout(2,1));
        sentencePanel.setOpaque(false); // 背景を透過
        romajiLabel = new JLabel(""+model.getSentence(), SwingConstants.CENTER);
        kanjiLabel = new JLabel(""+model.getKanji(), SwingConstants.CENTER);
        romajiLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        kanjiLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        sentencePanel.add(kanjiLabel);
        sentencePanel.add(romajiLabel);
        
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
        
        backgroundPanel.add(timeLabel);
        backgroundPanel.add(sentencePanel);
        backgroundPanel.add(bottomPanel);
    }
    
    public JButton getEndButton() { return endButton; }

    public void update(Observable o, Object arg) {
    	    romajiLabel.setText(model.getSentence());
    	    kanjiLabel.setText(model.getKanji());
    	    scoreLabel.setText("スコア: "+model.getPoints());
            corLabel.setText("正解: "+model.getCorrect());
            missLabel.setText("ミス: "+model.getMiss());
    }
}
