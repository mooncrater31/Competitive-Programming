import java.util.* ;
class SVO
{
	int s ;
	int f ;
	int v ;
	int index ;
	SVO(int s1,int f1,int v1, int index1)
	{
		s = s1 ;
		f = f1 ;
		v = v1 ;
		index = index1 ;
	}
	
}
class SchedulingValue
{
	
	SVO[] mergeSSort(SVO so[],int p,int r)
	{
		if(p==r)
			return so ;
		int q = (p+r)/2 ;
		so = mergeSSort(so,p,q) ;
		so = mergeSSort(so,q+1,r) ;
		so = mergeS(so,p,q,r) ;
		return so ;
		
	}
	SVO[] mergeS(SVO so[],int p,int q,int r)
	{
		Integer inf =  Integer.MAX_VALUE ;
		int n1 = q-p+2 ;
		int n2 = r-q+1 ;
		int i,j ;
		SVO L[] = new SVO[n1] ;
		SVO R[] = new SVO[n2] ;
		for(i=0;i<n1-1;i++)
		{   
			L[i] = so[p+i] ;
		}
		for(j=0;j<n2-1;j++)
		{
			
			R[j] = so[q+1+j] ;
		}
		L[n1-1] = new SVO(inf,0,0,0) ;
		R[n2-1] = new SVO(inf,0,0,0) ;
		int x = p ;
		i=0;
		j=0 ;
		while(x!=r+1)
		{
			if(L[i].s<=R[j].s )
			{
				so[x] = L[i] ;
				i++ ;
			}
			else
			{
				so[x] = R[j] ;
				j++ ;
			}
			x++ ;
		}
		return so ;
	}
		

	
	int[] sValue(SVO so[])
	{
		int n = so.length ;
		so = mergeSSort(so,0,n-1) ;
		for(int i=0;i<so.length;i++)
		{
			System.out.print(i+"-->"+so[i].s+" "+so[i].f+" "+so[i].v+" "+so[i].index+"\n") ;
		}
		System.out.println() ;
		int[] MV = new int[n] ;
		int[] NE = new int[n] ;
		for(int i=0;i<n;i++)
		{
			MV[i] = so[i].v ;
			NE[i] = i ;
		}
		for(int i = n-1 ;i>=0;i--)
		{ 	
			 for(int j=i+1;j<n;j++)
			 {
				 if(so[i].f<=so[j].s)
				 {
					 if(MV[i]<=so[i].v+MV[j])
					 {
					  MV[i] = so[i].v+MV[j] ;
					  NE[i] = j ;
					 }
				 }
			 }
		}
		for(int i=0;i<n;i++)
		{
			System.out.print(MV[i]+" ") ;
		}
		System.out.println() ;
		for(int i=0;i<n;i++)
		{
			System.out.print(NE[i]+" ") ;
		}
		System.out.println() ;
		int max_index = 0 ;
		for(int i=0;i<n;i++)
		{
			if(MV[max_index]<=MV[i])
			{
				max_index = i ;
			}
			
		}
		System.out.println("max_index is: "+max_index) ;
		int[] res1 = new int[n] ;
		int k = 0 ;
		int i ;
		for( i=max_index;NE[i]!=i;i = NE[i])
		{
			res1[k++] = so[i].index ;
		}
		res1[k] = so[i].index ;
		int[] res = new int[k+1] ;
		for(int j=0;j<=k;j++)
		{
			res[j] = res1[j] ;
		}
		
		return res ;
	}
	public static void main(String args[])
	{
		SchedulingValue S = new SchedulingValue() ;
		SVO so1 = new SVO(1,4,5,1) ;
		SVO so2 = new SVO(3,5,6,2) ;
		SVO so3 = new SVO(0,6,1,3) ;
		SVO so4 = new SVO(5,7,2,4) ;
		SVO so5 = new SVO(3,9,3,5) ;
		SVO so6 = new SVO(5,9,9,6) ;
		SVO so7 = new SVO(6,10,1,7) ;
		SVO so8 = new SVO(8,11,8,8) ;
		SVO so9 = new SVO(8,12,7,9) ;
		SVO so10 = new SVO(2,14,6,10) ;
		SVO so11 = new SVO(12,16,2,11) ;
		SVO so[] = {so1,so2,so3,so4,so5,so6,so7,so8,so9,so10,so11} ;
		int[] res = S.sValue(so) ;
		System.out.println("The activities are:") ;
		for(int i=0;i<res.length;i++)
		{
			System.out.print(res[i]+" ") ;
		}
	}
}