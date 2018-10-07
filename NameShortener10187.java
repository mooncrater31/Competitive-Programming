import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class NameShortener10187
{
	public static void main(String args[]) throws Exception 
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		// int T  = Integer.parseInt(bro.readLine()) ;
		//Assuming T = 1 
		int M = Integer.parseInt(bro.readLine()) ;
		
		HashMap<String,String> H = new HashMap<String,String>() ;
		String[][] S  = new String[M][] ;
		for(int i=0;i<M;i++)
		{
			S[i] = bro.readLine().split(" ") ;
			for(int k=0;k<2;k++)
			{
				if(!H.containsKey(S[i][k]))
				{
					
					// if(!H.containsValue(S[i][0].substring(0,1))) 
						// H.put(S[i][0],S[i][0].substring(0,1)) ;
					// else if(!H.containsValue(S[i][0].substring(0,2))) 
						// H.put(S[i][0],S[i][0].substring(0,2)) ;
					// if(!H.containsValue(S[i][0].substring(0,1))) 
						// H.put(S[i][0],S[i][0].substring(0,1)) ;
					for(int j=1;!H.containsKey(S[i][k]);j++)
					{
						if(!H.containsValue(S[i][k].substring(0,j)))
							H.put(S[i][k],S[i][k].substring(0,j)) ;
					}
					
				}
			}
			
		}
		System.out.println(M) ;
		for(int i=0;i<M;i++)
		{
			System.out.println(H.get(S[i][0])+" "+H.get(S[i][1])+" "+S[i][2]+" "+S[i][3]) ;
		}
		//For getting the nodes
		// List<String> L = new ArrayList<String>() ;
		HashSet<String> HH = new HashSet<String>() ;
		for(int i=0;i<M;i++)
		{
			for(int j=0;j<=1;j++)
				HH.add(S[i][j]) ;
		}
		List<String> L = new ArrayList<String>(HH) ;
		Collections.sort(L) ;
		System.out.println("Names:") ;
		for(int i=0;i<L.size();i++)
		{
			System.out.println(H.get(L.get(i))) ;
		}
	}
}