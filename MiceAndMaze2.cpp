#include <bits/stdc++.h> 
#define ll long long
#define mx 105
#define inf 10000000000000000OLL
using namespace std;

struct node
{
	
	int x, w;
	node (int _x, int _w)
	{
		this->x = _x; this->w = _w
	}
};
vector <node> vt[mx]; ll dist[mx];
void dis(int n, int e, int t)
{
	set <int> pq; set <int> :: iterator it; 
	for (int i = 0; i < mx; i++) dist[i] = inf;
	pq.insert(e); dist[e] = 0; 
	while (!pq. empty())
	{
		it = pq.begin(); int tp = *it; pq.erase(it); 
		for (unsigned i = 0; i < vt[tp].size(); i++)
		{
			node x = vt[tp][i];
			if (dist [x.x] > dist[tp] + vt [tp][i].w) {
			dist[x.x] = dist[tp] + vt[tp][i].w ;
			pq.insert(x.x);
			}
		}
	}
}

int main()
{

	int tc, m, n, e, t, x, y, w, cs = 0;
	cin >> tc;
	while (tc--)
	{
		
		cin >> n >> e >> t >> m;
		if(cs++)
			cout << '\n' ;
		while (m--)
		{
			cin >> x >> y >> w;
			vt[y].push_back (node(x,w));
		}
		dis(n,e,t) ;
		int cnt = 0 ;
		for(int i=0;i<mx;i++)
		{
			if(dist[i]<=t) cnt++ ;
		}
		cout << cnt << '\n' ;
		for(int i=0;i<mx;i++)
		{
			if(vt[i].size())
				vt[i].clear() ;
		}
	}
	return 0 ;
}