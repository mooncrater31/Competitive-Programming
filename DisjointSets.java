class Obj
{
	int rank ;
	Obj p ;
	Obj(Obj P,int Rank)
	{
		p = P ;
		rank = Rank ;
	}
}
public class DisjointSets
{
	void makeSet(Obj x)
	{
		x.p = x ;
		x.rank = 0 ;
	}
	void link(Obj x,Obj y)
	{
		if (x.rank>y.rank )
			y.p = x ;
		else
		{
			x.p = y ;
			if(x.rank == y.rank)
				y.rank = y.rank+1 ;
		}			
	}
	Obj findSet(Obj x)
	{
		if(x!=x.p)
			x.p = findSet(x.p) ;
		return x.p ;
	}
}