import java.util.*;

class TestBoxing {
  public static void main(String argv[]){
    int sum=0;
    ArrayList<Integer> a = new ArrayList<Integer>();
    a.add(10);
    a.add(100);
    a.add(1000);
    for (int v: a){
      sum += v;
    }
    System.out.println(sum);
  }
}
