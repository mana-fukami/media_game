package TypingGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

class Controller2 implements KeyListener,ActionListener{
    private GameView gameView;
    private Model model;
    /* 制限時間に関する変数 */
	private Timer timer;
	private int limit;//経過した時間

    public Controller2(GameView gameView, Model model) {
        this.gameView = gameView;
        this.model = model;
        // イベントリスナーを登録
        gameView.getInputField().addKeyListener(this);
        gameView.getEndButton().addActionListener(this);
        //timerの作成とスタート
        limit=60000;
        model.setTime(limit);
        timer=new Timer(1000,this);
        timer.start();
    }
    // キー入力
    @Override
    public void keyTyped(KeyEvent e) {
        char typedChar = e.getKeyChar(); 
        model.Check(typedChar); // 文字が入力されたらCheckを呼び出す
    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    // 終了ボタン
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameView.getEndButton()) {
            model.Result(); // 結果画面へ切り替える 
            timer.stop();
        }
        if(model.getTime()==0) {
        	model.Result();
        	timer.stop();
        }else {
        	limit-=1000;
        	model.setTime(limit);
        	gameView.timeLabel.setText("残り時間"+model.getTime()/1000+"秒");
        	//System.out.println(""+model.getTime());//debug
        }
    }
}
