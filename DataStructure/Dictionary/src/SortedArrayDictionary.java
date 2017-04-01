public class SortedArrayDictionary<Key, Obj> {

	private static final int DEFAULT_MAX_SIZE = 20;
	private int _maxLength;
	private int _length;
	private Element<Key, Obj>[] _element;


	@SuppressWarnings("unchecked")
	public SortedArrayDictionary() {
		this._length = 0;
		this._maxLength = DEFAULT_MAX_SIZE;
		this._element = new Element[DEFAULT_MAX_SIZE];
	}

	
	@SuppressWarnings("unchecked")
	public SortedArrayDictionary(int givenMaxSize) {
		this._length = 0;
		this._maxLength = givenMaxSize;
		this._element = new Element[this._maxLength];
	}

	public boolean isEmpty() {
		return this._length == 0;
	}

	public boolean isFull() {
		return this._maxLength == this._length;
	}

	public int size() {
		return this._length;
	}

	public boolean doesKeyExist(Key givenKey) {
		for (int i = 0; i < this._length; i++)
			if (this._element[i].compareTo(givenKey) == 0)
				return true;
		return false;
	}

	public Obj objectForKey(Key givenKey) {
		for (int i = 0; i < this._length; i++)
			if (this._element[i].compareTo(givenKey) == 0)
				return (Obj) this._element[i].object();
		return null;
	}


	public boolean addKeyandObject(Key aKey, Obj anObject) {
		int index = 0;
		if (this.doesKeyExist(aKey))
			return false;
		else if(this.isFull())
			return false;
		else {
			while (this._element[index] != null && this._element[index].compareTo(aKey) < 0)
				index++;
			for (int i = this._length-1; i >= index; i--)
				this._element[i + 1] = this._element[i];
			this._element[index] = new Element(aKey,anObject); 
			this._length++;
			return true;
		}

	}

	public Obj removeObjectForKey(Key givenKey) {
		if (this.isEmpty())
			return null;
		else {
			int index;
			Obj removeElement;
			for (index = 0; index < this._length; index++)
				if (this._element[index].compareTo(givenKey) == 0)
					break;
			removeElement = this._element[index].object();
			for(int i=index;i<this._length;i++)
				this._element[i]=this._element[i+1];
			this._length--;
			return removeElement;
		}
	}

	public boolean replaceObjectForKey(Obj newObject, Key givenKey) {
		for (int i = 0; i < this._length; i++)
			if (this._element[i].compareTo(givenKey) == 0){
				this._element[i].setObject(newObject);
				return true;
			}
		return false;
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		this._length = 0;
		this._maxLength = DEFAULT_MAX_SIZE;
		this._element =  (Element[]) new Object[DEFAULT_MAX_SIZE];
	}

}
