public class Node {
	
	private Star _star;
	private Node _next;
	
	public Node() {
		this._star = null;
		this._next = null;
	}

	public Node(Star aStar) {
		this._star = aStar;
		this._next = null;		
	}

	public Node(Star aStar, Node aNode) {
		this._star = aStar;
		this._next = aNode;
	}

	public Star star() {
		return this._star;
	}

	public Node next() {
		return this._next;
	}

	public void setStar(Star aStar) {
		this._star = aStar;
	}

	public void setNext(Node givenNode) {
		this._next = givenNode;
	}
}
