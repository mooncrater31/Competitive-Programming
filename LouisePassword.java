import java.util.* ;
class LouisePassword
{
	
	public static void main(String args[])
	{
		String numbers = "0123456789" ;
	String lower_case = "abcdefghijklmnopqrstuvwxyz" ;
	String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ;
	String special_characters = "!@#$%^&*()-+" ;
	HashSet<Character> no = new HashSet<Character>() ;
	HashSet<Character> loca = new HashSet<Character>() ;
	HashSet<Character> upca = new HashSet<Character>() ;
	HashSet<Character> spca = new HashSet<Character>() ;
			
		for(int i=0;i<lower_case.length();i++)
		{
			loca.add(lower_case.charAt(i)) ;
		}
		for(int i=0;i<numbers.length();i++)
		{
			no.add(numbers.charAt(i)) ;
		}
		for(int i=0;i<upper_case.length();i++)
		{
			upca.add(upper_case.charAt(i)) ;
		}
		for(int i=0;i<special_characters.length();i++)
		{
			spca.add(special_characters.charAt(i)) ;
		}
		boolean lc = false,uc = false,n = false,sp = false ;
		Scanner in = new Scanner(System.in) ;
		int N = in.nextInt() ;
		String input = in.next() ;
		for(int i=0;i<input.length();i++)
		{
			char c = input.charAt(i) ;
			if(loca.contains(c))
				lc = true ;
			else if(no.contains(c))
				n = true ;
			else if(upca.contains(c))
				uc = true ;
			else if(spca.contains(c))
				sp = true ;
		}
		int alpha = lc?0:1,beta = n?0:1,gamma = uc?0:1,delta = sp?0:1 ;

		if(input.length()+alpha+beta+gamma+delta<6)
		{
			System.out.println(6-(input.length())) ;
		}
		else
		{
			System.out.println(alpha+beta+gamma+delta) ;
		}
			
	}		
}