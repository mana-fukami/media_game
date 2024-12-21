package TypingGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Sentences {
	private int num;//呼び出す問題番号
	private int difficulty;//難易度
	private int max=4;//最大問題数
	private String sentence;
	
	public Sentences() {//難易度の番号を引数にするといいかな？
		num=1;
		this.difficulty=1;
	}
	
	public String newSentence() {
		String filename=null;
		switch(difficulty) {
		case 1:
			filename="easy.txt";
			break;
		case 2:
			break;
		case 3:
			break;
		}
		PickSentence(filename);
		return sentence;
	}
	
	private void PickSentence(String filename) {
		try {
			File file=new File(filename);//読みこむファイルの指定
			file=new File(file.getAbsolutePath());
			if(file.exists()) {
				/*ファイルを行ごとに読みこむ準備*/
				FileReader filereader=new FileReader(file);
				BufferedReader bufferedreader=new BufferedReader(filereader);
				for(int i=0;i<num;i++) {
					sentence=bufferedreader.readLine();
				}
				num++;
				if(num>max) {
					num=1;
				}
				bufferedreader.close();//ファイルを閉じる
			}else {
				//"C:\Users\mana\Desktop\大学\メディア演習\グループ演習\TypingGame\easy.txt"
				System.out.println("ファイルが存在しません\n"+file);
				return;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	

}
