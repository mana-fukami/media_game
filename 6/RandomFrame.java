import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//-----座標(x,y),大きさsize,色col-----
class Figure {
  protected int x,y,size;
  protected Color col[]={Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN,Color.ORANGE};
  protected Color color;
  Figure() {
    x = (int)(Math.random()*450);
    y = (int)(Math.random()*450);
    size=(int)(Math.random()*30+20);
    color=col[(int)(Math.random()*col.length)];
  }
  void draw(Graphics g) {}
  void fall() {y++;}
}

//-----図形を描く-----
class Circle extends Figure {
  void draw(Graphics g) {
    g.setColor(color);
    g.drawOval(x,y,size,size);
  }
}
class Box extends Figure {
  void draw(Graphics g) {
    g.setColor(color);
    g.drawRect(x,y,size,size);
  }
}
class Triangle extends Figure {
	void draw(Graphics g) {
		g.setColor(color);
		//座標配列
		ArrayList<Integer> p1=new ArrayList<Integer>();
		p1.add(x);p1.add(y+size);
		ArrayList<Integer> p2=new ArrayList<Integer>();
		p2.add(x+(int)(size*Math.cos(Math.toRadians(210))));p2.add(y+(int)(size*Math.sin(Math.toRadians(210))));
		ArrayList<Integer> p3=new ArrayList<Integer>();
		p3.add(x+(int)(size*Math.cos(Math.toRadians(330))));p3.add(y+(int)(size*Math.sin(Math.toRadians(330))));
		//三角形の描画
		g.drawLine(p1.get(0),p1.get(1),p2.get(0),p2.get(1));
		g.drawLine(p2.get(0),p2.get(1),p3.get(0),p3.get(1));
		g.drawLine(p3.get(0),p3.get(1),p1.get(0),p1.get(1));
	}
}

//-----ランダムに図形を配置-----
class RandomPanel extends JPanel implements ActionListener{
  private final static int NUM=50;
  private ArrayList<Figure> fig;
  private Timer timer;
  RandomPanel(){
    fig=new ArrayList<Figure>();
    for(int i=0;i<NUM;i++){
      fig.add(new Box());
      fig.add(new Circle());
      fig.add(new Triangle());
    }
    timer=new Timer(10,this);
    timer.start();
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(Figure f: fig){
      f.draw(g); 
    }
  }
  public void actionPerformed(ActionEvent e) {
  	for(int i=0;i<fig.size();i++) {
  		fig.get(i).fall();
  	}
  	this.repaint();
  }
}

//-----画面に表示する-----
class RandomFrame extends JFrame{
    public RandomFrame(){
      this.setTitle("Random Frame");
      this.setSize(500,530);
      this.add(new RandomPanel());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
    public static void main(String argv[]) {
      new RandomFrame();
   }
}
