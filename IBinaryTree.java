package Ejercicio_ArbolAVL_Tweets;
import java.util.*;
public interface IBinaryTree {
	
		//ADT Árbol
		int size();
		boolean isEmpty();
		BinaryNode root();
		BinaryNode parent(BinaryNode node);
		Collection<BinaryNode> children(BinaryNode node);
		boolean isInternal(BinaryNode node);
		boolean isExternal(BinaryNode node);
		boolean isRoot(BinaryNode node);

		//ADT Árbol Binario
		BinaryNode left(BinaryNode node);
		BinaryNode right(BinaryNode node);
		boolean hasLeft(BinaryNode node);
		boolean hasRight(BinaryNode node);
		Iterator<Object> elementsInOrder();
		Iterator<Object> elementsPreOrder();
		Iterator<Object> elementsPostOrder();
		Iterator<BinaryNode> nodesInOrder();
		Iterator<BinaryNode> nodesPreOrder();
		Iterator<BinaryNode> nodesPostOrder();
		void insertLeft(BinaryNode node, Object newElement);
		void insertRight(BinaryNode node, Object newElement);
}
