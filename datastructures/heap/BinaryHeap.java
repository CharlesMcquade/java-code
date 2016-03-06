package code.datastructures.heap;
//A binary heap is a heap data structure created using a binary tree.
//it can be seen as a binary tree with two additional constraints:
//
// Shape property:
//     A binary heap is a complete binary tree; that is, all levels of
//     the tree, except possibly the last one (deepest) are fully filled,
//     and, if the last level of the tree is not complete, the nodes of 
//     that level are filled from left to right.
// Heap property:
// 	   All nodes are either greater than or equal to or 
// 	   less than or equal to each of its children, according to a 
// 	   comparison predicate defined for the heap.

interface BinaryHeap<E extends Comparable<? super E>> {
	// To add an element to a heap we must perform an up-heap operation
	// (also known as bubble-up, percolate-up, sift-up, trickle-up, or cascade-up)
	// by following this algorithm:
	//	1. Add the element to the bottom level of the heap
	//  2. Compare the added element with its parent; if they are in the 
	//     correct order, stop.
	//  3. If not, swap the element with its parent and return to the 
	//     previous step.
	public void insert(E val);

	// The procedure for deleting the root from the heap 
	// (effectively extracting the maximum element in a max-heap
	// or the minimum element in a min-heap) and restoring the properties 
	// is called down-heap (also known as bubble-down, percolate-down, 
	// sift-down, trickle down, heapify-down, cascade-down, and extract-min/max).
	// The steps:
	// 	1. Replace the root of the heap with the last element on the last level.
	// 	2. Comapre the new root with its children; 
	// 	   if they are in the correct order, stop.
	// 	3. If not, swap the element with one of its children and return to the
	//        previous step (Swap with its smaller child in a min-heap or larger child
	//        in a max-heap).
	public E extract();
}