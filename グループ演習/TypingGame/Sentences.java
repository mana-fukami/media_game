package TypingGame;

import java.io.BufferedReader;
import java.io.File;

public class Sentences {
	private int num;//呼び出す問題番号
	private int difficulty;//難易度
	private int max=30;//最大問題数
	
	public Sentences() {//難易度の番号を引数にするといいかな？
		num=0;
		this.difficulty=1;
	}
	
	public String newSentence() {
		String sentence=null;
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
		sentence=PickSentence(filename);
		return sentence;
	}
	
	private String PickSentence(String filename) {
		String sentence=null;
		File file=new File(filename);//読みこむファイルの指定
		if(file.exists()) {//ファイルが存在するか確認
			BufferedReader bufferedreader=new BufferedReader(file);
			for(int i=0;i<num;i++) {
				sentence=bufferedreader.readLine();
				if(sentence==null) {
					num=1;
					sentence=bufferedreader.readLine();
				}
			}
		}else{//ファイルが存在しなければ
			System.out.println("ファイルが存在しません");//debug
		}
		return sentence;
	}
	

}
