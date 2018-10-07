import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
UVa-1208:: Mooncrater 
Bugs :
#1: PriorityQueue not working properly. Working properly under the tester3.java file.
-> Ohkay. It's working properly.
#2: Size of EdgeList is 0.
-> Didn't initialize p in the constructor of UnionSet.
#3: Wrong answer for :
1
11
 0, 23, 8, 2, 23, 12, 19, 17, 7, 9, 1
 23, 0, 10, 24, 19, 2, 10, 10, 26, 21, 24
 8, 10, 0, 8, 0, 27, 22, 3, 2, 7, 7
 2, 24, 8, 0, 5, 23, 3, 9, 7, 2, 11
 23, 19, 0, 5, 0, 7, 19, 18, 20, 13, 23
 12, 2, 27, 23, 7, 0, 9, 26, 3, 6, 28
 19, 10, 22, 3, 19, 9, 0, 25, 19, 5, 4
 17, 10, 3, 9, 18, 26, 25, 0, 19, 5, 28
 7, 26, 2, 7, 20, 3, 19, 19, 0, 8, 20
 9, 21, 7, 2, 13, 6, 5, 5, 8, 0, 24
 1, 24, 7, 11, 23, 28, 4, 28, 20, 24, 0
My answer :
Case 1:
A-E 2
B-G 2
C-J 2
D-K 2
C-I 3
D-H 3
F-J 3
D-F 5
G-K 5
A-J 7
Correct Answer :
Case 1:
A-K 1
A-D 2
B-F 2
C-I 2
D-J 2
C-H 3
D-G 3
F-I 3
D-E 5
H-J 5
-> Solved. Input contains a "space" too. Took care of it.
#4 : Wrong answer for the input :
1
18
 0, 0, 13, 16, 11, 18, 13, 29, 15, 12, 8, 15, 25, 15, 16, 20, 13, 15
 0, 0, 22, 9, 13, 13, 15, 15, 14, 26, 17, 15, 18, 22, 2, 18, 27, 14
 13, 22, 0, 14, 27, 28, 4, 4, 21, 16, 8, 8, 23, 21, 28, 4, 25, 22
 16, 9, 14, 0, 18, 1, 23, 9, 18, 27, 7, 22, 10, 11, 15, 0, 7, 11
 11, 13, 27, 18, 0, 25, 6, 1, 18, 8, 27, 19, 16, 20, 9, 14, 2, 9
 18, 13, 28, 1, 25, 0, 5, 17, 23, 24, 24, 8, 12, 21, 24, 16, 9, 12
 13, 15, 4, 23, 6, 5, 0, 5, 5, 21, 24, 2, 22, 8, 23, 4, 17, 24
 29, 15, 4, 9, 1, 17, 5, 0, 22, 27, 29, 0, 25, 27, 19, 26, 15, 25
 15, 14, 21, 18, 18, 23, 5, 22, 0, 11, 11, 7, 22, 16, 20, 24, 16, 8
 12, 26, 16, 27, 8, 24, 21, 27, 11, 0, 5, 24, 22, 20, 25, 13, 25, 26
 8, 17, 8, 7, 27, 24, 24, 29, 11, 5, 0, 28, 7, 20, 6, 18, 13, 12
 15, 15, 8, 22, 19, 8, 2, 0, 7, 24, 28, 0, 21, 19, 3, 4, 10, 15
 25, 18, 23, 10, 16, 12, 22, 25, 22, 22, 7, 21, 0, 5, 26, 10, 14, 1
 15, 22, 21, 11, 20, 21, 8, 27, 16, 20, 20, 19, 5, 0, 23, 20, 8, 23
 16, 2, 28, 15, 9, 24, 23, 19, 20, 25, 6, 3, 26, 23, 0, 9, 25, 29
 20, 18, 4, 0, 14, 16, 4, 26, 24, 13, 18, 4, 10, 20, 9, 0, 15, 7
 13, 27, 25, 7, 2, 9, 17, 15, 16, 25, 13, 10, 14, 8, 25, 15, 0, 3
 15, 14, 22, 11, 9, 12, 24, 25, 8, 26, 12, 15, 1, 23, 29, 7, 3, 0
