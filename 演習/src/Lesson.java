import java.util.ArrayList;

class Lesson {
//----------各種変数----------
	private String name;//授業名
	private String teacher;
	private ArrayList<Student> st;//Student配列
	
//----------コンストラクタ----------
	public Lesson(String name,String teacher) {
		this.name=name;
		this.teacher=teacher;
		st=new ArrayList<Student>();//配列の確保
	}
	
//----------生徒の登録----------
	public void add(Student s) {
		for(int i=0;i<st.size();i++) {
			if(s.getId().compareTo(st.get(i).getId())<0) {
				st.add(i,s);
				return;
			}
		}
		st.add(s);//stが空or末尾まできたら
	}
	
//----------Lessonの情報を表示----------
	public void print() {
		System.out.println("Lesson　 :"+this.name);
		System.out.println("Teacher　:"+this.teacher);
		System.out.println("#Students:"+this.st.size());//受講者総数
		for(int i=0;i<st.size();i++) {
			st.get(i).print_short();
		}
		System.out.println("----------");
	}
}

class Student {
    private String id;    // 学籍番号
    private String name;  // 氏名
    private int grade;    // 成績

 //----------コンストラクタ----------
   public Student(String id,String name,int grade) {
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
//----------各種getter----------
   public String getId() {return this.id;}
   public String getName() {return this.name;}
   public int getGrade() {return this.grade;}
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

class main {
  public static void main(String argv[]){
  //インスタンスの作成
	  Lesson pro=new Lesson("Pro Enshu","Yanai");
  //生徒の登録
	  pro.add(new Student("012210","Enshu Shiro",60));
	  pro.add(new Student("012200","Dentsu Taro",80));
	  pro.add(new Student("012207","Chofu Saburp",70));
	  pro.add(new Student("012205","UEC Jiro",54));
  //Lessonの中身の表示
	  pro.print();
  }
}
