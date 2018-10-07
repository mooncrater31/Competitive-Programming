import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;
class Temp 
{
    String S ;
    int val ;
    Temp(String S,int v)
    {
        this.S = S ;
        this.val = v ;
    }
}
public class Permutation
{
    static int top=-1,tail=-1 ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        String[] S = bro.readLine().split(" ") ;
        char[] inp = new char[N] ;
        char[] inpSorted = new char[N] ;
        
        for(int i=0;i<N;i++)
        {
            inp[i]=S[i].charAt(0) ;
            inpSorted[i]=inp[i];
        }
        mergeSort(inpSorted,0,N-1) ;
        String sorted = new String(inpSorted) ;
        Temp[] q = new Temp[(int)1e6] ;
        HashSet<String> visited = new HashSet<String>() ;
        enq(q,new Temp(new String(inp),0)) ;
        
        while(top!=-1)
        {
            Temp temp = deq(q) ;
            if(!visited.contains(temp.S))
            {
                visited.add(temp.S) ;
                if(temp.S.equals(sorted))
                {
                    System.out.println(temp.val) ;
                    break ;
                    
                }
                else 
                {
                    for(int i=1;i<N;i++)
                    {
                        enq(q,new Temp(reverse(temp.S.substring(0,i+1))+(i+1==N?"":temp.S.substring(i+1)),temp.val+1)) ;
                    }
                }
            }
        }
        
    }
    static String reverse(String S)
    {
        char[] ch = S.toCharArray() ;
        char[] rev = new char[S.length()] ;
        for(int i=0;i<S.length();i++)
        {
            rev[i]=ch[S.length()-1-i] ;
        }
        return new String(rev) ;
    }
    static void mergeSort(char[] inp,int p,int r)
    {
        if(p<r)
        {
            int q = (p+r)/2 ;
            mergeSort(inp,p,q) ;
            mergeSort(inp,q+1,r) ;
            merge(inp,p,q,r) ;
        }   
    }
    static void merge(char[] inp,int p,int q,int r)
    {
        int n1 = q-p+1 ;
        int n2 = r-q ;
        char[] L = new char[n1+1] ;
        char[] R = new char[n2+1] ;
        for(int i=0;i<n1;i++) L[i]=inp[p+i] ;
        for(int i=0;i<n2;i++) R[i]=inp[q+i+1] ;
        L[n1] = Character.MAX_VALUE ;
        R[n2] = Character.MAX_VALUE ;
        int i=0,j=0 ;
        for(int k=p;k<=r;k++)
        {
            if(L[i]<R[j])
                inp[k]=L[i++] ;
            else inp[k]=R[j++] ;
        }
        
    }
    static void enq(Temp[] q,Temp val)
    {
        q[++top]=val ;
        if(top==0) tail++ ;
    }
    static Temp deq(Temp[] q)
    {
       Temp ret = q[tail++] ;
       if(tail>top) {top=-1 ;tail=-1 ;}
       return ret ;
    }
}