My answer :
Case 2:
D-F 1
E-H 1
M-R 1
B-O 2
E-Q 2
G-L 2
L-O 3
Q-R 3
C-G 4
C-H 4
C-P 4
L-P 4
F-G 5
G-I 5
M-N 5
K-O 6
K-M 7
A-K 8
Correct answer :
Case 2:
D-F 1
E-H 1
M-R 1
B-O 2
E-Q 2
G-L 2
L-O 3
Q-R 3
C-G 4
C-H 4
C-P 4
F-G 5
G-I 5
J-K 5
M-N 5
K-O 6
A-K 8
-> Counts an edge "L-P", which makes a cycle.
-> Row and column index mismatch. 
-> Solved.
#5: ArrayIndexOutOfBoundsException: 1::: Only taking 4 cases as input.
4th Input is:
1
11
 0, 21, 24, 18, 9, 4, 19, 27, 24, 11, 17
 21, 0, 8, 17, 3, 29, 13, 15, 20, 28, 27
 24, 8, 0, 21, 11, 25, 4, 20, 0, 15, 23
 18, 17, 21, 0, 21, 29, 10, 26, 16, 8, 2
 9, 3, 11, 21, 0, 0, 27, 14, 2, 29, 15
 4, 29, 25, 29, 0, 0, 15, 27, 4, 9, 6
 19, 13, 4, 10, 27, 15, 0, 18, 27, 19, 23
 27, 15, 20, 26, 14, 27, 18, 0, 14, 3, 4
 24, 20, 0, 16, 2, 4, 27, 14, 0, 13, 10
 11, 28, 15, 8, 29, 9, 19, 3, 13, 0, 26
 17, 27, 23, 2, 15, 6, 23, 4, 10, 26, 0
 -> Correct answer. No problem.
5th input is:
1
2
 0, 24
 24, 0
-> Yeah. Here is the problem.
-> Constructor of UnionFind had pq.size(), which is |vertices|-1. So, now it's pq.size()+1 to accomodate all the vertices.
#6 : Wrong answer for the input
1
21
 0, 2, 19, 10, 4, 11, 15, 13, 8, 0, 16, 21, 21, 18, 24, 20, 9, 13, 4, 11, 19
 2, 0, 26, 22, 4, 5, 11, 7, 0, 5, 4, 4, 20, 13, 0, 15, 28, 29, 9, 2, 27
 19, 26, 0, 18, 18, 21, 27, 29, 9, 15, 20, 28, 24, 5, 10, 3, 20, 0, 6, 18, 4
 10, 22, 18, 0, 0, 21, 20, 26, 22, 14, 10, 21, 19, 0, 18, 15, 7, 13, 24, 3, 29
 4, 4, 18, 0, 0, 11, 18, 3, 4, 14, 0, 8, 20, 20, 7, 5, 25, 8, 12, 9, 4
 11, 5, 21, 21, 11, 0, 15, 21, 3, 3, 18, 5, 10, 6, 3, 4, 8, 3, 28, 29, 21
 15, 11, 27, 20, 18, 15, 0, 5, 13, 18, 21, 13, 10, 27, 28, 15, 14, 21, 14, 29, 29
 13, 7, 29, 26, 3, 21, 5, 0, 9, 2, 27, 21, 13, 0, 24, 1, 12, 20, 4, 26, 16
 8, 0, 9, 22, 4, 3, 13, 9, 0, 22, 16, 19, 12, 7, 25, 1, 25, 0, 19, 11, 17
 0, 5, 15, 14, 14, 3, 18, 2, 22, 0, 17, 3, 18, 13, 2, 26, 20, 8, 28, 7, 10
 16, 4, 20, 10, 0, 18, 21, 27, 16, 17, 0, 9, 22, 3, 29, 11, 17, 0, 11, 23, 29
 21, 4, 28, 21, 8, 5, 13, 21, 19, 3, 9, 0, 4, 12, 10, 9, 0, 24, 10, 29, 7
 21, 20, 24, 19, 20, 10, 10, 13, 12, 18, 22, 4, 0, 11, 19, 13, 17, 27, 23, 28, 13
 18, 13, 5, 0, 20, 6, 27, 0, 7, 13, 3, 12, 11, 0, 28, 14, 26, 6, 17, 4, 11
 24, 0, 10, 18, 7, 3, 28, 24, 25, 2, 29, 10, 19, 28, 0, 27, 28, 11, 24, 0, 5
 20, 15, 3, 15, 5, 4, 15, 1, 1, 26, 11, 9, 13, 14, 27, 0, 22, 26, 23, 6, 16
 9, 28, 20, 7, 25, 8, 14, 12, 25, 20, 17, 0, 17, 26, 28, 22, 0, 1, 14, 13, 4
 13, 29, 0, 13, 8, 3, 21, 20, 0, 8, 0, 24, 27, 6, 11, 26, 1, 0, 28, 4, 15
 4, 9, 6, 24, 12, 28, 14, 4, 19, 28, 11, 10, 23, 17, 24, 23, 14, 28, 0, 26, 1
 11, 2, 18, 3, 9, 29, 29, 26, 11, 7, 23, 29, 28, 4, 0, 6, 13, 4, 26, 0, 2
 19, 27, 4, 29, 4, 21, 29, 16, 17, 10, 29, 7, 13, 11, 5, 16, 4, 15, 1, 2, 0
