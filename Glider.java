import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Glider
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int h = Integer.parseInt(S[1]) ;
        int[] glide = new int[n] ;
        int[] gap = new int[n] ;
        int prevB=Integer.MIN_VALUE ;
        for(int i=0;i<n;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0]) ;
            if(i>0) gap[i-1]=a-prevB ;
            int b = Integer.parseInt(S[1]) ;
            prevB = b ;
            glide[i]=b-a ;
            
        }
        gap[n-1]=Integer.MAX_VALUE ;
        if(debug)
        {
            System.out.println("glide :"+Arrays.toString(glide)) ;
            System.out.println("gap :"+Arrays.toString(gap)) ;
        }
        System.out.println(solve(glide,gap,h)) ;
    }
    static int solve(int[] glide,int[] gap,int h)
    {
        int n = glide.length ;
        int maxans=0,ans = 0 ;
        int start = 0,end=-1 ;
        int tempH = h ;
        while(end<n-1 && tempH>0)
        {
            ans+=glide[++end] ;
            
            tempH-=gap[end] ;
            if(tempH>0) ans+=gap[end] ;
            // else ans+=gap[end]+tempH ;
        }
        maxans = ans+(end<n?gap[end]+tempH:0) ;
        tempH+=gap[end] ;
        if(debug)
        {
            System.out.println("end :"+end) ;
            System.out.println("tempH :"+tempH) ;
            System.out.println("ans :"+ans) ;
            System.out.println("maxans :"+maxans) ;
        }
        for(int i=1;i<n;i++)
        {
            ans-=glide[i-1] ;
            if(i-1!=end)
            {
                ans-=gap[i-1] ;
                tempH+=gap[i-1] ;
                tempH-=gap[end] ;
                if(tempH>=0) ans+=gap[end] ;
            }
            // else ans+=gap[end]+tempH ;
            while(end<n-1 && tempH>0)
            {
                ans+=glide[++end] ;
                tempH-=gap[end] ;
                if(tempH>0) ans+=gap[end] ;
                // else ans+=gap[end]+tempH ;   
            }
            maxans = Math.max(maxans,ans+(end<n?gap[end]+tempH:0)) ;
            tempH+=gap[end] ;
        }
        return maxans ;
    }
}