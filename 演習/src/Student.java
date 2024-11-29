class Student1 {
    public String id;    // 蟄ｦ邀咲分蜿ｷ
    public String name;  // 蜷榊燕
    public int grade;    // 謌千ｸｾ

    public void print() {
      System.out.println("ID   : "+id);  
      System.out.println("Name : "+name);
      System.out.println("Grade: "+grade);
    }
    
}

class main2 {
  public static void main(String argv[]){
    Student1 st  = new Student1();
    Student1 st2 = new Student1();
    st.id="01110";    st2.id="01111";
    st.name="Suzuki"; st2.name="Yamada";
    st.grade=100;     st2.grade=80;

    st.print();       
    st2.print();
  }
}
