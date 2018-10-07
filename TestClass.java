/* IMPORTANT: class must not be public. */

/*
 * uncomment this if you want to read input.
import java.io.BufferedReader;
import java.io.InputStreamReader;
*/
import java.util.* ;

class TestClass {
    public static void main(String args[] ) throws Exception {
        

        int[] A = {9,8,4,10,14,18,0,1,17} ;
        List<obj> L = new ArrayList<obj>() ;
        for(int i:A)
            L.add(new obj(i)) ;
        Collections.sort(L) ;
        for(obj o:L)
        {
            System.out.print(o.a+" ") ;
        }
        System.out.println() ;
    }
}
class obj implements Comparable<obj>
{
    int a ;
    obj(int a)
    {
        this.a = a ;
    }
    public int compareTo(obj o)
    {
        if(this.a>o.a)//Current element is more than input 
        {
            return 1 ;
        }
        else return -1 ;
    }
}
