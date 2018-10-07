import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class NamedExtensionDialing
{
	private static final boolean debug = false ;
	private static final boolean debug2 = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		String[] fNames,lNames,ext,q ;
		ArrayList<String> fN = new ArrayList<String>() ;
		ArrayList<String> lN = new ArrayList<String>() ;
		ArrayList<String> ex = new ArrayList<String>() ;
		ArrayList<String> qq = new ArrayList<String>() ;
		String S;
		for(S = bro.readLine();S.charAt(0)>=65;S = bro.readLine())
		{
			String[] arr = S.split(" ") ;
			fN.add(arr[0]) ;
			lN.add(arr[1]) ;
			ex.add(arr[2]) ;
		}
		for(String temp = S;!temp.equals("");temp = bro.readLine())
		{
			qq.add(temp) ;
		}
		String[] ans = solve(fN,lN,ex,qq) ;
		for(int i=0;i<ans.length;i++)
		{
			System.out.println(ans[i].equals("")?0:ans[i].trim()) ;
		}
	}
	static String[] solve(List<String> fNames,List<String> lNames,List<String> ext,List<String> q)//m->queries,n--> Extensions
	{
		HashSet<String> extHash = new HashSet<String>(ext) ;
		String codes[] = new String[fNames.size()] ;
		String ans[] = new String[q.size()] ;
		for(int i=0;i<ans.length;i++)
		{//O(m)
			ans[i] = "" ;
		}
		for(int i=0;i<fNames.size();i++)
		{
			codes[i] = String.valueOf(getCode(fNames.get(i).charAt(0))) ;//O(1) access,O(1) function call,O(1) conversion to string, O(n)
		}
		for(int i=0;i<lNames.size();i++)
		{//Goes through all characters of the last names. Unavoidable.
			for(int j=0;j<lNames.get(i).length();j++)
			{
				char temp = lNames.get(i).charAt(j) ;
				codes[i]+=getCode(((int)temp>=65&&(int)temp<=90)?temp:(char)(temp-32)) ;
			}
		}
		//codes have been created until now ;
		label:for(int i=0;i<q.size();i++)
		{
			if(q.get(i).length()==4)
			{										
				if(extHash.contains(q.get(i)))
				{
					ans[i] = q.get(i) ;
					continue label ;
				}
			}
			for(int j=0;j<codes.length;j++)
			{
				if(codes[j].startsWith(q.get(i)))
				{
					ans[i]+=ext.get(j)+' ' ;
				}
			}
		}
		return ans ;
	}
	static char getCode(char c)//In CAPS
	{
		if(c<'D')
			return '2' ;
		else if(c<'G')
			return '3' ;
		else if(c<'J')
			return '4' ;
		else if(c<'M')
			return '5' ;
		else if(c<'P')
			return '6' ;
		else if(c<'T')
			return '7' ;
		else if(c<'W')
			return '8' ;
		else return '9' ;
	}
}