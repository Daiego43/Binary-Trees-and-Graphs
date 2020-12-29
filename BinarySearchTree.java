package Ejercicio_ArbolAVL_Tweets;

import java.util.*;

public class BinarySearchTree extends BinaryTree implements IBinarySearchTree{
	Comparator<Object> comp;
	
	public BinarySearchTree(Object ele, Comparator<Object> comp) {
		super(ele);	
		this.comp = comp; 
	}
	public BinarySearchTree(BinaryNode ele, Comparator<Object> comp) {
		super(ele);	
		this.comp = comp; 
	}

	@Override
	public void addElement(Object element) {
		addElement(root, element);
	}
	private void addElement(BinaryNode node, Object element) {
			if(comp.compare(element, node.getElement())<0) {
				
				if(!hasLeft(node))
					insertLeft(node,element);
				else
					addElement(left(node), element);
				
			}else if (comp.compare(element, node.getElement())>0){
				
				if(!hasRight(node)) 
					insertRight(node,element);
				else 
					addElement(right(node), element);
				
			}else if (comp.compare(element, node.getElement())==0) {
				if(!hasLeft(node))
					insertLeft(node,element);
				else
					addElement(left(node), element);
			}
	}
	@Override
	public Object removeElement(Object element) {
		BinaryNode bn = new BinaryNode(null);
		Object o = null;
		if (find(element)) {
			bn = findE(element);
			//Primer Caso: Nodo sin hijos
			if(isExternal(bn)) {
				o=bn.getElement();
				removeNode(bn);
			}else if(isInternal(bn)) {
				//Caso 2: Nodo con un hijo
				if (children(bn).size()==1) {
					o = bn.getElement();
					removeCase1(bn);
				}else if(children(bn).size()==2) {
					o =bn.getElement();
					removeCase2(bn);
				}
			}
		}
		return o;
	}
	private void removeCase1(BinaryNode bn) {
		List<BinaryNode> l = (List<BinaryNode>) children(bn);
		if( hasRight(parent(bn)) && right(parent(bn)).equals(bn)) {
			parent(bn).setRight(l.get(0));
		}else {
			parent(bn).setLeft(l.get(0));
		}
	}
	private void removeCase2(BinaryNode bn) {
		BinarySearchTree t = new BinarySearchTree(right(bn),this.comp);
		if(t.root().equals(t.findMin())) {
			if(isRightChild(bn)) {
				parent(bn).setRight(new BinaryNode(t.root.getElement(),bn.getLeft(), null));
			}else {
				parent(bn).setLeft(new BinaryNode(t.root.getElement(),bn.getLeft(),null));
			}
		}else {
			if(isRightChild(bn)) {
				parent(bn).setRight(new BinaryNode(t.removeMin(),bn.getLeft(), t.root));
			}else {
				parent(bn).setLeft(new BinaryNode(t.removeMin(),bn.getLeft(), t.root));
			}
		}
	}
	@Override
	public void removeAllOcurrences(Object element) {
		while(find(element)) {
			removeElement(element);
		}
	}
//Quita el nodo de menor valor, conveniente si es un nodo hoja
	@Override
	public Object removeMin() {
		Object o = new Object();
		List<BinaryNode> l = (List<BinaryNode>) nodesInOrder(new ArrayList<BinaryNode>(),this.root);
		BinaryNode bn =l.get(0);
		o = bn.getElement();
		removeNode(bn);
		return o;
	}
//quita el nodo de mayor valor, conveniente si es un nodo hoja
	@Override
	public Object removeMax() {
		Object o = new Object();
		List<BinaryNode> l = (List<BinaryNode>) nodesInOrder(new ArrayList<BinaryNode>(),this.root);
		BinaryNode bn =l.get(l.size()-1);
		o = bn.getElement();
		removeElement(bn);
		return o;
	}
//Devuelve el nodo con el valor más bajo
	@Override
	public BinaryNode findMin() {
		List<BinaryNode> l = (List<BinaryNode>) nodesInOrder(new ArrayList<BinaryNode>(),this.root);
		BinaryNode bn =l.get(0);
		return bn;
	}
//Devuelve el nodo con el valor más alto
	@Override
	public BinaryNode findMax() {
		List<BinaryNode> l = (List<BinaryNode>) nodesInOrder(new ArrayList<BinaryNode>(),this.root);
		BinaryNode bn =l.get(l.size()-1);
		return bn;
	}
	//Devuelve verdadero si el elemento está en el arbol
	@Override
	public boolean find(Object elementToFind) {
		boolean b=false;
		return find(root,elementToFind,b);
	}
	private boolean find(BinaryNode node,Object elementToFind, boolean b) {
		if(node.getElement().equals(elementToFind))
			b=true;
		else if(comp.compare(elementToFind, node.getElement())>0 && hasRight(node))
			b= find(right(node),elementToFind,b);
		else if(comp.compare(elementToFind, node.getElement())<=0 && hasLeft(node))
			b= find(left(node),elementToFind,b);
		return b;
	}
	//Devuelve el nodo del arbol que contenga el elemento, en caso de que el elemento esté en el arbol, si no está devuelve null
	public BinaryNode findE(Object elementToFind) {
		BinaryNode bn = null;
		if(find(elementToFind))
			return findE(root,elementToFind,bn);
		return bn;
	}
	private BinaryNode findE(BinaryNode node, Object elementToFind, BinaryNode bn) {
		if(node.getElement().equals(elementToFind))
			bn=node;
		else if(comp.compare(elementToFind, node.getElement())>0 && hasRight(node))
			bn= findE(right(node),elementToFind,bn);
		else if(comp.compare(elementToFind, node.getElement())<=0 && hasLeft(node))
			bn= findE(left(node),elementToFind,bn);
		return bn;
		
	}

}
