#include<bits/stdc++.h>

using namespace std;

#define F first
#define S second
#define mp make_pair
#define pb push_back
#define int long long
#define ll long long
#define pii pair <int , int>
#define ld long double
#define for2(a,b,c) for(int (a) = (b); (a) < (c); (a)++)
#define for3(a,b,c) for(    (a) = (b); (a) < (c); (a)++)
#define setp cout << fixed << setprecision(15)
#define endl "\n"
#define minit(a,b) a = min(a,b)
#define maxit(a,b) a = max(a,b)
#define PII pair<int,int>
#define Vec vector<int>
#define error(x) cerr << #x << " = " << (x) << endl;
#define all(x) (x).begin() , (x).end()

const int maxn = 400000+200;
vector<int> adj[maxn];
bool seen[maxn];
int cnt;
void dfs(int root){
    seen[root] = 1;
    for(auto x : adj[root]) if(!seen[x]) dfs(x);
}

int32_t main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n,m,q; cin >> n >> m >> q;
    for2(i,0,q){
        int x,y; cin >> x >> y;
        x--,y--;
        y += 200000;
        adj[x].pb(y);
        adj[y].pb(x);
    }
    for2(i,0,n) if(!seen[i]) dfs(i),cnt++;
    for2(i,0,m) if(!seen[i+200000]) dfs(i+200000),cnt++;
    cout << cnt-1 << endl;
    return 0;
}