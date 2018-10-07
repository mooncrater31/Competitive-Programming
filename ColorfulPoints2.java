import java.util.* ;
import java.io.* ;
//uses ArrayList. remove(i) is assholic, takes O(n) time :/ 
public class ColorfulPoints2
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		String S = br.readLine() ;
		int counter=0 ;
		ArrayList<Group> al = new ArrayList<Group>() ;
		for(int i=0;i<S.length();i++)
		{
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
		while(al.size()>1)
		{
			int minOps = Integer.MAX_VALUE,minIndex ;
			for(int i=0;i<al.size();i++)
			{
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
			// System.out.println("minOps :"+minOps) ;
			counter = counter+minOps ;
			//number of operations needed to make atleast one group disappear = minOps.
			boolean frontDeleted = false ;
			for(int i=0;i<al.size();i++)
			{
				if((!frontDeleted && i==0)||i==al.size()-1)
				{
					int val = al.get(i).size-minOps ;
					if(val>0)
						al.get(i).size = val ;
					else
					{
						// System.out.println(i+" is removed") ;
						if(i==0)
							frontDeleted = true ;
						al.remove(i) ;
						groups-- ;
						i=i-1;
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
						al.remove(i) ;//O(n) complexity
						groups-- ;
						i=i-1;
					}
				}
			}
			for(int i=0;i<al.size();i++)
			{//checking if merging is needed, doing it if necessary
				if(i+1!=al.size() && al.get(i).c==al.get(i+1).c)
				{
					al.get(i).size = al.get(i).size+al.get(i+1).size ;
					al.remove(i+1) ;
					i-- ;
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