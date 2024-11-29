import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

class GridFrame extends JFrame {
    public GridFrame(){
      this.setSize(400,400);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(new GridLayout(4,4));
      int a='A';
      for(int i=1;i<=16;i++) {
    	  JButton b=new JButton();
    	  char[] txt=Character.toChars(a);
    	  String str=new String(txt);
    	  b.setText("<html><span style='color:rgb(37,139,126);font-size:40pt;'>"+str+"</span></html>");
    	  add(b);
    	  a++;
      }
      this.setVisible(true);
    }

    public static void main(String argv[]) {
      new GridFrame();
   }
}
