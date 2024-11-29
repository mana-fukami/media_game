import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class LabelFrame extends JFrame {
    public LabelFrame(){
      this.setSize(600,500);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JButton b = new JButton("OK");
      this.add(b,BorderLayout.SOUTH);
      JLabel txt1 = new JLabel("<html><span style='font-family:MS ゴシック; font-size:20pt;'>"
    	+"アフリカ大陸に広く生息しているネコ科の肉食獣</span></html>",JLabel.CENTER);
      JLabel txt2 = new JLabel("<html><span style='font-family:MS ゴシック; font-size:36pt; color:#F0702B; " 
          +"background-color:#FFEB9F;'>ライオン</span></html>",JLabel.CENTER);
      JLabel img1 = new JLabel("<html><img src='file:lion.jpg' width=400 height=300></html>",JLabel.CENTER);
      this.add(txt2,BorderLayout.WEST);
      this.add(txt1,BorderLayout.NORTH);
      this.add(img1,BorderLayout.CENTER);
      this.setVisible(true);
    }

    public static void main(String argv[]) {
      new LabelFrame();
   }
}
