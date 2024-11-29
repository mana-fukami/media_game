
class TestPolymorph {
  public static void main(String argv[]) {
    A a; B b; C c;
    a = new A(); a.g();
    b = new B(); b.g();
    c = new C(); c.g();
    a = new B(); a.g();
    a = new C(); a.g();
  }
}

class A {
  public void f1() {
    System.out.println("f1 A");
  }
  public void f2() {
    System.out.println("f2 A");
  }
  public void g() {
    f1();
    f2();
  }
}

class B extends A {
  public void f1() {
    System.out.println("f1 B");
  }
}

class C extends B {
  public void f2() {
    System.out.println("f2 C");
  }
}
