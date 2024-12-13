package TypingGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

public class Model extends Observable implements ActionListener {
	/* 別ファイルのクラスをインスタンス化しておく */
	private Main main=new Main();
	private Sentences sentences; 
	
	/* 制限時間に関する変数 */
	private Timer timer;
	private int limit;//経過した時間
	
	/* 文字列の管理に関する変数 */
	private StringBuffer buffer;//文字列バッファ
	private String sentence;//正解の文章
	private int charnum;//入力された文字数
	private int length;//文字列の長さ
	
	/*結果に関する変数*/
	public int miss;
	public int correct;
	public int points;
	
//-----待機画面での処理-----//
	public void Stay() {
		main.setFlag(0);
	}
	
//-----ゲーム中の処理-----//
	/*スタートしたら呼び出す*/
	public void Game() {
		main.setFlag(1);//ゲーム画面の表示に切り替える
		Time();//タイマーのスタート
		sentences=new Sentences();//呼び出す問題集をつくる
		miss=0;
		correct=0;
		points=0;
		makeSentence();
		setChanged();
		notifyObservers();
	}
	/*問題の作成*/
	public void makeSentence() {
		charnum=0;
		sentence=sentences.newSentence();
		buffer=new StringBuffer(sentence);//文字列バッファに正解をセット
		length=buffer.length();
	}
	public String getSentence() {
		return sentence;
	}
	/*入力文字列が合っているのかを調べる*/
	public void Check (char c) {
		char answer=buffer.charAt(charnum);
		if(answer!=c) {Miss();}
		if(charnum==length) {Correct();}
		else{charnum++;}
	}
	/*タイマーの管理*/
	public int getTime() {
		return limit;
	}
	public void Time() {
		timer=new Timer(1000,this);
		limit=0;
	}
	public void actionPerformed(ActionEvent e) {
		limit+=1000;//1秒ずつ経過する
		if(limit>60000) {//60秒で終了
			timer.stop();
			Result();//結果画面の表示に切り替える
		}
		setChanged();
		notifyObservers();
	}
	/*ポイントの管理*/
	public void Point(int num) {
		points+=num;
	}
	public void Correct() {
		correct++;
		Point(5);//正解したら10点追加
		makeSentence();
		setChanged();
		notifyObservers();
	}
	public void Miss() {
		miss++;
		Point(-5);//ミスしたら5点減点
		makeSentence();
		setChanged();
		notifyObservers();
	}
	
//-----結果表示での処理-----
	/*getter*/
	public int getPoints() {
		return points;
	}
	public int getCorrect() {
		return correct;
	}
	public int getMiss() {
		return miss;
	}
	/*画面切り替え*/
	public void Result() {
		main.setFlag(2);
	}
}
