package TypingGame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class Model extends Observable{
	/* 別ファイルのクラスをインスタンス化しておく */
	private Main main=new Main();
	private Sentences sentences; 
	
	/* 制限時間に関する変数 */
	private int Time;//経過した時間
	
	/* 文字列の管理に関する変数 */
	private String sentence;//ローマ字の文章
	private String kanji;//漢字の文
	private int charnum;//入力された文字数
	private int length;//文字列の長さ
	private char prevchar;//前の文字
	private char nextchar;//次の文字
	
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
		//System.out.println("Game()"); // debug
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
		prevchar='\n';
		nextchar='\n';
		sentences.newSentence();
		sentence=sentences.getSentence();
		kanji=sentences.getKanji();
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
	public String getKanji() {
		String str="<html><span style='color:black;'>"+kanji+"</span></html>";
		return str;
	}
	/*入力文字列が合っているのかを調べる*/
	public void Check (char c) {
		//System.out.println("checked");//debug
		char answer=sentence.charAt(charnum);
		nextchar=sentence.charAt(charnum+1);
		if(charnum<length-1) {
			if(c==answer) {charnum++;prevchar=answer;}
			else if(prevchar=='s'&&answer=='i') {
				if(c=='h') {sentence=sentence.substring(0,charnum)+"h"+sentence.substring(charnum);}
			}
			else {Miss();}
		}else {
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
		//System.out.println(Time);
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
		correctSound();
		makeSentence();
	}
	public void Miss() {
		miss++;
		//Point(-5);//ミスしたら5点減点
		missSound();
	}
	
	/*効果音*/
	public void correctSound() {
		File file=new File("correct.wav");
	    try {
	    	//System.out.println(file.getAbsolutePath());//debug
	        AudioInputStream originalStream = AudioSystem.getAudioInputStream(new File(file.getAbsolutePath()));
	        AudioFormat originalFormat = originalStream.getFormat();
	        //音声のフォーマットを合わせる。
	        AudioFormat targetFormat = new AudioFormat(
	            AudioFormat.Encoding.PCM_SIGNED,
	            originalFormat.getSampleRate(),
	            16, // 16-bit
	            originalFormat.getChannels(),
	            originalFormat.getChannels() * 2, // Frame size
	            originalFormat.getSampleRate(),
	            false // little-endian
	        );
	        AudioInputStream convertedStream = AudioSystem.getAudioInputStream(targetFormat, originalStream);
	        Clip clip = (Clip)AudioSystem.getClip();
	        clip.open(convertedStream);
	        clip.loop(0);
	        clip.flush();
	        while(clip.isActive()) {
	            Thread.sleep(100);
	        }
	    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	public void missSound() {
		File file=new File("miss.wav");
	    try {
	    	//System.out.println(file.getAbsolutePath());//debug
	        AudioInputStream originalStream = AudioSystem.getAudioInputStream(new File(file.getAbsolutePath()));
	        AudioFormat originalFormat = originalStream.getFormat();
	        //音声のフォーマットを合わせる。
	        AudioFormat targetFormat = new AudioFormat(
	            AudioFormat.Encoding.PCM_SIGNED,
	            originalFormat.getSampleRate(),
	            16, // 16-bit
	            originalFormat.getChannels(),
	            originalFormat.getChannels() * 2, // Frame size
	            originalFormat.getSampleRate(),
	            false // little-endian
	        );
	        AudioInputStream convertedStream = AudioSystem.getAudioInputStream(targetFormat, originalStream);
	        Clip clip = (Clip)AudioSystem.getClip();
	        clip.open(convertedStream);
	        clip.loop(0);
	        clip.flush();
	        while(clip.isActive()) {
	            Thread.sleep(100);
	        }
	    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
	        e.printStackTrace();
	    }
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
