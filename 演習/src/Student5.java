class Student5 {
    private String id;    // 学籍番号
    private String name;  // 氏名
    private int grade;    // 成績

 //----------コンストラクタ----------
   public Student5(String id,String name,int grade) {
    	this.id=id;
    	this.name=name;
    //gradeの値チェック
    	if(grade<0) {
    		grade=0;
    	}else if(grade>=100) {
    		grade=100;
    	}
    	this.grade=grade;
    }
//----------出力メソッド----------
    void print_short() {
    	System.out.println(this.id+","+this.name+","+this.grade);
    }
   
    void print() {
      System.out.println("ID   : "+this.id);  
      System.out.println("Name : "+this.name);
      System.out.println("Grade: "+this.grade);
    }
    
}

class main5 {
  public static void main(String argv[]){
  //インスタンスの作成
	  Lesson pro=new Lesson("Pro Enshu","Yanai",100);
  //生徒の登録
	  pro.add(new Student("012200","Dentsu Taro",80));
	  pro.add(new Student("012205","UEC Jiro",54));
	  pro.add(new Student("012207","Chofu Saburp",70));
	  pro.add(new Student("012210","Enshu Shiro",60));
  //Lessonの中身の表示
	  pro.print();
  }
}
