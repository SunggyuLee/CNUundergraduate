public class BinaryNode<Key, Obj> {
	private Element<Integer,Integer> _element;
	private BinaryNode<Key, Obj> _left;
	private BinaryNode<Key, Obj> _right;

	public BinaryNode() {
		this._element = null;
		this._left = null;
		this._right = null;
	}

	public BinaryNode(Element anElement) {
		this._element = anElement;
		this._left = null;
		this._right = null;
	}

	public BinaryNode(BinaryNode givenLeft, BinaryNode givenRight) {
		this._element = null;
		this._left = givenLeft;
		this._right = givenRight;
	}

	public BinaryNode(Element anElement, BinaryNode givenLeft,
			BinaryNode givenRight) {
		this._element = anElement;
		this._left = givenLeft;
		this._right = givenRight;
	}

	public Element element() {
		return this._element;
	}

	public void setElement(Element givenElement) {
		this._element = givenElement;
	}

	public BinaryNode left() {
		return this._left;
	}

	public void setLeft(BinaryNode givenLeft) {
		this._left = givenLeft;
	}

	public BinaryNode right() {
		return this._right;
	}

	public void setRight(BinaryNode givenRight) {
		this._right = givenRight;
	}

}
