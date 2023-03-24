public class TreeNode implements Comparable<TreeNode> {
	public int freq;
	public int val;
	public TreeNode right;
	public TreeNode left;

	public TreeNode(int freq) {
		this.freq = freq;
	}

	@Override
	public int compareTo(TreeNode other) {
		return this.freq - other.freq;
	}

	@Override
	public String toString() {
		return Character.toString((char) val);
	}
}