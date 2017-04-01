public class Node {
	
	private Coin _coin;
	private Node _next;
	
	public Node() {
		this._coin = null;
		this._next = null;
	}

	public Node(Coin aCoin) {
		this._coin = aCoin;
		this._next = null;		
	}

	public Node(Coin aCoin, Node aNode) {
		this._coin = aCoin;
		this._next = aNode;
	}

	public Coin coin() {
		return this._coin;
	}

	public Node next() {
		return this._next;
	}

	public void setCoin(Coin aCoin) {
		this._coin = aCoin;
	}

	public void setNext(Node givenNode) {
		this._next = givenNode;
	}
}