My answer :
Case 11:
H-P 1
I-P 1
Q-R 1
S-U 1
A-B 2
B-T 2
H-J 2
J-O 2
T-U 2
C-P 3
D-T 3
E-H 3
F-J 3
F-R 3
J-L 3
K-N 3
A-E 4
B-K 4
L-M 4
G-H 5
Correct Answer :
Case 11:
H-P 1
I-P 1
Q-R 1
S-U 1
A-B 2
B-T 2
H-J 2
J-O 2
T-U 2
C-P 3
D-T 3
E-H 3
F-I 3
F-R 3
J-L 3
K-N 3
A-E 4
B-K 4
L-M 4
G-H 5 
"F-J 3" in my answer, whereas "F-I 3" is the correct answer.
-> Solved. "F-J" should have lower precedence than "F-I". Therefore, allowed checking for equal this.a and t.a, inside the triplet class
and if true, then it returns this.b-t.b.
*/
public class Oreon
{
	private static final boolean debug = true ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int n = Integer.parseInt(bro.readLine()) ;
			Queue<Triplet> pq = new PriorityQueue<Triplet>() ;
			for(int i=0;i<n;i++)
			{
				String[] s = bro.readLine().split(" ") ;
				for(int j=i+1;j<n+1;j++)
				{
					int w = getNum(s[j]) ;
					
					if(w>0)
					{
						if(debug) System.out.println("Inputting : ("+i+","+(j-1)+","+w+")") ;
						pq.add(new Triplet(i,j-1,w)) ;
					}
				}
			}
			if(debug) 
			{
				System.out.println("Size of the priority queue :"+pq.size()) ;
				Queue<Triplet> temp = new PriorityQueue<Triplet>(pq) ;
				System.out.println("Contents of the priority queue :") ;
				while(temp.size()!=0)
				{
					Triplet tt = temp.poll() ;
					System.out.println(tt.a+" "+tt.b+" "+tt.w) ;
				}
				
			}
			List<Triplet> EdgeList = kruskal(pq) ;
			System.out.println("Case "+(t+1)+":") ;
			if(debug) System.out.println("Size of the EdgeList :"+EdgeList.size()) ;
			for(int i=0;i<EdgeList.size();i++)
			{
				Triplet tr = EdgeList.get(i) ;
				System.out.println((char)('A'+tr.a)+"-"+(char)('A'+tr.b)+" "+tr.w) ;
			}
		}
		
	}
	static int getNum(String s)
	{
		String[] s1 = s.split(",") ;
		return Integer.parseInt(s1[0]) ;
	}
	static List<Triplet> kruskal(Queue<Triplet> pq)
	{
		List<Triplet> EdgeList = new ArrayList<Triplet>() ;
		UnionFind U = new UnionFind(pq.size()+1) ;
		while(!(pq.size()==0))
		{
			Triplet t = pq.poll() ;
			if(debug) System.out.println(" Removed :"+t.a+" "+t.b+" "+t.w) ;
			if(!U.isSameSet(t.a,t.b))
			{
				if(debug) System.out.println("Working on :"+t.a+" "+t.b) ;
				EdgeList.add(t) ;
				U.unionSet(t.a,t.b) ;
			}
		}
		return EdgeList ;
	}
}
class Triplet implements Comparable<Triplet>
{
	int a,b,w ;
	Triplet(int at,int bt,int wt)
	{
		a = at ;
		b = bt ;
		w = wt ;
	}
	@Override
	public int compareTo(Triplet t)
	{
		// return this.w-t.w ;
		return (this.w==t.w)?(this.a==t.a?this.b-t.b:this.a-t.a):(this.w-t.w) ;//assuming a<b
	}
}
class UnionFind
{
	private int[] p,rank ;
	UnionFind(int n)
	{
		p = new int[n] ;
		rank = new int[n] ;
		for(int i=0;i<n;i++)
		{
			p[i] = i ;
		}
	}
	int findSet(int i)
	{
		return (p[i]==i)?i:(p[i] = findSet(p[i])) ;
	}
	boolean isSameSet(int i,int j)
	{
		return (findSet(i)==findSet(j)) ;
	}
	void unionSet(int i,int j)
	{
		int x = findSet(i),y = findSet(j) ;
		if(rank[x]>rank[y])
			p[y] = x ;
		else
		{
			p[x] = y ;
			if(rank[x]==rank[y])
				rank[y]++ ;
		}
	}
}