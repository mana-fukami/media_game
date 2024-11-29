import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HelloLabelFrame extends JFrame implements ActionListener { 
    private JLabel label;

    public HelloLabelFrame(){
      this.setSize(300,200);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JButton b = new JButton("Hello");
      this.add(b,BorderLayout.SOUTH);
      label = new JLabel(" ",JLabel.CENTER);
      this.add(label,BorderLayout.CENTER);
      b.addActionListener(this);
      this.setVisible(true);
    }
    public void actionPerformed(ActionEvent ev) {
      label.setText("Hello !");
    }
    public static void main(String argv[]) {
      new HelloLabelFrame();
    }
}
