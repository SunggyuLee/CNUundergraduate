
public class HeapPriorityQueue<Element> implements PriorityQueue<Element>{
	private static final int DEFAULT_CAPACITY = 100 ;
	private static final int ROOT = 1 ;
	private int _maxSize ;
	private int _size ;
	private Element [] _tree;
	
	@SuppressWarnings("unchecked")
	public HeapPriorityQueue(){
		this._maxSize = DEFAULT_CAPACITY;
		this._size = 0;
		this._tree = (Element[]) new Object[DEFAULT_CAPACITY+1];	// 배열의 0인덱스는 특징상 사용하지 않으므로 하나더 크게 배열을 만든다
	}
	
	@SuppressWarnings("unchecked")
	public HeapPriorityQueue(int initialCapacity){
		this._maxSize = initialCapacity;
		this._size = 0;
		this._tree = (Element[]) new Object[initialCapacity+1];	// 배열의 0인덱스는 특징상 사용하지 않으므로 하나더 크게 배열을 만든다
	}
	
	@Override
	public boolean add(Element anElement) {
		// TODO Auto-generated method stub
		if( this.isFull())
			return false;
		else{
			if(!(anElement instanceof Comparable))	
				throw new IllegalArgumentException();	//anElement가 Comparable이 아닐경우 오류 출력
			this._size++;	// 특성상 1올리고 시작
			int i = this._size;	// i = _size
			Comparable<Element> x = (Comparable<Element>)anElement;	// compareTo이용을 위한 객체 선언 및 생성
			while((i>ROOT)&& (x.compareTo((Element)this._tree[i/2])>0)){	
				// ROOT(==1)보다 i가 크고 넣으려는 값이 자신의 parent보다 크면 
				this._tree[i] = this._tree[i/2];	// parent의 값을 i인덱스 배열에 저장
				i/=2;	// i = i/2;
			}
			this._tree[i] = anElement;	// i인덱스배열에 넣는다
			return true;
		}
	}

	@Override
	public Element max() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		else
			return this._tree[ROOT];	//heap은 ROOT값이 최댓값이므로 ROOT를 알려준다
	}

	@Override
	public Element removeMax() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		Element rootElement = (Element) this._tree[ROOT];	// ROOT의 값이 최댓값이므로 return할 변수에 저장
		this._size--;
		if(this._size > 0){
			Element lastElement = (Element) this._tree[this._size+1];	
			int parent = ROOT;
			int biggerChild;
			while((parent*2) <= this._size){
				biggerChild = parent*2;
				// child 중 큰 child를 biggerChild에 저장
				Comparable<Element> comparable = (Comparable<Element>)this._tree[biggerChild];
				if((biggerChild<this._size)&&(comparable.compareTo(this._tree[biggerChild+1]) < 0)){
					biggerChild++;
				}
				// lastElement가 biggerChild보다 크면 멈춘다
				Comparable<Element> comparable2 = (Comparable<Element>) lastElement;
				if(comparable2.compareTo((Element)this._tree[biggerChild]) >= 0){
					break;
				}
				this._tree[parent] = this._tree[biggerChild];
				parent = biggerChild;
			}
			this._tree[parent] = lastElement;	//parent자리에 lastElement를 넣는다
		}
			return rootElement;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this._size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this._size == 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return this._size == this._maxSize;
	}
	
	public Iterator<Element> iterator() {
		return new Iterator();
	}
	
	public class Iterator<Element>{
		private int _nextPosition;
		
		private Iterator(){
			this._nextPosition = 1;
		}
		public boolean hasNext(){
			return (this._nextPosition < size());
		}
		public Element next(){
			if(this._nextPosition == size()) 
				return null;
			else {
				@SuppressWarnings("unchecked")
				Element tree = (Element)_tree[this._nextPosition];
				this._nextPosition++;
				return tree;
			}
		}
	}


	
}
