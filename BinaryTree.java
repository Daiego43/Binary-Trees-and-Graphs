package Ejercicio_ArbolAVL_Tweets;

import java.util.*;

public class BinaryTree implements IBinaryTree{
	public BinaryNode root;
//Dada una raíz a la que se le han insertado nodos, se podrá manipular dicho arbol resultante llamando a este constructor
	public BinaryTree(BinaryNode root) {
		this.root=root;
	}
//Para crear un árbol desde 0. Esta implementación permite que el árbol tenga nodos con clases de información distintas
	public BinaryTree(Object ele) {
		this.root = new BinaryNode(ele);
	}
//Devuelve el número total de nodos del árbol	
	@Override
	public int size() {
		int count=0;
		if(!isEmpty()) {
			count=tam(this.root,this.root.getLeft(),this.root.getRight());
		}
		return count;
	}
	private int tam(BinaryNode bn, BinaryNode left, BinaryNode right) {
		int count =1;
		if(left!=null)
			count+=tam(left, left.getLeft(),left.getRight());
		if(right!=null)
			count += tam(right, right.getLeft(),right.getRight());						//Tonto el que lo lea
		
		return count ;
	}
//Devuelve True si el árbol está vacío
	@Override
	public boolean isEmpty() {
		return this.root==null;
	}
//Devuelve la raíz del árbol
	@Override
	public BinaryNode root() {
		return this.root;
	}
//Devuelve el padre de un nodo dado
	@Override
	public BinaryNode parent(BinaryNode node) {
		return parent(this.root,node,null);
	}
	private BinaryNode parent(BinaryNode root,BinaryNode node, BinaryNode parent) {
		BinaryNode bn;
		
		if(root==null)return null;
		
		if(node.equals(root))
			bn=parent;
		else {
			bn=parent(root.getLeft(),node, root);
			if(bn==null)
				bn=parent(root.getRight(),node,root);
		}
		return bn;
	}
//Devuelve verdadero si el nodo es el hijo izquierdo de su nodo padre
	public boolean isLeftChild(BinaryNode bn) {
		return hasLeft(parent(bn)) && left(parent(bn)).equals(bn);
	}
//Devuelve verdadero si el nodo es el hijo derecho de su nodo padre
	public boolean isRightChild(BinaryNode bn) {
		return hasRight(parent(bn)) &&right(parent(bn)).equals(bn);
	}	
//Devuelve una colección que contiene los hijos del nodo
	@Override
	public Collection<BinaryNode> children(BinaryNode node) {
		Collection<BinaryNode> children= new ArrayList<BinaryNode>();
		
		if(hasLeft(node))
			children.add(node.getLeft());
		if(hasRight(node))
			children.add(node.getRight());
			
		return children;
	}
//Devuelve verdadero si el nodo tiene al menos un hijo
	@Override
	public boolean isInternal(BinaryNode node) {
		return (hasLeft(node) || hasRight(node));
	}
//Devuelve verdadero si el nodo es un nodo hoja (no tiene hijos)
	@Override
	public boolean isExternal(BinaryNode node) {
		return (!hasLeft(node) && !hasRight(node));
	}
//Devuelve verdadero si el nodo es la raíz del árbol
	@Override
	public boolean isRoot(BinaryNode node) {
		return node==this.root;
	}
//Devuelve una referencia al nodo izquierdo del nodo dado, si no tiene nodo izquierdo devuelve null
	@Override
	public BinaryNode left(BinaryNode node) {
		if(hasLeft(node))
		return node.getLeft();
		else
			return null;
	}
//Devuelve una referencia al nodo derecho del nodo dado, si no tiene devuelve null
	@Override
	public BinaryNode right(BinaryNode node) {
		if(hasRight(node))
			return node.getRight();
		else
			return null;
	}
//Devuelve verdadero si el nodo dado tiene rama izquierda
	@Override
	public boolean hasLeft(BinaryNode node) {
		return node.getLeft()!=null;
	}
//Devuelve verdadero si el nodo dado tiene rama derecha
	@Override
	public boolean hasRight(BinaryNode node) {
		return node.getRight()!=null;
	}
//Devuelve un iterador sobre los ELEMENTOS del arbol en recorrido inorden
//Estos métodos tienen un toString para que se vea que se ha hecho bien
//Devuelve un iterador sobre los ELEMENTOS del árbol, haciendo el recorrido in order (left,root,right)
	@Override
	public Iterator<Object> elementsInOrder() {
		Collection<Object> elementsIO = new ArrayList<Object>();
		elementsIO=elementsInOrder(elementsIO,this.root);
		return elementsIO.iterator();
	}
	private Collection<Object> elementsInOrder(Collection<Object> elementsIO, BinaryNode node) {
		if(isEmpty())return elementsIO;
		if(hasLeft(node)) 
			elementsInOrder(elementsIO,node.getLeft());
		elementsIO.add(node.getElement());
		if(hasRight(node)) 
			elementsInOrder(elementsIO,node.getRight());
		return elementsIO;
	}
//Devuelve un iterador sobre los ELEMENTOS del árbol, haciendo el recorrido PreOrder(root,left,right)
	@Override
	public Iterator<Object> elementsPreOrder() {
	Collection<Object> elementsPre = new ArrayList<Object>();
	elementsPre= elementsPreOrder(elementsPre,this.root);
	return  elementsPre.iterator();
	}
	private Collection<Object> elementsPreOrder(Collection<Object> elementsPre, BinaryNode node) {
		if(isEmpty())return null;
		elementsPre.add(node.getElement());
		if(hasLeft(node)) 
			elementsPreOrder(elementsPre,node.getLeft());	
		if(hasRight(node))
			elementsPreOrder(elementsPre, node.getRight());
		return elementsPre;
		
	}
//Devuelve un iterador sobre los ELEMENTOS del árbol, haciendo el recorrido post order (left, right,root)
	@Override
	public Iterator<Object> elementsPostOrder() {
		Collection<Object> elementsPost = new ArrayList<Object>();
		elementsPost=elementsPostOrder(elementsPost,this.root);
		return elementsPost.iterator();
	}
	private Collection<Object> elementsPostOrder(Collection<Object> elementsPost, BinaryNode node) {
		if(isEmpty())return null;
		if(hasLeft(node)) 
			elementsPostOrder(elementsPost,node.getLeft());	
		if(hasRight(node))
			elementsPostOrder(elementsPost, node.getRight());
		elementsPost.add(node.getElement());
		return elementsPost;
		
	}
//Devuelve un iterador sobre los NODOS del árbol, inorden
	@Override
	public Iterator<BinaryNode> nodesInOrder() {
		Collection<BinaryNode> nodes = new ArrayList<BinaryNode>();
		nodes=nodesInOrder(nodes,this.root);
		return nodes.iterator();
	}
	public Collection<BinaryNode> nodesInOrder(Collection<BinaryNode> nodes, BinaryNode node) {
		if(isEmpty())return nodes;
		if(hasLeft(node)) 
			nodesInOrder(nodes,node.getLeft());		
		nodes.add(node);
		if(hasRight(node)) 
			nodesInOrder(nodes,node.getRight());
		return nodes;
	}
//Devuelve un iterador sobre los NODOS del árbol, preorden
	@Override
	public Iterator<BinaryNode> nodesPreOrder() {
		Collection<BinaryNode> nodes = new ArrayList<BinaryNode>();
		nodes=nodesPreOrder(nodes,this.root);
		return nodes.iterator();
	}
	private Collection<BinaryNode> nodesPreOrder(Collection<BinaryNode> nodes, BinaryNode node) {
		if(isEmpty())return nodes;
			nodes.add(node);
		if(hasLeft(node)) 
			nodesPreOrder(nodes,node.getLeft());		
		
		if(hasRight(node)) 
			nodesPreOrder(nodes,node.getRight());
		
		return nodes;
	}
//Devuelve un iterador sobre los ELEMENTOS del árbol, postorden
	@Override
	public Iterator<BinaryNode> nodesPostOrder() {
		Collection<BinaryNode> nodes = new ArrayList<BinaryNode>();
		nodes=nodesPostOrder(nodes,this.root);
		return nodes.iterator();
	}
	private Collection<BinaryNode> nodesPostOrder(Collection<BinaryNode> nodes, BinaryNode node) {
		if(isEmpty())return nodes;
		if(hasLeft(node)) 
			nodesPostOrder(nodes,node.getLeft());	
		if(hasRight(node)) 
			nodesPostOrder(nodes,node.getRight());	
		nodes.add(node);
		return nodes;
	}
//Inserta un nuevo nodo a la izquierda o a la derecha de otro nodo	
	@Override
	public void insertLeft(BinaryNode node, Object newElement) {
	if(!hasLeft(node))
		node.setLeft(new BinaryNode(newElement));
	}
	@Override
	public void insertRight(BinaryNode node, Object newElement) {
		if(!hasRight(node))
			node.setRight(new BinaryNode(newElement));
	}
//Elimina un nodo y por tanto el contenido de sus subarboles
	public void removeNode(BinaryNode node) {
		if (isRoot(node)) 
			this.root=null;
		else if(hasRight(parent(node)) && right(parent(node)).equals(node)) 
			parent(node).setRight(null);
		else if(hasLeft(parent(node)) && left(parent(node)).equals(node)) 
			parent(node).setLeft(null);
	}
	public void printTreeInOrder() {
		Collection<Object> elementsIO = new ArrayList<Object>();
		elementsIO=elementsInOrder(elementsIO,this.root);
		System.out.println(elementsIO.toString());
	}	
	public void printTreePreOrder() {
		Collection<Object> elementsPre = new ArrayList<Object>();
		elementsPre= elementsPreOrder(elementsPre,this.root);
		System.out.println(elementsPre.toString());
	}
	public void printTreePostOrder() {
		Collection<Object> elementsPost = new ArrayList<Object>();
		elementsPost=elementsPostOrder(elementsPost,this.root);
		System.out.println(elementsPost.toString());
	}
//Comprueba el parentesco de los nodos
	public void repasarParentesco() {
		Iterator<BinaryNode> it = nodesInOrder();
		BinaryNode n=null;
		while(it.hasNext()) {
			n=it.next();
			if(isRoot(n))
				System.out.println(n.getElement()+" es la raíz");
			else {
			String s=n.getElement()+" es hijo ";
				if(isRightChild(n))
					s+="derecho";
				else
					s+="izquierdo";
			s+=" de "+parent(n).getElement();
			
			System.out.println(s);
			}
		}
		
	}
	
	
		
	}


