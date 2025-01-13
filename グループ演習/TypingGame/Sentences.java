package TypingGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Sentences {
	private int num;//呼び出す問題番号
	private int max;//最大問題数
	private String sentence;
	private String kanji;
	private String filename;
	
	public Sentences(int difficulty) {//難易度の番号を引数にするといいか
		filename=null;
		num=1;
		switch(difficulty) {
		case 0:
			max=2;
			filename="test.txt";
			break;
		case 1:
			max=24;
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
			File file=new File(filename);//読みこむファイルの指定
			file=new File(file.getAbsolutePath());
			if(file.exists()) {
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
				System.out.println("ファイルが存在しません\n"+file);
				return;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
