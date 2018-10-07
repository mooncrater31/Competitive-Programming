#include <algorithm>
#include <fstream>
#include <iostream>
#include <vector>
using namespace std;

const int inf = 0x3f3f3f3f, kMaxN = 100005;

#define int64 long long

vector<pair<int, int> > ev;

int main() {
	ios::sync_with_stdio(false);
	int n; cin >> n;
	for (int i = 1; i <= n; ++i) {
		int x, h; cin >> x >> h;
		ev.push_back(make_pair(x, h));
	}
	sort(ev.begin(), ev.end());
	int last = -2 * inf;
	int rez = 0;
	for (int i = 0; i < int(ev.size()); ++i) {
		auto itr = ev[i];
		if (itr.first - itr.second > last) {
			++rez;
			last = itr.first;
		} else if ((i + 1 == int(ev.size())) or (itr.first + itr.second < ev[i + 1].first)) {
			++rez;
			last = itr.first + itr.second;
		} else {
			last = itr.first;
		}
	}
	cout << rez << '\n';
	return 0;
}