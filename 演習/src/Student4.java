class Student4 {
    private String id;    //学籍番号
    private String name;  //名前
    private int grade;    //成績

 //----------まとめてset----------
    void set(String id,String name,int grade) {
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
    void print() {
      System.out.println("ID   : "+this.id);  
      System.out.println("Name : "+this.name);
      System.out.println("Grade: "+this.grade);
    }
    
}

class main4 {
  public static void main(String argv[]){
    Student4 st  = new Student4();
    Student4 st2 = new Student4();
    st.set("01110","Suzuki",107);
    st2.set("01111","Yamada",-10);
    st.print();       
    st2.print();
  }
}
