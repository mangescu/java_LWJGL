
package a_star_pathfinding;

import java.util.ArrayList;


public class InOrderInsert {
    
    static ArrayList<Integer>   number = new ArrayList<Integer>();
    
    public static void main(String[] args) {
        
        
        InOrderInsert  test = new InOrderInsert();
        
        number.add(44);
        
        test.add(4);
        test.add(66);
        test.add(3);
        test.add(9);
        test.add(66);
        test.add(7);
        test.add(0);
        test.add(77);
        test.add(5);
        test.add(1);
        
        
         test.print();
    }
    
    public void add(int num){
      boolean ok = false;
      for(int i=0 ; i<number.size() ; i++)
      {
         if(num <= number.get(i))
         {
           number.add(i,num);
           ok = true;
           break;
         }
      }
      if(!ok){
          number.add(num);
      }
    }
    public void print(){
    
      for(int i=0 ; i<number.size() ; i++)
      {
          System.err.print("  "+number.get(i)+"   ");
      }
    }
    
}
