import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class CirclePanel extends JPanel implements MouseListener,MouseMotionListener {
	private int   radius = 5/2;
	private Color color  = Color.red;
	private int   x[],y[],r[];
	private Color c[];
	private int   num=0;
	final static int MAX=5000;
	public boolean flag[];//連続した線かどうか
//----------setter----------
	void setRadius(int r) {
		this.radius=r;
	}
	void setPanelColor(Color c) {
		this.color=c;
	}
	//描画のリセット
	public void clear() {
		x=new int[MAX];
		y=new int[MAX];
		r=new int[MAX];
		c=new Color[MAX];
		num=0;
		flag=new boolean[MAX];
		this.repaint();
	}
//----------コンストラクタ----------
	public CirclePanel(){
		radius=5/2;
		x=new int[MAX];
		y=new int[MAX];
		r=new int[MAX];
		c=new Color[MAX];
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		flag=new boolean[MAX];
	}
//----------●を描く----------
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0;i<num;i++){
			g.setColor(c[i]);
			if(x[i+1]!=0&&y[i+1]!=0&&flag[i+1]==true) {
				for(int j=-r[i];j<=r[i];j++) {
					g.drawLine(x[i]+j,y[i]+j,x[i+1]+j,y[i+1]+j);
				}
			}
		}
	}
	private void addCircle(int x0,int y0){
		if (num>=MAX) return; 
		x[num]=x0; y[num]=y0;
		r[num]=radius; c[num]=color;
		num++;
		this.repaint();
		flag[num]=true;
	}
	//MouseListener
	public void mousePressed(MouseEvent e) {
		addCircle(e.getX(),e.getY());
	}
	public void mouseClicked(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) {
		flag[num]=false;//線の区切り//終了座標のidの次にfalse入る。
	}
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e)  {}
	//MouseMotionListener
	public void mouseDragged(MouseEvent e) {
		addCircle(e.getX(),e.getY());
	}
	public void mouseMoved(MouseEvent e) {}
}

class CircleFrame extends JFrame implements ActionListener{
	private CirclePanel panel;
	public CircleFrame(){
		this.setTitle("CircleFrame");
		this.setSize(500,500);
		panel=new CirclePanel();
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	//左のボタン:色を変えるボタン
		JPanel pl=new JPanel();pl.setLayout(new GridLayout(3,1));
		JButton b1=new JButton("<html><span style='color:red;'>RED</span></html>");
		JButton b2=new JButton("<html><span style='color:blue;'>BLUE</span></html>");
		JButton b3=new JButton("<html><span style='color:orange;'>ORANGE</span></html>");
		//ActionListener
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		//ActionCommand
		b1.setActionCommand("red");
		b2.setActionCommand("blue");
		b3.setActionCommand("orange");
		//パネルに配置
		pl.add(b1);pl.add(b2);pl.add(b3);
	//右のボタン:半径を変えるボタン
		JPanel pr=new JPanel();pr.setLayout(new GridLayout(2,1));
		//ペンの太さ
		JButton b4=new JButton("5");
		JButton b5=new JButton("10");
		//ActionListener
		b4.addActionListener(this);
		b5.addActionListener(this);
		//ActionCommand
		b4.setActionCommand("5");
		b5.setActionCommand("10");
		//パネルに配置
		pr.add(b4);pr.add(b5);
	//パネルをセット
		this.add(pl,BorderLayout.WEST);
		this.add(pr,BorderLayout.EAST);
	//リセットボタン
		JButton clear=new JButton("CLEAR");
		clear.addActionListener(this);
		clear.setActionCommand("clear");
		this.add(clear,BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent e) {
		String es=e.getActionCommand();
		if(es.equals("red")) {
			panel.setPanelColor(Color.red);
		}
		if(es.equals("blue")) {
			panel.setPanelColor(Color.blue);
		}
		if(es.equals("orange")) {
			panel.setPanelColor(Color.orange);
		}
		if(es.equals("5")) {
			panel.setRadius(5/2);
		}
		if(es.equals("10")) {
			panel.setRadius(10/2);
		}
		if(es.equals("clear")) {
			panel.clear();
		}
	}
    public static void main(String argv[]) {
    	new CircleFrame();  
    }
}
