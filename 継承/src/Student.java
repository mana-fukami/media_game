class Student {
	private String id;
	private String name;
	private int grade;

//-----setter-----
	public void setId(String id) {
		this.id=id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setGrade(int grade) {
		this.grade=grade;
	}
//-----出力-----
	public void print() {
		System.out.println("ID        :"+this.id);
		System.out.println("Name      :"+this.name);
		System.out.println("Grade     :"+this.grade);
	}

}

class Student3 extends Student{//Studentを継承
	private String course;
	
//-----setter-----
	public void setCourse(String course) {
		this.course=course;
	}
//-----出力(オーバーライド)-----
	@Override
	public void print() {
		super.print();
		System.out.println("Course    :"+this.course);
	}
	
}

class Student4 extends Student3{
	private String supervisor;

//-----setter-----
	public void setSupervisor(String supervisor) {
		this.supervisor=supervisor;
	}
//-----出力(オーバーライド)-----
	@Override
	public void print() {
		super.print();
		System.out.println("Supervisor:"+this.supervisor);
	}
}

class Main {
	public static void main(String argv[]) {
		Student3 st3=new Student3();
		Student4 st4=new Student4();
//-----各種設定-----
	//3年次
		st3.setId("0000000");
		st3.setName("電通太郎");
		st3.setGrade(60);
		st3.setCourse("メディア情報学");
	//4年次
		st4.setId("0000001");
		st4.setName("電通次郎");
		st4.setGrade(60);
		st4.setCourse("経営情報学");
		st4.setSupervisor("電通三郎");
//-----出力-----
	//3年次
		System.out.println("[3年生]");
		st3.print();
	//4年次
		System.out.println("[4年生]");
		st4.print();
	}
}
