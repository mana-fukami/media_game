package TypingGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StayView extends JFrame {
    private JButton startButton;
    private RoundedButton levelEasyButton;
    private RoundedButton levelMediumButton;
    private RoundedButton levelHardButton;
    private RoundedButton selectedButton; // 現在選択中のボタンを追跡

    public StayView() {
        setTitle("待機画面");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 背景パネル
        BackgroundPanel backgroundPanel = new BackgroundPanel("view13_bg.png");
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null); // レイアウトマネージャを無効化

        // ラベルを配置
        JLabel levelLabel = new JLabel(
            "<html><div style='text-align: center; font-size: 16px; color: black; font-weight: bold;'>ステージを選択してください</div></html>",
            SwingConstants.CENTER
        );
        levelLabel.setBounds(0, 225, 1000, 50); // 座標とサイズを設定
        backgroundPanel.add(levelLabel);

        // JLabel startLabel = new JLabel(
        //     "<html><div style='text-align: center; font-size: 16px; color: black; font-weight: bold;'>ゲームを開始するにはスペースキーを押してください</div></html>",
        //     SwingConstants.CENTER
        // );
        // startLabel.setBounds(0, 225, 1000, 50); // 座標とサイズを設定
        // backgroundPanel.add(startLabel);

        // レベル選択ボタンを配置
        levelEasyButton = createLevelButton("基礎科学実験");
        levelEasyButton.setBounds(160, 300, 200, 60); // 座標とサイズを設定
        backgroundPanel.add(levelEasyButton);

        levelMediumButton = createLevelButton("線形代数");
        levelMediumButton.setBounds(400, 300, 200, 60); // 座標とサイズを設定
        backgroundPanel.add(levelMediumButton);

        levelHardButton = createLevelButton("微積");
        levelHardButton.setBounds(640, 300, 200, 60); // 座標とサイズを設定
        backgroundPanel.add(levelHardButton);

        // ゲーム開始ボタンを配置
        startButton = new JButton("ゲーム開始");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBounds(0, 510, 1000, 60); // 座標とサイズを設定
        backgroundPanel.add(startButton);
    }

    private RoundedButton createLevelButton(String text) {
        RoundedButton button = new RoundedButton(text, Color.WHITE, 30, 30);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null) {
                    // 以前の選択ボタンの状態をリセット
                    selectedButton.setSelected(false);
                    selectedButton.setBackground(Color.WHITE);
                    selectedButton.setForeground(new Color(70, 130, 180));
                    selectedButton.repaint();
                }
                // 現在の選択ボタンを更新
                    selectedButton = button;
                    selectedButton.setSelected(true);
                    selectedButton.setBackground(new Color(70, 130, 180));
                    selectedButton.setForeground(Color.WHITE);
                    selectedButton.repaint();
            }
        });
        return button;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public RoundedButton getLevelEasyButton() {
        return levelEasyButton;
    }

    public RoundedButton getLevelMediumButton() {
        return levelMediumButton;
    }

    public RoundedButton getLevelHardButton() {
        return levelHardButton;
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        // 画像を読み込む
        backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 背景画像を描画
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

class RoundedButton extends JButton {
    private Color defaultBackgroundColor;
    private int arcWidth;
    private int arcHeight;
    private boolean isSelected; // 選択状態のフラグ

    public RoundedButton(String text, Color backgroundColor, int arcWidth, int arcHeight) {
        super(text);
        this.defaultBackgroundColor = backgroundColor;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.isSelected = false;
        setBackground(backgroundColor);
        setForeground(new Color(70, 130, 180));
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - fm.getDescent());
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 選択状態に応じて枠線の太さを変更
        if (isSelected) {
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.WHITE);
        } else {
            g2.setStroke(new BasicStroke(1));
            g2.setColor(new Color(70, 130, 180));
        }

        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        g2.dispose();
    }
}

