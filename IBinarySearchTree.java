package Ejercicio_ArbolAVL_Tweets;

public interface IBinarySearchTree extends IBinaryTree {
	void addElement(Object element);

	Object removeElement(Object element);

	void removeAllOcurrences(Object element);

	Object removeMin();

	Object removeMax();

	Object findMin();

	Object findMax();

	boolean find(Object elementToFind);
}
