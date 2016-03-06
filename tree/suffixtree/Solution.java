import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        SuffixTree st = new SuffixTree();
        st.addString("hello");
        st.buildSuffixTree("hello");
        System.out.println(st.contains("llo"));
    }
}


class SuffixTree {
	Node root;
	public SuffixTree() {
		root = new Node((char)0);
	}

	public void addString(String str) {
		Node curr = root;
		int i = 0;
		while (i < str.length() && curr != null) {
			curr = curr.addChild(str.charAt(i));
			i++;
		}
	}

	public void buildSuffixTree(String str) {
		for (int i = 0; i < str.length() - 1; i++) {
			addString(str.substring(i, str.length()));
		}
	}

	public int contains(String str) {
		int i = 0;
		Node curr = root;
		while (i < str.length() && curr != null) {
			curr = curr.getChild(str.charAt(i));
			i++;
		}
		return i;
	}

	public Node getNext(Node n, char c) throws IllegalStateException {
		if (n == null) throw new IllegalStateException();
		n = n.getChild(c);
		return n;
	} 

}


class Node {
	char c;
	int numBelow;
	int numHere;
	HashMap<Character, Node> children;

	public Node(char c) {
		children = new HashMap<Character, Node>(3);
		this.c = c;
		numHere = 1;
	}

	public Node getChild(char c) {
		if (children.containsKey(c))
			return children.get(c);
		return null;
	}

	public Node addChild(char c) {
		Node child;
		if (!children.containsKey(c)) {
			child = new Node(c);
			children.put(c, child);
			numBelow++;
		}
		else {
			child = getChild(c);
			numHere++;
		}
		return child;

	}

	public boolean hasChild() {
		return children.size() > 0;
	}
}