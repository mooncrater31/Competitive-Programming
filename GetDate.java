import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class GetDate
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        System.out.println(solve(Integer.parseInt(S[0]),Integer.parseInt(S[1]),Integer.parseInt(S[2]))) ;
    }
    static int solve(int day,int month,int year)
    {
        int[] t = {0,3,2,5,0,3,5,1,4,6,2,4} ;
        //0,31,59,90,120,151,181,212,243,273,304,334
        //0,3,3,6,1,4,6,2,5,0,3,5 
        //0,3,2,5,0,3,5,1,4,6,2,4
        year = year-(month<3?1:0) ;
        return (year+year/4-year/100+year/400+t[month-1]+day)%7 ;
    }
}