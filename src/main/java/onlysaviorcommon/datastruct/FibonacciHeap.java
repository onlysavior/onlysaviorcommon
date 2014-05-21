package onlysaviorcommon.datastruct;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FibonacciHeap<T> {
	public int keyNum = 0;
	public FibonacciNode<T> minNode = null;
	public int maxDegree = 0;

	public static class FibonacciNode<T> {
		public T key;
		public int degree = 0;
		public FibonacciNode<T> parent;
		public FibonacciNode<T> child;
		public FibonacciNode<T> left;
		public FibonacciNode<T> right;

		public double priority;
		public boolean mark;

		public FibonacciNode(T value, double priority) {
			this.key = value;
			this.priority = priority;
		}
	}

	public FibonacciNode<T> insert(T value, double priority) {
		checkPriority(priority);
		FibonacciNode<T> rtn = new FibonacciNode<T>(value, priority);

		minNode = mergeList(minNode, rtn);
		++keyNum;

		return rtn;
	}

	public FibonacciNode<T> deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		--keyNum;
		FibonacciNode<T> toRemove = minNode;
		if (minNode.right == minNode) {
			minNode = null;
		} else {
			minNode.left.right = minNode.right;
			minNode.right.left = minNode.left;
		}

		if (toRemove.child != null) {
			FibonacciNode<T> curr = toRemove.child;
			do {
				curr.parent = null;
				curr = curr.right;
			} while (curr != toRemove.child);
		}

		minNode = mergeList(minNode, toRemove.child);
		if (minNode == null)
			return toRemove;

		List<FibonacciNode<T>> treeTable = new ArrayList<FibonacciNode<T>>();
		List<FibonacciNode<T>> toVisit = new ArrayList<FibonacciNode<T>>();

		for (FibonacciNode<T> curr = minNode; toVisit.isEmpty()
				|| toVisit.get(0) != curr; curr = curr.right) {
			toVisit.add(curr);
		}

		for (FibonacciNode<T> curr : toVisit) {
			while (true) {
				while (curr.degree > treeTable.size()) {
					treeTable.add(null);
				}

				if (treeTable.get(curr.degree) == null) {
					treeTable.set(curr.degree, curr);
					break;
				}

				FibonacciNode<T> other = treeTable.get(curr.degree);
				treeTable.set(curr.degree, null);
				FibonacciNode<T> min = (other.priority < curr.priority) ? other
						: curr;
				FibonacciNode<T> max = (other.priority < curr.priority) ? curr
						: other;

				max.left.right = max.right;
				max.right.left = max.left;

				max.left = max.right = max;
				min.child = mergeList(min.child, max);
				max.parent = min;
				max.mark = false;

				++min.degree;
				curr = min;
			}

			if (curr.priority < minNode.priority)
				minNode = curr;
		}

		return toRemove;
	}
	
	public void delete(FibonacciNode<T> node) {
		decreasePriority(node, Double.MIN_VALUE);
		deleteMin();
	}

	public void decreasePriority(FibonacciNode<T> node, double newPriority) {
		checkPriority(newPriority);
		if (newPriority > node.priority) {
			throw new IllegalArgumentException(
					"new priority must less than the old one");
		}
		decreasePriorityUnchecked(node, newPriority);
	}

	public static <T> FibonacciNode<T> mergeList(FibonacciNode<T> one,
			FibonacciNode<T> two) {
		if (one == null && two == null) {
			return null;
		}

		if (one == null && two != null) {
			return two;
		}

		if (one != null && two == null) {
			return one;
		}

		FibonacciNode<T> oneNext = one.right;
		one.right = two.right;
		two.right.left = one;
		two.right = oneNext;
		oneNext.left = two;

		return one.priority < two.priority ? one : two;
	}

	public static <T> FibonacciHeap<T> merge(FibonacciHeap<T> one,
			FibonacciHeap<T> two) {
		FibonacciHeap<T> result = new FibonacciHeap<T>();

		result.minNode = mergeList(one.minNode, two.minNode);

		result.keyNum = one.keyNum + two.keyNum;

		one.keyNum = two.keyNum = 0;
		one = null;
		two = null;

		return result;
	}

	public FibonacciNode<T> min() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return minNode;
	}

	public boolean isEmpty() {
		return minNode == null;
	}

	public int size() {
		return keyNum;
	}

	private void checkPriority(double priority) {
		if (Double.isNaN(priority)) {
			throw new IllegalArgumentException("priority is illegal "
					+ priority);
		}
	}

	private void decreasePriorityUnchecked(FibonacciNode<T> node,
			double newPriority) {
		node.priority = newPriority;
		if (node.parent != null) {
			if (node.priority <= node.parent.priority) {
				cut(node);
			}
		}

		if (node.priority < minNode.priority) {
			minNode = node;
		}
	}
	
	private void cut(FibonacciNode<T> node) {
		node.mark = false;
		if(node.parent == null) return;
		if(node.right != node) {
			node.left.right = node.right;
			node.right.left = node.left;
		}
		
		if(node.parent.child == node) {
			if(node.right == null) {
				node.parent.child = null;
			} else {
				node.parent.child = node.right;
			}
		}
		
		--node.parent.degree;
		node.left = node.right = node;
		minNode = mergeList(minNode, node);
		
		if (node.parent.mark)
            cut(node.parent);
        else
        	node.parent.mark = true;

        node.parent = null;
	}
}
