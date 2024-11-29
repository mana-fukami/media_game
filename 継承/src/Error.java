interface I { }
class S { }
class C extends S implements I { }
 
class AssignTest {
    I i;
    S s;
    C c;
    void g1(I i) { }
    void g2(S s) { }
    void g3(C c) { }
    void f() {
        i = new I(); // 13
        s = new I(); // 14
        c = new I(); // 15
        i = new S(); // 16
        s = new S(); // 17
        c = new S(); // 18
        i = new C(); // 19
        s = new C(); // 20
        c = new C(); // 21
        g1(i); // 22
        g2(i); // 23
        g3(i); // 24
        g1(s); // 25
        g2(s); // 26
        g3(s); // 27
        g1(c); // 28
        g2(c); // 29
        g3(c); // 30
    }
}