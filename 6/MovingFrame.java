import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MovingPanel extends JPanel implements ActionListener {
  private Timer timer;
  private int x,y;
  MovingPanel(){
    x=0; y=0;
    timer = new Timer(10, this); // 10ミリ秒毎にactionPerformedを呼び出し
    timer.start(); // タイマーをスタート．一度，スタートすると，stopメソッドを
                   // 呼ぶまでactionPerformedが定期的に呼ばれ続けます．
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.fillOval(x,y,10,10);
  }

  public void actionPerformed(ActionEvent e){
    x++; y++;
    if (x>490) timer.stop(); // 端まで移動したら終了．
    this.repaint();   
  }
}

class MovingFrame extends JFrame {
    public MovingFrame(){
      this.setTitle("Random Frame");
      this.setSize(500,530);
      this.add(new MovingPanel());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
    public static void main(String argv[]) {
      new MovingFrame();
   }
}
