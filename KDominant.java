import java.util.* ;
public class KDominant
{
	int[] maxK = new int[26] ;
	ArrayList<Integer>[] V = new ArrayList[26] ;//delete the 2nd "<Integer>" if this doesn't work.
	void addToList(Character c,int pos)
	{
		int index = (int)c-97 ;
		if(V[index]!=null)
		{
			
			// int s = ++size[index] ;
			
			V[index].add(pos) ;
			if(V[index].size()==2)//two elements
			{
				// maxK[((int)character-97)] 
				maxK[index] = (V[index].get(0)+1) > (V[index].get(1)-V[index].get(0))?(V[index].get(0)+1):(V[index].get(1)-V[index].get(0)) ;
			}
			else if(V[index].size()>2)
			{
				int newDif = V[index].get(V[index].size()-1)-V[index].get(V[index].size()-2) ;
				if(maxK[index]<newDif)
				{
					maxK[index] = newDif ;
				}
			}
		}
		else
		{
			V[index] = new ArrayList<Integer>() ;
			V[index].add(pos) ;
		}
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		String s = in.next() ;
		KDominant K = new KDominant() ;
		
		for(int i=0;i<s.length();i++)
		{
			K.addToList(s.charAt(i),i) ;
		}
		for(int i=0;i<s.length() ; i++)
		{
			int index = (int)s.charAt(i)-97 ;
			int size = K.V[index].size() ;
			if(size>1 && s.length()-K.V[index].get(size-1)>K.maxK[index])
			{
				K.maxK[index] = s.length()-K.V[index].get(size-1) ;
			}
			
			// int size = K.V[i].size() ;
			// if(s.length()-K.V[i].get(size-1)-1>K.maxK[i])
			// {
				// K.maxK[i] = s.length()-K.V[i].get(size-1)-1 ;
			// }
		}
		int tempMax = (s.length()/2)+1 ;
		for(int i=0;i<K.maxK.length;i++)
		{
			if(K.maxK[i]!=0 && K.maxK[i]<tempMax )
			{
				tempMax = K.maxK[i] ;
				
			}
			// System.out.println("maxK["+i+"] = "+K.maxK[i]) ;
		}
		System.out.println(tempMax) ;
	}
}