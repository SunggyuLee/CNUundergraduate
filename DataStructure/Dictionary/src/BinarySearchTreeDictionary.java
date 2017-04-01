public class BinarySearchTreeDictionary<Key, Obj> {

	private BinaryNode<Key, Obj> _root;
	private int _size;

	public BinarySearchTreeDictionary() {
		this._root = null;
		this._size = 0;
	}

	public boolean isEmpty() {
		return this._size == 0;
	}

	public boolean isFull() {
		return false;
	}

	public int size() {
		return this._size;
	}

	private boolean doesKeyExistInTree(BinaryNode<Key, Obj> currentRoot,
			Key givenKey) {
		if (currentRoot == null)
			return false;
		else {
			if (currentRoot.element().compareTo(givenKey) == 0)
				return true;
			else if (currentRoot.element().compareTo(givenKey) > 0)
				return doesKeyExistInTree(currentRoot.left(), givenKey);
			else
				return doesKeyExistInTree(currentRoot.right(), givenKey);
		}
	}

	public boolean doesKeyExist(Key givenKey) {
		return doesKeyExistInTree(this._root, givenKey);
	}

	public Obj objectForKey(Key givenKey) {
		BinaryNode<Key, Obj> currentRoot = this._root;
		while (currentRoot != null) {
			if (currentRoot.element().compareTo(givenKey) == 0)
				return (Obj) currentRoot.element().object();
			else if (currentRoot.element().compareTo(givenKey) > 0)
				currentRoot = currentRoot.left();
			else
				currentRoot = currentRoot.right();
		}
		return null;
	}

	private boolean addKeyAndObjectToSubtree(BinaryNode<Key, Obj> currentRoot,
			Key aKey, Obj anObject) {
		BinaryNode<Key, Obj> newNode = null;
		if (currentRoot.element().compareTo(aKey) == 0)
			return false;
		else if (currentRoot.element().compareTo(aKey) > 0) {
			if (currentRoot.left() == null) {
				newNode = new BinaryNode<>(
						new Element<Key, Obj>(aKey, anObject));
				currentRoot.setLeft(newNode);
				this._size++;
				return true;
			} else
				return addKeyAndObjectToSubtree(currentRoot.left(), aKey,
						anObject);
		} else {
			if (currentRoot.right() == null) {
				newNode = new BinaryNode(new Element<Key, Obj>(aKey, anObject));
				currentRoot.setRight(newNode);
				this._size++;
				return true;
			} else
				return addKeyAndObjectToSubtree(currentRoot.right(), aKey,
						anObject);
		}
	}

	public boolean addKeyandObject(Key aKey, Obj anObject) {
		if (this.isEmpty()) {
			this._root = new BinaryNode<Key, Obj>(new Element<Key, Obj>(aKey,
					anObject));
			this._size++;
			return true;
		} else
			return addKeyAndObjectToSubtree(this._root, aKey, anObject);
	}

	public Obj removeObjectForKey(Key givenKey) {
		Obj removeObject = null;
		if (this._root == null) {
			return null;
		} else if (this._root.element().compareTo(givenKey) == 0) {
			removeObject = (Obj) this._root.element().object();
			if ((this._root.left() == null) && (this._root.right() == null)) {
				this._root = null;
			} else if (this._root.left() == null) {
				this._root = this._root.right();
			} else if (this._root.right() == null) {
				this._root = this._root.left();
			} else {
				BinaryNode<Key, Obj> newRoot = removeRightMostOfLeftTree(this._root);
				newRoot.setLeft(this._root.left());
				newRoot.setRight(this._root.right());
				this._root = newRoot;
			}
			this._size--;
			return removeObject;
		} else
			return removeObjectForKeyFromSubtree(this._root, givenKey);
	}

	private BinaryNode<Key, Obj> removeRightMostOfLeftTree(BinaryNode<Key, Obj> currentRoot) {
		BinaryNode<Key, Obj> leftOfCurrentRoot = currentRoot.left();
		BinaryNode<Key, Obj> leftOfParent = leftOfCurrentRoot;
		if (leftOfCurrentRoot == null) {
			return null;
		}
		if (leftOfParent.right() == null) {
			currentRoot.setLeft(leftOfParent.left());
			return leftOfCurrentRoot;
		} else {
			BinaryNode<Key, Obj> parentOfRightMost = leftOfCurrentRoot;
			BinaryNode<Key, Obj> rightMost = leftOfCurrentRoot.right();
			while (rightMost.right() != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right();
			}
			parentOfRightMost.setRight(rightMost.left());
			rightMost.setLeft(null);
			return rightMost;
		}
	}

	private Obj removeObjectForKeyFromSubtree(BinaryNode currentRoot,
			Key givenKey) {
		BinaryNode<Key, Obj> child = null;
		if (currentRoot.element().compareTo(givenKey) > 0) { // left subtree에서
			// 삭제해야 한다
			child = currentRoot.left();
			if (child == null)
				return null;
			else {
				if (child.element().compareTo(givenKey) == 0) {
					Obj removedObject = (Obj) child.element().object();
					if (child.left() == null && child.right() == null) { // child가
						// leaf
						currentRoot.setLeft(null);
					} else if (child.left() == null) { // child 의 left tree가 없다
						currentRoot.setLeft(child.right());
					} else if (child.right() == null) { // child 의 right tree 가
						// 없다
						currentRoot.setLeft(child.left());
					} else { // child의 left tree, right tree가 모두 있다
						BinaryNode<Key, Obj> newChild = removeRightMostOfLeftTree(child);
						newChild.setLeft(child.left());
						newChild.setRight(child.right());
						currentRoot.setLeft(newChild);
					}
					this._size--;
					return removedObject;
				} else {
					return removeObjectForKeyFromSubtree(child, givenKey);
				}
			}
		} else { // right subtree에서 삭제해야 한다
			child = currentRoot.right();
			if (child == null) {
				return null;
			} else {
				if (child.element().compareTo(givenKey) == 0) {
					Obj removedObject = (Obj) child.element().object();
					if (child.left() == null && child.right() == null) { // child가
						// leaf
						currentRoot.setRight(null);
					} else if (child.left() == null) { // child 의 left tree가 없다
						currentRoot.setRight(child.right());
					} else if (child.right() == null) { // child 의 right tree 가
						// 없다
						currentRoot.setRight(child.left());
					} else { // child의 left tree, right tree가 모두 있다
						BinaryNode<Key, Obj> newChild = removeRightMostOfLeftTree(child);
						newChild.setLeft(child.left());
						newChild.setRight(child.right());
						currentRoot.setRight(newChild);
					}
					this._size--;
					return removedObject;
				} else {
					return removeObjectForKeyFromSubtree(child, givenKey);
				}
			}
		}
	}

	public boolean replaceObjectForKey(Obj newObject, Key givenKey) {
		BinaryNode<Key, Obj> currentRoot = this._root;
		while (currentRoot != null) {
			if (currentRoot.element().compareTo(givenKey) == 0){
				currentRoot.element().setObject(newObject);
				return true;
			}
			else if (currentRoot.element().compareTo(givenKey) > 0)
				currentRoot = currentRoot.left();
			else
				currentRoot = currentRoot.right();
		}
		return false;
	}

	public void clear() {
		this._root = null;
		this._size = 0;
	}

}
