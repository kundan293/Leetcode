#include <vector>
#include <string>
#include <algorithm>

using namespace std;

struct Node {
    char prefChar, suffChar;
    int prefLen, suffLen, maxLen;
    int len;

    Node() : prefChar(' '), suffChar(' '), prefLen(0), suffLen(0), maxLen(0), len(0) {}

    Node(char c) {
        prefChar = suffChar = c;
        prefLen = suffLen = maxLen = 1;
        len = 1;
    }
};

class SegmentTree {
private:
    int n;
    vector<Node> tree;

    Node merge(const Node& left, const Node& right) {
        Node res;
        res.len = left.len + right.len;

        res.prefChar = left.prefChar;
        res.prefLen = left.prefLen;
        if (left.prefLen == left.len && left.suffChar == right.prefChar) {
            res.prefLen = left.len + right.prefLen;
        }

        res.suffChar = right.suffChar;
        res.suffLen = right.suffLen;
        if (right.suffLen == right.len && left.suffChar == right.prefChar) {
            res.suffLen = right.len + left.suffLen;
        }

        res.maxLen = max(left.maxLen, right.maxLen);
        if (left.suffChar == right.prefChar) {
            res.maxLen = max(res.maxLen, left.suffLen + right.prefLen);
        }

        return res;
    }

    void build(const string& s, int node, int start, int end) {
        if (start == end) {
            tree[node] = Node(s[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(s, 2 * node, start, mid);
        build(s, 2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    void update(int node, int start, int end, int idx, char val) {
        if (start == end) {
            tree[node] = Node(val);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

public:
    SegmentTree(const string& s) {
        n = s.length();
        tree.resize(4 * n);
        build(s, 1, 0, n - 1);
    }

    void update(int idx, char val) {
        update(1, 0, n - 1, idx, val);
    }

    int getMax() const {
        return tree[1].maxLen;
    }
};

class Solution {
public:
    vector<int> longestRepeating(string s, string queryCharacters, vector<int>& queryIndices) {
        SegmentTree st(s);
        int k = queryIndices.size();
        vector<int> ans(k);

        for (int i = 0; i < k; ++i) {
            st.update(queryIndices[i], queryCharacters[i]);
            ans[i] = st.getMax();
        }

        return ans;
    }
};