import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MinimumValueRectangle
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            int n = Integer.parseInt(bro.readLine()) ;
            int[] A = new int[n] ;
            String[] S = bro.readLine().split(" ") ;
            int max = Integer.MIN_VALUE ;
            for(int i=0;i<n;i++) {A[i] = Integer.parseInt(S[i]) ;max = Math.max(A[i],max);}
            solve(A,max) ;
        }
    }
    static int[] countingSort(int[] A,int max)
    {
        int[] C = new int[max+1] ;
        int[] B = new int[A.length] ;
        for(int i=0;i<A.length;i++)
        {
            C[A[i]]++ ;
        }
        for(int i=1;i<=max;i++)
            C[i] += C[i-1] ;
        // System.out.println(Arrays.toString(C)) ;
        for(int i=A.length-1;i>=0;i--)//Makes sure it's inplace
        {
            B[C[A[i]]-1] = A[i] ;
            C[A[i]]-=1 ;
        }
        return B ;
        
    }
    static void solve(int[] A,int max)
    {
        int k = A.length; 
        if(max>k*(Math.log(k)/Math.log(2)-1))//Counting sort will be slower in this case
            Arrays.sort(A) ;
        else
            A = countingSort(A,max) ;
        List<Integer> group = new ArrayList<Integer>() ;
        int last = A[0] ;
        int count = 1 ;
        int a=1,b=1 ;
        for(int i=1;i<A.length;i++)
        {
            if(last==A[i])
            {
                count++ ;
            }
            else
            {
                count=1 ;
                last = A[i] ;
            }
            if(count==2)
            {
                group.add(A[i]) ;
                count= 0 ;
                int gs = group.size() ;
                if(gs==1)
                {
                    a = b = group.get(0) ;
                }
                if(gs==2)
                {
                    a = group.get(0) ;
                    b = group.get(1) ;
                    
                }
                if(gs>2)
                {
                    int a1 = group.get(gs-2) ;
                    int b1 = group.get(gs-1) ;
                    if(1L*a1*b>1L*a*b1)
                    {
                        a = a1 ;
                        b = b1 ;
                    }
                }
                if(debug)
                {
                    System.out.println("group :"+group.toString()+" a :"+a+" b :"+b) ;
                }
                
            }
        }
        // if(group.size()==1)
        // {
            // int temp = group.get(0) ;
            // System.out.println(temp+" "+temp+" "+temp+" "+temp) ;
        // }
        // else
        // {
            // int a=group.get(0),b=group.get(1) ;
            // for(int i=0;i<group.size()-1;i++)
            // {
                
                // int a1 = group.get(i) ;
                // int b1 = group.get(i+1) ;
                // if(1L*a1*b>1L*a*b1)
                // {
                    
                    // a = a1 ;
                    // b = b1 ;
                    // if(debug)
                    // {
                        // System.out.println("a :"+a+" b :"+b+" min :"+min) ;
                    // }
                // }
            // }
            // System.out.println(a+" "+a+" "+b+" "+b) ;
        // }
        System.out.println(a+" "+a+" "+b+" "+b) ;
    }
}