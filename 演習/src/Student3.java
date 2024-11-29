class Student3 {
    private String id;    //学籍番号
    private String name;  //氏名
    private int grade;    //成績

 //----------各種setter----------
    void setId(String id) {
    	this.id=id;
    }
    
    void setName(String name) {
    	this.name=name;
    }
    
    void setGrade(int grade) {
    	this.grade=grade;
    }
//----------出力メソッド----------
    void print() {
      System.out.println("ID   : "+this.id);  
      System.out.println("Name : "+this.name);
      System.out.println("Grade: "+this.grade);
    }
    
}

class main3 {
  public static void main(String argv[]){
    Student3 st  = new Student3();
    Student3 st2 = new Student3();
    st.setId("01110");    st2.setId("01111");
    st.setName("Suzuki"); st2.setName("Yamada");
    st.setGrade(100);     st2.setGrade(80);

    st.print();       
    st2.print();
  }
}
