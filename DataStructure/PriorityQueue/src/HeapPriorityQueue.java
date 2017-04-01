
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
		this._tree = (Element[]) new Object[DEFAULT_CAPACITY+1];	// �迭�� 0�ε����� Ư¡�� ������� �����Ƿ� �ϳ��� ũ�� �迭�� �����
	}
	
	@SuppressWarnings("unchecked")
	public HeapPriorityQueue(int initialCapacity){
		this._maxSize = initialCapacity;
		this._size = 0;
		this._tree = (Element[]) new Object[initialCapacity+1];	// �迭�� 0�ε����� Ư¡�� ������� �����Ƿ� �ϳ��� ũ�� �迭�� �����
	}
	
	@Override
	public boolean add(Element anElement) {
		// TODO Auto-generated method stub
		if( this.isFull())
			return false;
		else{
			if(!(anElement instanceof Comparable))	
				throw new IllegalArgumentException();	//anElement�� Comparable�� �ƴҰ�� ���� ���
			this._size++;	// Ư���� 1�ø��� ����
			int i = this._size;	// i = _size
			Comparable<Element> x = (Comparable<Element>)anElement;	// compareTo�̿��� ���� ��ü ���� �� ����
			while((i>ROOT)&& (x.compareTo((Element)this._tree[i/2])>0)){	
				// ROOT(==1)���� i�� ũ�� �������� ���� �ڽ��� parent���� ũ�� 
				this._tree[i] = this._tree[i/2];	// parent�� ���� i�ε��� �迭�� ����
				i/=2;	// i = i/2;
			}
			this._tree[i] = anElement;	// i�ε����迭�� �ִ´�
			return true;
		}
	}

	@Override
	public Element max() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		else
			return this._tree[ROOT];	//heap�� ROOT���� �ִ��̹Ƿ� ROOT�� �˷��ش�
	}

	@Override
	public Element removeMax() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		Element rootElement = (Element) this._tree[ROOT];	// ROOT�� ���� �ִ��̹Ƿ� return�� ������ ����
		this._size--;
		if(this._size > 0){
			Element lastElement = (Element) this._tree[this._size+1];	
			int parent = ROOT;
			int biggerChild;
			while((parent*2) <= this._size){
				biggerChild = parent*2;
				// child �� ū child�� biggerChild�� ����
				Comparable<Element> comparable = (Comparable<Element>)this._tree[biggerChild];
				if((biggerChild<this._size)&&(comparable.compareTo(this._tree[biggerChild+1]) < 0)){
					biggerChild++;
				}
				// lastElement�� biggerChild���� ũ�� �����
				Comparable<Element> comparable2 = (Comparable<Element>) lastElement;
				if(comparable2.compareTo((Element)this._tree[biggerChild]) >= 0){
					break;
				}
				this._tree[parent] = this._tree[biggerChild];
				parent = biggerChild;
			}
			this._tree[parent] = lastElement;	//parent�ڸ��� lastElement�� �ִ´�
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
