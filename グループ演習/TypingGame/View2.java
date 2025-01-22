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

class GameBackground extends JPanel {
    private Image backgroundImage;

    public GameBackground(String imagePath) {
        // 背景画像をロード
        backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(new GridLayout(5,1));
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
    	GameBackground backgroundPanel = new GameBackground("bb.png");
        setContentPane(backgroundPanel);
        
        setTitle("ゲーム名"); 
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel topPanel = new JPanel();
        timeLabel = new JLabel(String.format("00:%05.2f", model.getTime()), SwingConstants.CENTER);
        topPanel.add(timeLabel);
        timeLabel.setFont(new Font("Impact",Font.PLAIN, 35));

        JPanel sentencePanel = new JPanel(new GridLayout(2,1));
        sentencePanel.setOpaque(false); // 背景を透過
        romajiLabel = new JLabel(""+model.getSentence(), SwingConstants.CENTER);
        kanjiLabel = new JLabel(""+model.getKanji(), SwingConstants.CENTER);
        romajiLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        kanjiLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        sentencePanel.add(kanjiLabel);
        sentencePanel.add(romajiLabel);
        
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        scoreLabel = new JLabel("",SwingConstants.CENTER);
        corLabel = new JLabel("",SwingConstants.CENTER);
        missLabel = new JLabel("",SwingConstants.CENTER);
        endButton = new JButton("ゲームを終了");
        scoreLabel.setFont(new Font("Impact",Font.PLAIN, 20));
        corLabel.setFont(new Font("Impact",Font.PLAIN, 20));
        missLabel.setFont(new Font("Impact",Font.PLAIN, 20));
        endButton.setFont(new Font("Impact",Font.PLAIN, 20));
        bottomPanel.add(scoreLabel);
        bottomPanel.add(corLabel);
        bottomPanel.add(missLabel);
        bottomPanel.add(endButton);
        
        backgroundPanel.add(timeLabel);
        JPanel n1 = new JPanel(); n1.setOpaque(false);
        backgroundPanel.add(n1); // レイアウトの調整のための空白
        backgroundPanel.add(sentencePanel);
        JPanel n2 = new JPanel(); n2.setOpaque(false);
        backgroundPanel.add(n2); // レイアウトの調整のための空白
        backgroundPanel.add(bottomPanel);
    }
    
    // Color(70, 130, 180) 青色
    
    public JButton getEndButton() { return endButton; }

    public void update(Observable o, Object arg) {
    		// sentencePanel
    	    romajiLabel.setText(model.getSentence());
    	    kanjiLabel.setText(model.getKanji());
    	    // bottomPanel
    	    scoreLabel.setText("<html>スコア: <span style='color:rgb(70, 130, 180); font-size:20px;'>"+model.getPoints()+"</span> pts</html>");
            corLabel.setText("<html>正解: <span style='font-size:20px;'>"+model.getCorrect()+"</span> 個</html>");
            missLabel.setText("<html>ミスタイプ: <span style='font-size:20px;'>"+model.getMiss()+"</span> 回</html>");
    }
}
