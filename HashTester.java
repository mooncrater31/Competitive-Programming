import java.util.* ;
class HashTester
{
	public static void main(String args[])
	{
		Hashtable<Integer,Boolean> ht = new Hashtable<Integer,Boolean>() ;
		ht.put(2,true) ;
		ht.put(1,true) ;
		for(Map.Entry m:ht.entrySet()){  
   System.out.println(m.getKey()+" "+m.getValue());  
		
	}
	}
}