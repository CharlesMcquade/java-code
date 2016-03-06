class QuickHashMap {
	public static final int DEFAULT_CAPACITY = 100;
	public static final double DEFAULT_LOADFACTOR = 0.75;
	public static final double DEFAULT_LOADFACTORDOWN = 0.20;
	Entry[] map;
	int sz;
	double loadLowFactor;
	double loadFactor;
	private int numCollisions;

	public QuickHashMap() {
		map = new Entry[DEFAULT_CAPACITY];
		sz = 0;
		loadFactor = DEFAULT_LOADFACTOR;
		numCollisions = 0;
	}

	public void put(int key, int val) {
		resizeIfNeeded();
		int idx = getIndexForKey(key);
		if (map[idx] == null) {
			map[idx] = new Entry(key, val);
		}
		else {
			numCollisions++;
			Entry tail = null;
			Entry curr = map[idx];
			while (curr != null && curr.key != key) {
				tail = curr;
				curr = curr.next;
			}
			if (curr == null) {
				tail.next = new Entry(key, val);
			}
			else 
				curr.val = val;
		}
	}

	public void resizeIfNeeded() {
		double currLoad = (double)sz / (double)map.length;
		if (currLoad >= loadFactor) {
			resizeUp();
		}
		else if (currLoad < loadLowFactor && map.length > DEFAULT_CAPACITY) {
			resizeDown();
		}
	}

	private void resizeUp() {}
	private void resizeDown() {}

	public int get(int key) {
		int idx = getHashForKey(key);
		if (map[idx] == null) return -1;
		Entry curr = map[idx];
		while (curr != null && curr.key != key) {
			curr = curr.next;
		}
		if (curr == null) return -1;
		return curr.val;
	}

	public boolean delete(int key) {
		int idx = getHashForKey(key);
		if (map[idx] == null) return false;
		Entry tail = null;
		Entry curr = map[idx];
		while (curr != null && curr.key != key) {
			tail = curr;
			curr = curr.next;
		}
		if (curr == null) return false;
		if (curr.next == null) {
			if (tail != null) tail.next = null;
			else map[idx] = null;
		}
		else {
			if (tail != null) tail.next = curr.next;
			else map[idx] = curr.next;
		}
		sz--;
		return true;
	}

	public int size() {
		return sz;
	}


	public void clear() {
		map = new Entry[DEFAULT_CAPACITY];
	}


	public int getIndexForKey(int key) {
		return getHashForKey(key) & (sz - 1);
	}

	public int getHashForKey(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
	    return h ^ (h >>> 7) ^ (h >>> 4);
	}

	private class Entry {
		int key;
		int val;
		Entry next;
		public Entry(int key, int val) {
			this.val = val;
			this.key = key;
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (Entry e : map) {
			Entry curr = e;
			while (curr != null) {
				sb.append('(');
				sb.append(curr.key);
				sb.append(' ');
				sb.append(':');
				sb.append(' ');
				sb.append(curr.val);
				sb.append(')');
				sb.append(',');
				sb.append(' ');
				curr = curr.next;
			}
		}
		if (sb.length() > 1) sb.setLength(sb.length() - 2);
		sb.append(']');
		return sb.toString();
	}
}

