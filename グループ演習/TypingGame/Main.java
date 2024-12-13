package TypingGame;

public class Main {
	public static StayView stayview;
	public static GameView gameview;
	public static ResultView resultview;
	
	public static int flag=0;//0:待機,1:ゲーム,2:結果
	//-----setter-----
	public void setFlag(int num) {
		flag=num;
		viewChange();
		//System.out.println(flag);
	}
	//-----getter-----
	public int getFlag() {return flag;}
	//-----メイン関数-----
	public static void main(String[] args) {
		//モデルの生成
		Model model=new Model();
		
		//待機ビューの作成
		/*StayView*/ stayview=new StayView();
		//stayviewに対応するコントローラーの作成
		Controller1 staycont=new Controller1(stayview,model);
		
		//ゲームビューの作成
		/*GameView*/ gameview=new GameView(model);
		//gameviewに対応するコントローラーの作成
		Controller2 gamecont=new Controller2(gameview,model);
		
		//結果ビューの作成
		/*ResultView*/ resultview=new ResultView();
		//resultviewに対応するコントローラーの作成
		Controller3 resultcont=new Controller3(resultview,model);
		viewChange();
	}
	public static void viewChange() {
		switch(flag) {
		case 0:
			stayview.setVisible(true);
			break;
		case 1:
			stayview.setVisible(false);
			gameview.setVisible(true);
			break;
		case 2:
			gameview.setVisible(false);
			resultview.setVisible(true);
			break;
		}
	}

}
