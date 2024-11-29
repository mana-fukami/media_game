
public class Furui {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int[] figure=new int[201];
		for(int i=1;i<=200;i++) {
			figure[i]=i;
		}
		figure[1]=0;//1は素数じゃないから0にしておく
		boolean flag=true;
		int i=2;
		while(flag==true&&i<=200) {//書き換えられている間繰り返す
			if(figure[i]!=0) {//figureの中身が0じゃない→その数の倍数が入ってるかを調べる。
				flag=find(i,figure);
			}
			i++;
		}
		int j=0;
		for(i=1;i<=200;i++) {
			if(figure[i]!=0) {
				System.out.print(figure[i]+", ");
				j++;
				if(j%10==0) {
					System.out.println();
				}
			}
		}
		System.out.println();
	}
	
	static boolean find(int n,int[] figure) {//nの倍数を0に書き換える
		boolean flag=false;
		for(int i=1;i<=200;i++) {
			if(figure[i]%n==0&&figure[i]/n!=1) {//商が1なら素数だから除外する
				figure[i]=0;
				flag=true;
			}
		}
		return flag;//書き換えがあったら→true,なかったら→false
	}

}
