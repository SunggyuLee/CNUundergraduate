public class Node<Key,Obj> {
	
	private Element<Integer, Integer> _element;
	private Node<Key,Obj> _next;
	
	public Node() {
		this._element = null;
		this._next = null;
	}
 
	public Node(Element anElement) {
		this._element = anElement;
		this._next = null;		
	}
 
	public Node(Element anElement, Node aNode) {
		this._element = anElement;
		this._next = aNode;
	}
 
	public Element element() {
		return this._element;
	}
 
	public Node next() {
		return this._next;
	}
 
	public void setElement(Element anElement) {
		this._element = anElement;
	}
 
	public void setNext(Node givenNode) {
		this._next = givenNode;
	}
}