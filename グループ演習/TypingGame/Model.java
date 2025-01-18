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
	private String sentence;//ローマ字の文
	private String kanji;//日本語入力された文字数の文
	private int charnum;//入力された文字数
	private int length;//入力する文字列の長さ
	private char prevchar;//前に入力された文字
	public int difficulty=1;//難易度
	
	/*結果に関する変数*/
	private int miss;
	private int correct;
	private int points;
	
//-----待機画面での処理-----//
	public void Stay() {
		main.setFlag(0);
	}
	public void setDifficulty(int num) {
		difficulty=num;
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
        this.sentences = new Sentences(difficulty);
        makeSentence(); // 新しい問題を生成
    }
	/*問題の作成*/
	public void makeSentence() {
		charnum=0;
		prevchar='\n';
		sentences.newSentence();
		sentence=sentences.getSentence();
		kanji=sentences.getKanji();
		length=sentence.length();
		setChanged();
		notifyObservers();
	}
	public String getSentence() {
		String str=null;
		if(charnum>0) {
			str="<html><span style='color:white;'>"+sentence.substring(0,charnum)+"</span>"+
					"<span style='color:gray;'>"+sentence.substring(charnum,length)+"</span></html>";
		}else {
			str="<html><span style='color:gray'>"+sentence+"</span></html>";
		}
		return str;
	}
	public String getKanji() {
		String str="<html><span style='color:white;'>"+kanji+"</span></html>";
		return str;
	}
	/*入力文字列が合っているのかを調べる*/
	public void Check (char c) {
		char answer=sentence.charAt(charnum);
		if(charnum<length-1) {
			if(c==answer) {
				charnum++;prevchar=answer;
			}else if(prevchar=='s'&&answer=='i') {//si,shi
				if(c=='h') {
					sentence=sentence.substring(0,charnum)+"h"+sentence.substring(charnum);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(prevchar=='t'&&answer=='u') {//tu,tsu
				if(c=='s') {
					sentence=sentence.substring(0,charnum)+"s"+sentence.substring(charnum);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(prevchar=='s'&&answer=='y') {//sya,sha etc.
				if(c=='h') {
					sentence=sentence.substring(0,charnum)+"h"+sentence.substring(charnum+1);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(answer=='t'&&sentence.charAt(charnum+1)=='i'){//ti,chi
				if(c=='c') {
					prevchar=c;
					sentence=sentence.substring(0,charnum)+"ch"+sentence.substring(charnum+1);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(answer=='h'&&sentence.charAt(charnum+1)=='u'){//hu,fu
				if(c=='f') {
					prevchar=c;
					sentence=sentence.substring(0,charnum)+"f"+sentence.substring(charnum+1);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(answer=='z'&&sentence.charAt(charnum+1)=='i'){//zi,ji etc.
				if(c=='j') {
					prevchar=c;
					sentence=sentence.substring(0,charnum)+"j"+sentence.substring(charnum+1);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(answer=='z'&&sentence.charAt(charnum+1)=='y'){//zya,ja etc.
				if(c=='j') {
					prevchar=c;
					sentence=sentence.substring(0,charnum)+"j"+sentence.substring(charnum+2);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(answer=='t'&&sentence.charAt(charnum+1)=='y'){//tya,cha etc.
				if(c=='c') {
					prevchar=c;
					sentence=sentence.substring(0,charnum)+"ch"+sentence.substring(charnum+2);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(prevchar=='n'&&answer!='a'&&answer!='i'&&answer!='u'&&answer!='e'&&answer!='o'){
				if(c=='n') {
					sentence=sentence.substring(0,charnum)+'n'+sentence.substring(charnum);
					charnum++;length=sentence.length();
				}else{Miss();}
			}else {Miss();}
		}else {
			if(c==answer) {
				charnum++;prevchar=answer;
			}else if(prevchar=='s'&&answer=='i') {//si,shi
				if(c=='h') {
					sentence=sentence.substring(0,charnum)+"h"+sentence.substring(charnum);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(prevchar=='t'&&answer=='u') {//tu,tsu
				if(c=='s') {
					sentence=sentence.substring(0,charnum)+"s"+sentence.substring(charnum);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(prevchar=='s'&&answer=='y') {//sya,sha etc.
				if(c=='h') {
					sentence=sentence.substring(0,charnum)+"h"+sentence.substring(charnum+1);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(answer=='t'&&sentence.charAt(charnum+1)=='i'){//ti,chi
				if(c=='c') {
					prevchar=c;
					sentence=sentence.substring(0,charnum)+"ch"+sentence.substring(charnum+1);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(answer=='h'&&sentence.charAt(charnum+1)=='u'){//hu,fu
				if(c=='f') {
					prevchar=c;
					sentence=sentence.substring(0,charnum)+"f"+sentence.substring(charnum+1);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(answer=='z'&&sentence.charAt(charnum+1)=='i'){//zi,ji etc.
				if(c=='j') {
					prevchar=c;
					sentence=sentence.substring(0,charnum)+"j"+sentence.substring(charnum+1);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(answer=='z'&&sentence.charAt(charnum+1)=='y'){//zya,ja etc.
				if(c=='j') {
					prevchar=c;
					sentence=sentence.substring(0,charnum)+"j"+sentence.substring(charnum+2);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(answer=='t'&&sentence.charAt(charnum+1)=='y'){//tya,cha etc.
				if(c=='c') {
					prevchar=c;
					sentence=sentence.substring(0,charnum)+"ch"+sentence.substring(charnum+2);
					length=sentence.length();
					charnum++;
				}else {Miss();}
			}else if(prevchar=='n'&&answer!='a'&&answer!='i'&&answer!='u'&&answer!='e'&&answer!='o'){
				if(c=='n') {
					sentence=sentence.substring(0,charnum)+'n'+sentence.substring(charnum);
					charnum++;length=sentence.length();
				}else{Miss();}
			}else {Miss();}
		}
		setChanged();
		notifyObservers();
	}
	/*タイマーの管理*/
	public void setTime(int num) {
		Time+=num;
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
		setTime(length/5);//正解したら制限時間プラス
		correctSound();
		makeSentence();
	}
	public void Miss() {
		miss++;
		missSound();
	}
	
	/*効果音*/
	public void correctSound() {
		File file=new File("correct.wav");
	    try {
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
