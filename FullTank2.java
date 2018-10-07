import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner ;
class Edge {
    public int x, d;
}
class Rec {
    public int x, c, cost;
    public Rec(int x, int c, int cost){
        this.x = x; this.c = c; this. cost = cost;
    }
}
public class FullTank2 {
    static Scanner in = new Scanner(System.in);
    static int n, m, e;
    static int head[] = new int[1010], pre[] = new int [20010], to[] = new int[20010], w[] = new int[20010], pri[] = new int[20010] ;
    static void addEdge (int x, int y, int d) {
    to[e] = y; w[e] = d; pre[e] = head[x];head[x] = e++;
    }
    private static final boolean debug = true ;
    static void input() {
    n = in.nextInt(); 
    m = in.nextInt ();
    for (int i=0; i<n; ++i)
        pri[i] = in.nextInt();
    int u, v, d;
    e = 0;
    for (int i=0; i<n; ++i) head[i] = -1; 
    for (int i=1; i<=m; ++i) {
        u = in.nextInt(); 
        v = in.nextInt();
        d = in.nextInt ();
        addEdge(u, v, d);
        addEdge(v, u, d);
        }
    }
static int solve(int max, int s, int e) {
    PriorityQueue<Rec> que = new PriorityQueue<Rec>(100000,
        new Comparator<Rec>() {
            @Override
            public int compare (Rec o1, Rec o2){
                return o1.cost - o2.cost;
            }
        });
        
    int dis [][] = new int [n+10][110];
    for (int i=0; i<dis.length; ++i)
        for (int j=0; j<dis[i].length; ++j)
            dis[i][j] = 1000000000;
            
    que.add(new Rec(s, 0, 0));
    
    while (!que.isEmpty()) {
        Rec p = que.poll();
        //System.out.println (p.X + " " + p. if (p.X == e) return p. cost;
//+ " " + p. cost + "??");
    if(p.x==e) return p.cost ;
    
    for (int i=head [p. x]; i!=-1; i=pre[i]) {
        if(p.c>=w[i] && p.cost<dis[to[i]][p.c-w[i]]) {
            dis[to[i]][p.c-w[i]] = p.cost;
            que.add(new Rec(to[i], p.c-w[i],p.cost)) ;
        }
    }
    if(p.c<max && p.cost+pri[p.x]<dis[p.x][p.c+1]){
        dis[p.x][p.c+1]=p.cost + pri[p.x] ;
        que.add(new Rec(p.x, p.c+1, p.cost+pri[p.x])) ;
    }
    }
    return -1;
    }
    public static void main(String[] args)
	{
		input();
		int q = in.nextInt(), c, s, e, ans ;
		double tim[] = new double[q] ;
		double sum = 0 ;
		for (int i=1;i<=q; ++i) {
        c = in.nextInt();
        s = in.nextInt ();
        e = in.nextInt();
		double t1 = System.nanoTime() ;
        ans = solve(c, s, e); 
		double t2 = System.nanoTime() ;
		// if(debug) System.out.println(t2-t1) ;
		tim[i-1] = (t2-t1)/1000000000.0 ;
		sum+=tim[i-1] ;
        if(ans == -1)
			System.out.println("impossible") ;
		else
			System.out.println(ans);
	}
	if(debug) System.out.println("--->"+(sum/(1.0*q))) ;
}
}
