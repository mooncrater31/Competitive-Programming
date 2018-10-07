import java.util.* ;

public class tester
{
	public static void main(String args[])
	{
		HashSet<that_shit> H = new HashSet<that_shit>() ;
		that_shit ts1 = new that_shit(1,2) ;
		that_shit ts2 = new that_shit(3,4) ;
		H.add(ts1) ;
		H.add(ts2) ;
		System.out.println("H.size() :"+H.size()) ;
		H.add(new that_shit(1,2)) ;
		System.out.println("H.size() :"+H.size()) ;
		H.add(ts1) ;
		System.out.println("H.size() :"+H.size()) ;
		System.out.println("ts1.hashCode() :"+ts1.hashCode()+"ts2.hashCode() :"+ts2.hashCode()+" some random object1 :"+(new that_shit(5,6)).hashCode()+" some random object2 :"+(new that_shit(7,8)).hashCode()) ;
		
	}
	
}
class that_shit
{
	int a,b ;
	that_shit(int aa,int bb)
	{
		this.a = aa ;
		this.b = bb ;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true ;
		if(obj == null)
			return false ;
		if(!(obj instanceof that_shit))
			return false ;
		that_shit ts = (that_shit)obj ;
		return (ts.a == this.a && ts.b == this.b) ;
		}
	@Override
	public int hashCode()
	{
		return Objects.hash(a,b) ;
	}
}