
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

class StringObservable extends Observable {
  private String value = "";
  private int num=0;//何個目のフィールドに入力されたか
  public String getValue() {
    return value;
  }
  public int getNum() {
	  return num;
  }
  public void setValue(String s,int num) {
	  value=s;
	  this.num=num;
    setChanged();
    notifyObservers();
  }
}

class TextFieldObserver extends JTextField implements Observer,ActionListener {
  private StringObservable stringObservable;
  public int num;//自分が何個目のフィールドか
  public TextFieldObserver(StringObservable so,int num) {
	  this.num=num;
    stringObservable = so;
    stringObservable.addObserver(this);
    this.setFont(new Font(Font.SANS_SERIF,Font.BOLD,26)); // 文字を大きくします．
    addActionListener(this);
  }
  public void update(Observable o,Object arg) {
    String s = stringObservable.getValue();
    int j=stringObservable.getNum();
    j=this.num-j;
    j=Integer.parseInt(s)+j;
    s=Integer.toString(j);
    setText(s);
  }
  public void actionPerformed(ActionEvent e) {
   String s = getText();
   stringObservable.setValue(s,num);
  }
}

class ObserverFrame extends JFrame {
    public ObserverFrame(){
      this.setTitle("Observer Frame");
      int num=10;//入力フィールドの数
      this.setLayout(new GridLayout(num,1));
      StringObservable s= new StringObservable();
      TextFieldObserver text[]=new TextFieldObserver[num];
      for (int i = 0; i < num; i++) {
    	  text[i]=new TextFieldObserver(s,i);
    	  this.add(text[i]);
    	  s.setValue("0",0);
      }
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.setVisible(true);
    }
    public static void main(String argv[]) {
      new ObserverFrame();
   }
}
