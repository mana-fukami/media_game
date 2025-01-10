package TypingGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Sentences {
	private int num;//呼び出す問題番号
	private int difficulty=1;//難易度
	private int max=24;//最大問題数
	private String sentence;
	private String kanji;
	private String filename;
	
	public Sentences() {//難易度の番号を引数にするといいか
		filename=null;
		num=1;
		switch(difficulty) {
		case 1:
			filename="uec.txt";
			break;
		case 2:
			break;
		case 3:
			break;
		}
	}
	//-----getter-----//
	public String getSentence() {
		return sentence;
	}
	
	public String getKanji() {
		return kanji;
	}
	
	public void newSentence() {
		//System.out.println("generate");//debug
		String str;
		int id=-1;
		try {
			//System.out.println("str");//debug
			File file=new File(filename);//読みこむファイルの指定
			file=new File(file.getAbsolutePath());
			if(file.exists()) {
				//System.out.println("generated");//debug
				/*ファイルを行ごとに読みこむ準備*/
				FileReader filereader=new FileReader(file);
				BufferedReader bufferedreader=new BufferedReader(filereader);
				for(int i=0;i<num;i++) {
					str=bufferedreader.readLine();
					//System.out.println("generated");//debug
					id=str.indexOf(",",0);
					if(id!=-1) {
						kanji=str.substring(0,id);
						sentence=str.substring(id+1);
					}else {
						System.out.println("error");
					}
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
