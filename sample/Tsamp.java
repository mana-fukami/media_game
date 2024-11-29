package sample;

class Tsamp {
	   public static void main(String[] args){
	      TurtleFrame f;             // TurtleFrame型の変数 f の宣言
	      f =  new TurtleFrame();    // TurtleFrame(表示フレーム)を生成しfに代入
	      Turtle m = new Turtle();   // Turtle(亀)を生成し，m の初期値として代入
	      f.add(m);                  // f(表示フレーム) に m(亀) を追加
	      m.rt(180);                 // 亀を下に向けます．(標準状態では上を向いています．) 
	      m.fd(50);                  // 前に 50 進め
	      m.lt(135);                 // 左に 135 度回り
	      m.fd(25);                  // 前に 25 進め
	      m.rt(180);                 // 右に 180 度回転 (方向転換)
	      m.up(); m.fd(25); m.down();// 前に ペンをあげて 25 進め
	      m.rt(90);                  // 右に 90度回転
	      m.fd(25);                  // 前に 25 進め
	      m.up(); m.fd(25); m.down();// 前に ペンをあげて 25 進め
	   }
	}   
