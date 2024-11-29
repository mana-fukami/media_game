import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MoveByKeyPanel extends JPanel implements KeyListener{
   private int x=200, y=200; //座標
   private int move = 5;     //移動距離
   private int r = 10;    
   public MoveByKeyPanel(){
      this.setBackground(Color.white);
      this.setFocusable(true);
      this.addKeyListener(this);
   }
   protected void paintComponent(Graphics g){
      super.paintComponent(g);
      g.fillOval(x, y, r, r);
   }
   public void keyPressed(KeyEvent e){
      int k = e.getKeyCode();
      switch(k){
        case KeyEvent.VK_RIGHT:
          x = x+move; 
          break;
        case KeyEvent.VK_LEFT:
          x = x-move; 
          break;
        case KeyEvent.VK_DOWN:
          y = y+move;
          break;
        case KeyEvent.VK_UP:
          y = y-move; 
          break;
      }
      repaint();
   }
   public void keyTyped(KeyEvent e){ 
     char c = e.getKeyChar();
     switch(c){
       case 'f':
         x = x+move; 
         break;
       case 'b':
         x = x-move; 
         break;
       case 'd':
         y = y+move;
         break;
       case 'u':
         y = y-move; 
         break;
     }
     repaint();
   }
   public void keyReleased(KeyEvent e){ }
}

class MoveByKeyFrame extends JFrame {
  public MoveByKeyFrame(){
    this.setTitle("MoveByKey Frame");
    this.setSize(500,500);
    this.add(new MoveByKeyPanel());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  public static void main(String argv[]) {
    new MoveByKeyFrame();
 }
}
