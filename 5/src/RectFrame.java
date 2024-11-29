import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class RectPanel extends JPanel {
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.BLUE);
    g.fillOval(150,150,300,200);
  }
}
class RectFrame extends JFrame {
    public RectFrame(){
      this.setTitle("RectFrame");
      this.setSize(500,500);
      this.add(new RectPanel());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
    public static void main(String argv[]) {
      new RectFrame();
   }
}
