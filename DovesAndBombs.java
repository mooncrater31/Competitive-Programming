import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
Assumptions :
1. Assumed the inner sort as a stable sort
2. Nodes in the same component will have the same dfs_low value.
-> Seems like this assumption f*cked me up.
-> Yeah it did. Now counting children by the number of nodes which told that the current node is an "articulation point". 
-> pigeon_value = children+numberofComponents 
Sample Input :
153 115
0 1
0 2
0 3
0 4
0 5
0 40
0 98
0 129
0 134
0 141
1 2
1 3
1 4
1 5
1 6
1 7
1 8
1 53
2 3
2 4
2 5
2 6
2 7
2 15
2 51
3 4
3 5
3 6
3 7
3 143
3 144
4 5
4 6
4 7
4 8
4 142
5 6
5 46
5 81
5 93
6 7
6 8
6 9
6 10
6 11
7 8
7 9
7 10
7 11
8 9
8 10
8 11
8 12
8 150
9 10
9 11
9 12
9 13
9 14
9 15
10 11
10 12
10 13
10 14
10 15
11 12
11 13
11 14
11 137
12 13
12 14
12 15
12 16
12 151
13 14
13 15
13 16
13 17
13 18
14 15
14 16
14 17
14 138
15 16
15 17
15 116
15 128
16 5
16 17
16 43
16 69
16 72
17 18
17 19
17 20
17 21
17 22
18 19
18 20
18 21
18 22
18 23
18 45
18 132
19 20
19 21
19 22
19 23
19 24
19 25
19 26
20 21
20 22
20 23
20 24
20 25
20 26
21 22
21 23
21 24
21 25
21 139
22 23
22 24
22 25
22 26
23 24
23 25
23 26
23 27
24 25
24 26
24 27
24 28
25 26
25 109
25 122
26 22
26 27
28 68
28 111
29 60
31 108
32 17
32 42
32 74
32 125
33 117
38 32
38 83
38 145
39 34
40 29
40 77
41 65
43 49
43 86
43 148
44 24
44 35
44 47
44 147
45 10
46 3
46 73
46 96
46 136
47 48
50 52
50 62
50 75
50 78
51 26
51 64
51 101
51 124
52 14
56 11
60 61
62 90
62 149
65 59
69 76
69 104
69 113
71 121
72 21
72 44
72 50
72 89
72 127
73 12
73 71
74 54
76 13
76 114
79 31
79 36
79 152
80 37
80 79
81 88
82 27
83 33
83 97
84 100
84 120
85 39
87 105
89 1
89 110
92 4
92 84
92 87
93 91
94 20
95 18
95 119
96 85
98 70
100 41
104 19
104 28
104 95
106 82
107 2
109 67
110 115
113 80
113 130
117 131
119 23
123 99
124 56
125 123
127 30
127 58
127 102
128 57
129 16
129 118
129 133
129 135
133 146
134 25
134 38
134 92
139 103
141 8
141 63
142 106
143 9
143 94
143 126
144 107
144 112
145 7
146 55
148 140
151 66
-1 -1
0 0
*/
public class DovesAndBombs
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int cases = 0;
		for(String S = bro.readLine() ;;S= bro.readLine())
		{
			String[] s = S.split(" ") ;
			int n = Integer.parseInt(s[0]) ;
			int m = Integer.parseInt(s[1]) ;
			if(n==0 && m==0)
			{
				if(debug)System.out.println("Broke at :"+cases) ;
				break ;
			}
			int[][] M = new int[n][n] ;
			for(String s1 = bro.readLine() ;; s1 = bro.readLine())
			{
				String[] s2 = s1.split(" ") ;
				int a = Integer.parseInt(s2[0]) ;
				int b = Integer.parseInt(s2[1]) ;
				if(a==-1 && b==-1)
				{
					break ;
				}
				
					M[a][b] = 1 ;
					M[b][a] = 1 ;
			}
			
			
			DovesAndBombs D = new DovesAndBombs(n) ;
			int add = 0 ;
			for(int i=0;i<n;i++)
			{
				if(!D.visited[i])
				{
					add++ ;
					D.dfs_root = i ;
					// D.root_children = 0;
					D.articulationPoints(i,M) ;
					// if(D.root_children<=1)
						// D.artiPoints[D.dfs_root] = false ;
				}
			}
			List<Pair> pigeon_value = new ArrayList<Pair>() ;
			for(int i=0;i<n;i++)
			{
				// HashSet<Integer> H = new HashSet<Integer>() ;
				// if(D.artiPoints[i])
				// {
					// for(int j=0;j<n;j++)
					// {
						// if(M[i][j]==1)
						// {
							// H.add(D.dfs_low[j]) ;
						// }
					// }
					// pigeon_value.add(new Pair(H.size(),i)) ;
				// }
				// else
				// {
					// pigeon_value.add(new Pair(1,i)) ;
				// }
				pigeon_value.add(new Pair(D.child[i]+add,i)) ;
			}
			Collections.sort(pigeon_value) ;
			if(debug)
			{
				cases++ ;
				System.out.println("Case #"+cases) ;
			}
			//assuming it to be a stable sort
			
			for(int i=0;i<m;i++)
			{
				int node = pigeon_value.get(i).b, pv = pigeon_value.get(i).a ;
				if(!debug)
					System.out.println(node+" "+pv) ;
				if(debug)
				{
					System.out.print(node+" "+pv) ;
					System.out.println("	From "+node) ;
					for(int j=0;j<M[node].length;j++)
					{
						if(M[node][j]==1)
						{
							System.out.print("		to "+j+" : "+D.dfs_low[j]) ;
						}
					}
					System.out.println() ;
				}
			}
			System.out.println() ;
			
		}
	}
	int root_children = 0, dfs_root,dfs_iteration = 0 ;
	int[] dfs_parent,dfs_num,dfs_low,child;
	boolean[] visited,artiPoints ;
	
	DovesAndBombs(int n)
	{
		dfs_parent = new int[n] ;
		dfs_num = new int[n] ;
		dfs_low = new int[n] ;
		visited = new boolean[n] ;
		artiPoints = new boolean[n] ;
		child = new int[n] ;
		// pigeon_value = new int[n] ;
	}
	void articulationPoints(int v,int[][] M)
	{
		visited[v] = true ;
		dfs_low[v] = dfs_num[v] = dfs_iteration++ ;
		int children = 0;
		for(int i=0;i<M[v].length;i++)
		{
			if(M[v][i]==1)
			{
				if(visited[i])
				{
					if(dfs_parent[v]!=i)
						dfs_low[v] = Math.min(dfs_low[v],dfs_num[i]) ;
				}
				else
				{
					children++ ;
					dfs_parent[i] = v ;
					articulationPoints(i,M) ;
					if(v==dfs_root)
					{
						child[v] = Math.max(child[v],children-1) ;
						// root_children++ ;
					artiPoints[v] = true ;
					}
					else if(v!=dfs_root && dfs_low[i]>=dfs_num[v])
					{
						child[v]++ ;
						artiPoints[v] = true ;
					}
					
					// if(dfs_low[i]>=dfs_num[v])
						// artiPoints[v] = true ;
					dfs_low[v] = Math.min(dfs_low[v],dfs_low[i]) ;
				}
			}
		}
	}
	
}
class Pair implements Comparable<Pair>
{
	int a,b ;
	Pair(int ta,int tb)
	{
		this.a = ta ; 
		this.b = tb ;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true ;
		if(obj==null)
			return false ;
		if(!(obj instanceof Pair))
			return false ;
		Pair p = (Pair)obj ;
		return (this.a==p.a && this.b == p.b) ;
	}
	@Override
	public int hashCode()
	{
		return Objects.hash(this.a,this.b) ;
	}
	@Override
	public int compareTo(Pair p2)
	{
		return p2.a - this.a ;
	}
}