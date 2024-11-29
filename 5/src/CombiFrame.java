import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class CombiFrame extends JFrame {
	public CombiFrame(){
	//左右に置くボタンのパネル
		JPanel  p1=new JPanel(),p2=new JPanel();
	//ボタンを収納する配列
		JButton[] blist=new JButton[15];
		for(int i=0;i<15;i++) {
			blist[i]=new JButton((i+1)+"");
		}
	//windowの名前
		this.setTitle("Panel Combination");
	//パネルの中のgrid配置
		p1.setLayout(new GridLayout(1,10));
		p2.setLayout(new GridLayout(5,1));
	//パネルの中にボタンを置く
		for(int i=0;i<10;i++) {
			p1.add(blist[i]);
		}
		for(int i=10;i<15;i++) {
			blist[i].setText("<html><span style='background:yellow;'>"+(i+1)+"</span></html>");
			p2.add(blist[i]);
		}
	//パネルをwindowに置く
		this.add(p1,BorderLayout.NORTH);
		this.add(p2,BorderLayout.WEST);
	//packでJframeのサイズを自動調整できるthis.setSize()と同じ役割
		this.pack(); 
	//おまじない
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//部品の可視化
		this.setVisible(true);
	}
	public static void main(String argv[]) {
		new CombiFrame();
	}
  }
