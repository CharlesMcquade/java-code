import java.util.HashMap;
class SuffTree {
	SuffixNode root;

	public SuffTree() {
		this.root = new SuffixNode((char)0);
	}

	public void addString(String str) {
		SuffixNode curr = root;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			SuffixNode next = null;
			if (curr.hasChild(c)) {
				next = curr.getChild(c);
				next.freq++;
			}
			else {
				next = curr.addChild(c);
			}
			curr = next;
		}
	}

	private class SuffixNode {
		int freq;
		char c;
		HashMap<Character, SuffixNode> children;
		public SuffixNode(char c) {
			this.c = c;
			this.freq = 1;
			this.children = new HashMap<Character, SuffixNode>();
		}

		public boolean hasChild(char c) {
			return children.containsKey(c);
		}

		public boolean isLeaf() {
			return children.size() == 0;
		}

		public SuffixNode getChild(char c) {
			return children.get(c);
		}

		// adds child and returns that child
		public SuffixNode addChild(char c) {
			SuffixNode newChild = new SuffixNode(c);
			children.put(c, newChild);
			return newChild;
		}
	}
}

