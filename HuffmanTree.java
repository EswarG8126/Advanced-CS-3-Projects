import java.io.*;
import java.util.*;

public class HuffmanTree {
	private TreeNode root;
	private HashMap<Integer, String> hm;

	public HuffmanTree(int[] frequency) {
		hm = new HashMap<Integer, String>();
		Queue<TreeNode> priorityQueue = new PriorityQueue<TreeNode>();
		for (int i = 0; i < frequency.length; i++) {
			TreeNode node = new TreeNode(frequency[i]);
			node.val = i;
			if (frequency[i] > 0) {
				priorityQueue.add(node);
			}
		}
		TreeNode endOfFile = new TreeNode(1);
		endOfFile.val = 256;
		priorityQueue.add(endOfFile);
		while (priorityQueue.size() != 1) {
			TreeNode a = priorityQueue.poll();
			TreeNode b = priorityQueue.poll();
			TreeNode root = new TreeNode(a.freq + b.freq);
			root.left = a;
			root.right = b;
			priorityQueue.add(root);
		}
		root = priorityQueue.peek();
	}

	public HuffmanTree(String cFile) {
		root = new TreeNode(1);
		try {
			Scanner scanner = new Scanner(new File(cFile));
			while (scanner.hasNext()) {
				int i = Integer.parseInt(scanner.nextLine());
				String s = scanner.nextLine();
				TreeNode node = root;
				while (s.length() > 1) {
					int n = Integer.parseInt(s.substring(0, 1));
					if (n == 0) {
						if (node.left == null) {
							node.left = new TreeNode(1);
						}
						node = node.left;
					}
					if (n == 1) {
						if (node.right == null) {
							node.right = new TreeNode(1);
						}
						node = node.right;
					}
					s = s.substring(1);
				}
				if (s.equals("0")) {
					node.left = new TreeNode(1);
					node.left.val = i;
				} else {
					node.right = new TreeNode(1);
					node.right.val = i;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write(String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(fileName + ".code"));
			fileWriter(pw, root, "");
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fileWriter(PrintWriter pw, TreeNode node, String fileInput) {
		if (node.right == null && node.left == null) {
			pw.println(node.val);
			pw.println(fileInput);
			hm.put(node.val, fileInput);
		} else if (node.left == null) {
			fileWriter(pw, node.right, fileInput + "1");
		} else if (node.right == null) {
			fileWriter(pw, node.left, fileInput + "0");
		} else {
			fileWriter(pw, node.left, fileInput + "0");
			fileWriter(pw, node.right, fileInput + "1");
		}
	}

	public void encode(BitOutputStream bos, String fileName) {
		try {
			Scanner scanner = new Scanner(new File(fileName + ".txt"));
			while (scanner.hasNext()) {
				char[] s = scanner.nextLine().toCharArray();
				for (char c : s) {
					String hc = hm.get((int) c);
					while (hc.length() > 0) {
						bos.writeBit(Integer.parseInt(hc.substring(0, 1)));
						hc = hc.substring(1);
					}
				}
			}
			String endOfFile = endOfFile(root, "");
			while (endOfFile.length() > 0) {
				bos.writeBit(Integer.parseInt(endOfFile.substring(0, 1)));
				endOfFile = endOfFile.substring(1);
			}
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String endOfFile(TreeNode node, String s) {
		if (node.right == null && node.left == null && node.val == 256) {
			return s;
		}
		if (node.right == null && node.left == null) {
			return "";
		} else if (node.right == null) {
			return endOfFile(node.left, s + "0");
		} else if (node.left == null) {
			return endOfFile(node.right, s + "1");
		} else {
			return endOfFile(node.left, s + "0") + endOfFile(node.right, s + "1");
		}
	}

	public void decode(BitInputStream bis, String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(fileName));
			TreeNode node = root;
			while (true) {
				while (node.right != null && node.left != null) {
					int i = bis.readBit();
					if (i == 1) {
						node = node.right;
					}
					if (i == 0) {
						node = node.left;
					}
				}
				if (node.val == 256) {
					break;
				}
				pw.print((char) node.val);
				node = root;
			}
			pw.close();
			bis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}