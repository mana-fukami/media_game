import java.awt.*;
import javax.swing.*;

class NoLayout extends JFrame {
  public NoLayout(){
    JButton b1=new JButton("button 1"),b2=new JButton("button 2");
    JButton b3=new JButton("button 3");
    this.setTitle("No Layout"); 
    this.setLayout(null);
    b1.setBounds(50,10,100,30);
    b2.setBounds(50,50,100,30);
    b3.setBounds(50,90,100,30);
    this.add(b1);
    this.add(b2);
    this.add(b3);
    this.setSize(300,200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  public static void main(String argv[]) {
    new NoLayout();
  }
}
