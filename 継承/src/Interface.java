
	interface A {              
	    public void f();       
	}                          
	 
	class B1 implements A {    
	    public void f() { }    
	    public void f1() { }   
	}
	 
	class B2 implements A {    
	    public void f1() { }   
	}                          
	 
	class C {                  
	    public void g(A a1) {  
	      a1.f();             
	      a1.f1();            
	      g(a1);              
	    }                      
	    public void h() {      
	      B1 b1 = new B1();   
	      b1.f();             
	      b1.f1();            
	      g(b1);              
	      A a2 = b1;          
	      a2.f();             
	      a2.f1();            
	      g(a2);              
	    }                      
	}  
