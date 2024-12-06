package TypingGame;

public class Main {
	public static int flag=0;//0:待機,1:ゲーム,2:結果
	//-----setter-----
	public void setFlag(int num) {flag=num;}
	//-----getter-----
	public int getFlag() {return flag;}
	//-----メイン関数-----
	public static void main(String[] args) {
		//モデルの生成
		Model model=new Model();
		
		//待機ビューの作成
		StayView stayview=new StayView();
		//stayviewに対応するコントローラーの作成
		Controller1 staycont=new Controller1();
		
		//ゲームビューの作成
		GameView gameview=new GameView();
		//gameviewに対応するコントローラーの作成
		Controller2 gamecont=new Controller2();
		
		//結果ビューの作成
		ResultView resultview=new ResultView();
		//resultviewに対応するコントローラーの作成
		Controller1 resultcont=new Controller1();
		switch(flag) {
		case 0:
			stayview.setVisible(true);
			break;
		case 1:
			gameview.setVisible(true);
			break;
		case 2:
			resultview.setVisible(true);
			break;
		}
	}

}
