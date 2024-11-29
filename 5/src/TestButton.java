import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ButtonPanel extends JPanel implements ActionListener{
  private JButton b1=new JButton("Button 1");
  private JButton b2=new JButton("Button 2");
  private JButton b3=new JButton("Button 3");
  private JLabel l;
  
  ButtonPanel(){
    l =  new JLabel("0",JLabel.CENTER);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
  //ActionCommand
    b1.setActionCommand("1");
    b2.setActionCommand("2");
    b3.setActionCommand("3");
    setLayout(new GridLayout(2,2));
    add(b1); add(b2); add(b3); add(l);
  }
  public void actionPerformed(ActionEvent e) {
	  String es=e.getActionCommand();
	  l.setText(es);
  }
}

class TestButton extends JFrame{
  public TestButton(){
    ButtonPanel b=new ButtonPanel();
    this.setTitle("Test Button");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(b);
    this.pack();
    this.setVisible(true);
  }
  public static void main(String argv[]) {
    new TestButton();
  }
}
