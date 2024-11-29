import javax.swing.*;
import java.awt.*;

class ColorFrame extends JFrame {
    public ColorFrame(){
      this.setSize(300,200);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JButton b = new JButton("OK");
      this.add(b,BorderLayout.SOUTH);
      JLabel l = new JLabel("JLabel");
      l.setForeground(Color.yellow);
      l.setOpaque(true);
      l.setBackground(Color.blue);
      l.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
      //JLabel l = new JLabel("<html><span style='font-size:36pt; color:blue; " 
      //    +"background-color:yellow;'>JLabel</span></html>",JLabel.CENTER);
      // JLabel l = new JLabel("<html><img src='file:lion.jpg' width=100 height=100></html>",JLabel.CENTER);
      this.add(l,BorderLayout.CENTER);
      this.setVisible(true);
    }

    public static void main(String argv[]) {
      new ColorFrame();
   }
}
