import java.util.* ;

public class JamieAndAlarmSnooze
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		System.out.println(solve(in.nextInt(),in.nextInt(),in.nextInt())) ;
	}
	static int solve(int x,int h,int m)
	{
		int count=0;
		while(h%10!=7 &&h/10!=7&&m%10!=7&&m/10!=7)
		{
			// System.out.println(h+" "+m) ;
			m=m-x ;
			if(m<0)
			{
				m=m+60 ;
				h = h-1 ;
				if(h<0)
					h = 23 ;
			}
			count++ ;
		}
		return count ;
	}
}