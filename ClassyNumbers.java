//Generating all numbers from 0 to 10^18 that are classy
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class ClassyNumbers
{
    private static final boolean debug=false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        List<Long> L = new ArrayList<Long>() ;
        generateNos(L,0,0,0) ;
        L.add((long)1e18) ;
        // Collections.sort(L) ;
        if(debug) System.out.println("L.size :"+L.get(15850)) ;
        for(int t=0;t<T;t++)
        {
            String[] S = bro.readLine().split(" ") ;
            long a = Long.parseLong(S[0]) ;
            long b = Long.parseLong(S[1]) ;
            int posa = Collections.binarySearch(L,a) ;
            int posb = Collections.binarySearch(L,b) ;
            if(debug) {System.out.println(" posa :"+posa+" posb :"+posb) ;}
            if(posa<0) posa = -posa-1 ;
            if(posb<0) posb = -posb-2 ;
            if(debug) {System.out.println(" posa :"+posa+" posb :"+posb) ;}
            System.out.println(posb-posa+1) ;
        }
    }
    static void generateNos(List<Long> L,int pos,int cnt,long curr)
    {
        if(pos==18)
        {
            L.add(curr) ;
            return ;
        }
        generateNos(L,pos+1,cnt,10*curr) ;
        if(cnt<3)
        for(int i=1;i<10;i++)
            generateNos(L,pos+1,cnt+1,10*curr+i) ;
    }
}