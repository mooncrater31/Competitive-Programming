import java.util.* ;
import java.io.* ;
//removed the "remove" method's usage. But still TLE.
public class ColorfulPoints3
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		String S = br.readLine() ;
		int counter=0 ;
		ArrayList<Group> al = new ArrayList<Group>() ;
		for(int i=0;i<S.length();i++)
		{//O(n) time
			int j=i ;
			int grpSize=0 ;
			while(j+1!=S.length() && S.charAt(j)==S.charAt(j+1))
				j++ ;
			grpSize = j-i+1 ;
			Group g = new Group(grpSize,S.charAt(i)) ;
			i=j ;//as the i will be incremented by one
			al.add(g) ;//this is the reason why I'm using ArrayList nigga!
		}
		int groups = al.size() ;
		while(groups>1)
		{
			boolean firstFound = false ;
			int first =0 ;
			int last = al.size()-1 ;
			int minOps = Integer.MAX_VALUE,minIndex ;
			for(int i=0;i<al.size();i++)
			{
				
				if(al.get(i)!=null)
				{
					last = i ;
					if (!firstFound)
					{
						firstFound = true ;
						first = i ;
					}
					int sz = al.get(i).size ;
					if(i==0 || i==al.size()-1)
					{
						
						if(sz<minOps)
						{
							minOps = sz ;
							minIndex = i ;
						}
					}
					else
					{
						if((sz%2==1?sz/2+1:sz/2)<minOps)
						{
							minOps = sz%2==1?sz/2+1:sz/2 ;
							minIndex = i ;
						}
					}
				}
			}
			// System.out.println("minOps :"+minOps) ;
			counter = counter+minOps ;
			//number of operations needed to make atleast one group disappear = minOps.
			boolean frontDeleted = false ;
			for(int i=0;i<al.size();i++)
			{
				if(al.get(i)!=null)
				{
					if((!frontDeleted && i==first)||i==last)
					{
						int val = al.get(i).size-minOps ;
						if(val>0)
							al.get(i).size = val ;
						else
						{
							// System.out.println(i+" is removed") ;
							if(i==first)
								frontDeleted = true ;
							al.set(i,null) ;
							groups-- ;
							// i=i-1;
						}
					}
					else
					{
						int val = al.get(i).size-2*minOps ;
						if(val>0)
							al.get(i).size = val ;
						else
						{
							// System.out.println(i+" is removed") ;
							al.set(i,null) ;//O(n) complexity
							groups-- ;
							// i=i-1;
						}
					}
				}
			}
			// for(int i=0;i<al.size();i++)
			// {//checking if merging is needed, doing it if necessary
				// if(i+1!=al.size() && al.get(i).c==al.get(i+1).c)
				// {
					// al.get(i).size = al.get(i).size+al.get(i+1).size ;
					// al.remove(i+1) ;
					// i-- ;
				// }
			// }
			for(int i=0;i<al.size()-1;i++)
			{
				if(al.get(i)!=null)
				{
					int j=i+1 ;
					while(j!=al.size() && (al.get(j)==null || al.get(j).c==al.get(i).c))
					{
						
						if(al.get(j)!=null)
						{
							al.get(i).size = al.get(i).size+al.get(j).size ;
							al.set(j,null) ;
							groups-- ;
						}
						j++ ;
						
					}
					i=j-1 ;
				}
			}
		}
		System.out.println(counter) ;
	}
}
class Group
{
	int size ;
	char c ;
	Group(int s,char C)
	{
		this.size = s ;
		this.c = C ;
	}
}	
