package TypingGame;

class Model extends Observable{
	/* 別ファイルのクラスをインスタンス化しておく */
	private Main main=new Main();
	private Sentences sentences; 
	
	/* 制限時間に関する変数 */
	private int Time;//経過した時間
	
	/* 文字列の管理に関する変数 */
	private String sentence;//正解の文章
	private int charnum;//入力された文字数
	private int length;//文字列の長さ
	
	/*結果に関する変数*/
	private int miss;
	private int correct;
	private int points;
	
//-----待機画面での処理-----//
	public void Stay() {
		main.setFlag(0);
	}
	
//-----ゲーム中の処理-----//
	/*スタートしたら呼び出す*/
	public void Game() {
		System.out.println("Game()"); // debug
        main.setFlag(1);
        resetGame(); // ゲームの状態をリセット
	}
	public void resetGame() {
        this.Time = 60; // 制限時間を初期化
        this.miss = 0;
        this.correct = 0;
        this.points = 0;
        this.sentences = new Sentences();
        makeSentence(); // 新しい問題を生成
        setChanged();
        notifyObservers();
    }
	/*問題の作成*/
	public void makeSentence() {
		charnum=0;
		sentence=sentences.newSentence();
		length=sentence.length();
		//System.out.println(length);//debug
		//System.out.println("makesentence");//debug
		setChanged();
		notifyObservers();
	}
	public String getSentence() {
		String str=null;
		if(charnum>0) {
			str="<html><span style='color:black;'>"+sentence.substring(0,charnum)+"</span>"+
					"<span style='color:gray;'>"+sentence.substring(charnum,length)+"</span></html>";
		}else {
			str="<html><span style='color:gray'>"+sentence+"</span></html>";
		}
		return str;
	}
	/*入力文字列が合っているのかを調べる*/
	/*複数の入力方式がある文字
	 * し si,shi
	 * ち ti,chi
	 * つ tu,tsu
	 * ふ hu,fu
	 * ん n,nn
	 */
	public void Check (char c) {
		//System.out.println("checked");//debug
		if(charnum<length-1) {
			char answer=sentence.charAt(charnum);
			if(c==answer) {charnum++;}
			else {Miss();}
		}else {
			char answer=sentence.charAt(charnum);
			if(answer!=c) {Miss();}
			else{Correct();}
		}
		setChanged();
		notifyObservers();
		//System.out.println(charnum);//debug
	}
	/*タイマーの管理*/
	public void setTime(int num) {
		Time+=num;
		System.out.println(Time);
	}
	public int getTime() {
		return Time;
	}
	/*ポイントの管理*/
	public void Point(int num) {
		points+=num;
	}
	public void Correct() {
		correct++;
		Point(5);//正解したら10点追加
		makeSentence();
	}
	public void Miss() {
		miss++;
		Point(-5);//ミスしたら5点減点
		makeSentence();
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
