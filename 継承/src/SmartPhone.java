import java.util.Random;

interface Photo {
    void TakePhoto();
}

class Cellular implements Photo {
    public void MakeCall() {
      System.out.println("携帯電話で電話を掛けました．");
    }
    public void TakePhoto() {
      System.out.println("携帯電話で写真を撮りました．");
    }
}
 
class Camera implements Photo {
    public void TakePhoto() {
      System.out.println("デジカメで写真を撮りました．");
    }
}
 
class VideoCam implements Photo {
    public void RecordMovie() {
      System.out.println("ビデオカメラで動画を撮影しました．");
    }
    public void TakePhoto() {
      System.out.println("ビデオカメラで写真を撮りました．");
    }
}

class Android implements Photo{
	public void TakePhoto() {
		System.out.println("Androidで写真をとりました．");
	}
}

class Photographer{
	Photo[] photos=new Photo[20];
	int num=0;
//-----Photo配列にクラスを追加する-----
	public void add(Photo p) {
		photos[num]=p;
		num++;
	}
//-----Photo配列内の全てのクラスからTakePhotoを呼び出す-----
	public void TakeAll() {
		for(int i=0;i<photos.length&&photos[i]!=null;i++) {
			photos[i].TakePhoto();
		}
	}
	
}

class Smartphone{
	public static void main(String argv[]) {
	//必要な変数
		Random rm=new Random();
		Photographer photographer=new Photographer(); 
		int num=0;
	//Photoオブジェクトをaddする
		for(int i=1;i<=20;i++) {//20個分のクラスをadd
			num=rm.nextInt(4)+1;//1~4の乱数生成
			switch(num) {//乱数に合わせてPhotoオブジェクトをadd
				case 1:
					photographer.add(new Cellular());
					break;
				case 2:
					photographer.add(new Camera());
					break;
				case 3:
					photographer.add(new VideoCam());
					break;
				case 4:
					photographer.add(new Android());
					break;
			}
		}
	//写真を撮る
		photographer.TakeAll();
	}
}
