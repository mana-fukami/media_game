import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class RadixObservable extends Observable{
	private String fig="";
	private int rad=10;//何進数の数字が入力されたか
//-----setter-----
	public void setValue(String s,int num) {
		fig=s;
		rad=num;
		setChanged();
		notifyObservers();
	}
	public void setRad(int num) {rad=num;}
//-----getter-----
	public String getFig() {
		return fig;
	}
	public int getRad() {
		return rad;
	}
}

class TextObserver extends JTextField implements Observer,ActionListener{
	private RadixObservable radixObservable;
	public int rad;//自分が何進数か
	public TextObserver(RadixObservable so,int num) {
		this.rad=num;
		radixObservable = so;
		radixObservable.addObserver(this);
		this.setFont(new Font(Font.SANS_SERIF,Font.BOLD,26)); // 文字を大きくします．
		addActionListener(this);
	}
	public void update(Observable o,Object arg) {
		String s=radixObservable.getFig();//入力された数字
		int num=radixObservable.getRad();//入力された数字の進数
		num=Integer.parseInt(s,num);//入力進数の数字へ
		s=Integer.toString(num,rad);//自分の進数の文字列へ
		setText(s);
	}
	public void actionPerformed(ActionEvent e) {
		String s=getText();
		radixObservable.setValue(s,rad);
	}
}

class RadixFrame extends JFrame{
    public RadixFrame() {
    	this.setTitle("Radix Frame");
    	RadixObservable r=new RadixObservable();
    	TextObserver hex=new TextObserver(r,16);//16進数
    	TextObserver dec=new TextObserver(r,10);//10進数
    	TextObserver oct=new TextObserver(r,8);//8進数
    	TextObserver bin=new TextObserver(r,2);//2進数
    	this.setSize(200,200);
    	this.setLayout(new GridLayout(4,2));
    	this.add(new JLabel("16進数"));
    	this.add(hex);
    	this.add(new JLabel("10進数"));
    	this.add(dec);
    	this.add(new JLabel("8進数"));
    	this.add(oct);
    	this.add(new JLabel("2進数"));
    	this.add(bin);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
    }
    public static void main(String argv[]) {
    	new RadixFrame();
    }
}