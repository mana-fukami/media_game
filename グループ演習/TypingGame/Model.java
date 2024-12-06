package TypingGame;

import java.util.Observable;

public class Model extends Observable {
	protected StringBuffer buffer=new StringBuffer();
	
	//-----待機画面での処理-----
	public void Stay() {}
	//-----ゲーム中の処理-----
	public void Game() {}
	public void Check() {}
	public void Time() {}
	public void Point() {}
	public void Correct() {}
	public void Miss() {}
	//-----結果表示での処理-----
	public void Result() {}
}
