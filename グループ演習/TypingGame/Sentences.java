package TypingGame;

public class Sentences {
	private int num;//呼び出す問題番号
	private int difficulty;//難易度
	private int max=4;//最大問題数
	private String[] sentences= {"aiueo","aiueo","kakikukeko","tachituteto"};
	
	Sentences() {//難易度の番号を引数にするといいかな？
		num=0;
		this.difficulty=1;
	}
	
	public String newSentence() {
		String sentence=null;
		switch(difficulty) {
		case 1:
			sentence=easy();
			break;
		case 2:
			break;
		case 3:
			break;
		}
		return sentence;
	}
	
	public String easy() {
		String sentence;
		sentence=sentences[num];
		num+=1;
		if(num>=max) {num=0;}
		return sentence;
	}

}
