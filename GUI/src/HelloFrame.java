import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

class HelloFrame extends JFrame implements ActionListener {
	private JButton b;
	private JLabel label;
	private int count=1;
	private Timer timer;
	
    public HelloFrame(){
      this.setSize(300,200);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //ボタンの作成
      b = new JButton(this.count+"回目");
      this.add(b,BorderLayout.SOUTH);
      //ラベルの作成
      label=new JLabel("",JLabel.CENTER);
      this.add(label,BorderLayout.CENTER);
      //おみくじアクション
      b.addActionListener(this);
      this.setVisible(true);
      //タイマーのセット:1000㎳ごとにおみくじを引く
      timer=new Timer(1000,this);
      timer.start();
    }

   public void actionPerformed(ActionEvent ev) {
	   double r=Math.random();
	   String text=null;
	   String pic=null;
	   if(r>0.7) {
		   text="<html><img src='file:good.png' width=70 height=70><span style='font-size:40pt;color:red;'>good</span></html>";
	   }else if(r>0.2) {
		   text="<html><img src='file:soso.png' width=70 height=70><span style='font-size:40pt;color:green;'>so so</span></html>";
	   }else {
		   text="<html><img src='file:bad.png' width=70 height=70><span style='font-size:40pt;color:blue;'>bad</span></html>";
	   }
	   this.label.setText(text);
	   count++;
	   b.setText(this.count+"回目");
   }

    public static void main(String argv[]) {
      new HelloFrame();
   }
}
