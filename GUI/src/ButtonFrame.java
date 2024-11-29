import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

class ButtonFrame extends JFrame {
    public ButtonFrame(){
      this.setSize(300,200);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JButton b = new JButton("WEST");
      this.add(b,BorderLayout.WEST);
      this.setVisible(true);
    }

    public static void main(String argv[]) {
      new ButtonFrame();
   }
}
