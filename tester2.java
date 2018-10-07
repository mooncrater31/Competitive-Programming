public class tester2
{
	public static void main(String args[])
	{
		// String s = "(101)" ;
		// String[] s1 = s.split("\\(") ;
		// String[] s2 = s1[1].split("\\)") ;
		// System.out.println(s2[0]) ;
		from_here :for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				System.out.println(i+ " "+j) ;
				if(j==6)
					break ;//from_here ;
			}
			System.out.println("This is hidden from the continue ") ;
		}
	}
}