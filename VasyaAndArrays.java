import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class VasyaAndArrays
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int n = Integer.parseInt(bro.readLine()) ;
        int[] A = new  int[n] ;
        String[] S = bro.readLine().split(" ") ;
        for(int i=0;i<n;i++) A[i]=Integer.parseInt(S[i]) ;
        int m = Integer.parseInt(bro.readLine()) ;
        S = bro.readLine().split(" ") ;
        int[] B = new int[m] ;
        for(int i=0;i<m;i++) B[i]=Integer.parseInt(S[i]) ;
        System.out.println(solve(A,B)) ;
        
    }
    static int solve(int[] A,int[] B)
    {
        int i=0,j=0 ;
        int n = A.length,m=B.length ;
        long tempA = A[0],tempB = B[0] ;
        int count=0 ;
        boolean done=true ;
        while(i<n && j<m)
        {
            if(debug) System.out.println("tempA :"+tempA +" : tempB :"+tempB) ;
            if(tempA==tempB)
            {
                count++ ;
                i++ ;
                j++ ;
                if(i==n && j==m)
                {
                    done=true ;
                    break ;
                    
                }
                else if(i==n || j==m)
                {
                    done=false ;
                    break ;
                }
                tempA=A[i];
                tempB=B[j];
            }
            else if(tempA<tempB)
            {
                i++ ;
                
                if(i==n)
                {
                    done=false ;
                    break ;
                }
                tempA+=A[i] ;
            }
            else 
            {
                j++ ;
                
                if(j==m)
                {
                    done=false ;
                    break ;
                }
                tempB+=B[j] ;
            }
        }
        if(!done) return -1 ;
        return count ;
    }
}