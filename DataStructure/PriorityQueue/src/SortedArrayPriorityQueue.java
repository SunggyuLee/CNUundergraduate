


public class SortedArrayPriorityQueue<Element> implements PriorityQueue<Element> {
	private static final int DEFAULT_INITIAL_CAPACITY = 100;
	private int _maxSize;
	private int _front;
	private int _rear;
	private Element[] _elements;
	
	@SuppressWarnings("unchecked")
	public SortedArrayPriorityQueue(){
		this._elements = (Element[]) new Object[DEFAULT_INITIAL_CAPACITY];
		this._front = 0;
		this._rear = 0;
		this._maxSize = DEFAULT_INITIAL_CAPACITY;
	}
	
	public SortedArrayPriorityQueue(int initialCapacity){
		this._elements = (Element[]) new Object[initialCapacity];
		this._front = 0;
		this._rear = 0;
		this._maxSize = initialCapacity;
	}

	@Override
	public boolean add(Element anElement) {
		// TODO Auto-generated method stub
		if (this.isFull())
			return false;
		else {
			int index = 0 ,i = 0;
			while(this._elements[i] != null){
				if(Integer.parseInt(String.valueOf(this._elements[i])) > Integer.parseInt(String.valueOf(anElement))){
					// anElement의 값이 i인덱스의 값보다 작으면 값을 저장하고 loop를 멈춤
					index = i;
					break;
				}
				i++;
				index = i;
			}
			for(i = this._rear-1; i>=index; i--)
				this._elements[i+1] = this._elements[i];	// 뒤로 하나씩 미뤄가며 넣을 자리를 마련
			this._elements[index] = anElement;	// 넣으려했던 자리에 넣는다
			this._rear++;
			return true;
		}
	}
	
	@Override
	public Element max() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		else			
			return this._elements[this._rear-1];
	}
	
	@Override
	public Element removeMax() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		else{
			Element removeElement;
			removeElement = this._elements[this._rear-1];
			this._elements[this._rear-1] = null;	// 최댓값을 지우는 메소드이므로 배열은 가장 뒤의 값을 지운다
			this._rear--;
			return removeElement;
		}
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this._rear;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this._front == this._rear;
	}
	
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return this._rear == this._maxSize;
	}

	public Iterator<Element> iterator() {
		return new Iterator();
	}
	public class Iterator<Element> {
		private int _nextPosition;
		
		private Iterator(){
			this._nextPosition = 0;
		}
		public boolean hasNext(){
			return (this._nextPosition < size());
		}
		public Element next(){
			if(this._nextPosition == size()) 
				return null;
			else {
				@SuppressWarnings("unchecked")
				Element element = (Element)_elements[this._nextPosition];
				this._nextPosition++;
				return element;
			}
		}
	}
}



