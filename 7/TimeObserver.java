
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TimeObservable extends Observable implements ActionListener{
  private javax.swing.Timer timer;
  private int hour,min,sec;
  private String current_time;
  TimeObservable(){
	  timer=new javax.swing.Timer(1000,this);
	  timer.start();
  }
  public void setValue() {
	  Calendar cal = Calendar.getInstance(); 
	  hour= cal.get(Calendar.HOUR_OF_DAY); 
	  min = cal.get(Calendar.MINUTE); 
	  sec = cal.get(Calendar.SECOND); 
	  current_time = String.format("%02d:%02d:%02d",hour,min,sec);
	  setChanged();
	  notifyObservers();
  }
 //-----getter-----
  public String getValue(int lag) {
	  hour=(hour+lag+24)%24;
	  current_time=String.format("%02d:%02d:%02d",hour,min,sec);
	  return current_time;
  }
  
  public void actionPerformed(ActionEvent e) {
	  this.setValue();
  }
}

class TimeObserver extends JPanel implements Observer{
  private TimeObservable timeObservable;
  private String name;
  private int lag;
  private JLabel city,time;
  public TimeObserver(TimeObservable so,String name,int lag) {
	  this.lag=lag;
	  this.name=name;
	  timeObservable = so;
	  timeObservable.addObserver(this);
	  this.setLayout(new GridLayout(1,2));
	  Font font=new Font(Font.SANS_SERIF,Font.BOLD,26);
	  city=new JLabel(name,JLabel.CENTER);
	  time=new JLabel(so.getValue(lag),JLabel.CENTER);
	  city.setFont(font);
	  time.setFont(font);
	  this.add(city);
	  this.add(time);
  }
  public void update(Observable o,Object arg) {
	  time.setText(timeObservable.getValue(lag));
  }
}

class TimeObserverFrame extends JFrame {
    public TimeObserverFrame(){
      this.setTitle("Observer Frame");
      TimeObservable s= new TimeObservable();
      this.setLayout(new GridLayout(6,1));
      this.add(new TimeObserver(s,"Tokyo",0));
      this.add(new TimeObserver(s,"Beijing",-2));
      this.add(new TimeObserver(s,"Paris",-8));
      this.add(new TimeObserver(s,"London",-9));
      this.add(new TimeObserver(s,"New York",-14));
      this.add(new TimeObserver(s,"Los Angels",-17));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.setVisible(true);
    }
    public static void main(String argv[]) {
      new TimeObserverFrame();
   }
}
