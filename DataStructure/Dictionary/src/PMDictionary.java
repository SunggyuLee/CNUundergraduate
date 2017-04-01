import java.util.Random;

public class PMDictionary {

	private static final int MaxTestSize = 500;
	private static final int FirstTestSize = 100; // 첫번째 실험 데이터 크기
	private static final int SizeIncrement = 100; // 실험 데이터 크기 증가량
	private int _maxTestSize;
	private Element<Integer, Integer>[] _element; // array for test data

	public PMDictionary(){
		this._maxTestSize = MaxTestSize;
		this._element = new Element[this._maxTestSize];
	}
	public void generateData() {
		Random random = new Random();
		int i = 0;
		while (i < this._maxTestSize) {
			int newData = random.nextInt(this._maxTestSize);
			if (!exist(newData, i)) {
				Element<Integer, Integer> newElement = new Element<Integer, Integer>(
						newData, i);
				this._element[i] = newElement;
				i++;
			}
		}
	}

	private boolean exist(int newData, int numOfCheck) {
		for (int i = 0; i < numOfCheck; i++)
			if (this._element[i].key() == newData)
				return true;
		return false;
	}

	public void testSortedArrayDictionary() {
		SortedArrayDictionary<Integer, Integer> dic = new SortedArrayDictionary<Integer,Integer>(this._maxTestSize+1);
		double insertTime, searchTime, deleteTime;
		long start, end;
		
		for(int i=0; i<this._maxTestSize; i++)
			dic.addKeyandObject(this._element[i].key(), this._element[i].object());
		System.out.println("Start testSortedArrayDictionary");
		for(int testSize = FirstTestSize ; testSize <= this._maxTestSize; testSize += SizeIncrement){
			insertTime = 0.0;
			for(int i =0;i<testSize;i++){
				start = System.nanoTime();
				dic.addKeyandObject(this._element[i].key(), this._element[i].object());
				end = System.nanoTime();
				insertTime += (double) (end-start);
			}
			searchTime = 0.0;
			for(int i=0;i<testSize;i++){
				Integer obj = null;
				start = System.nanoTime();
				obj = dic.objectForKey(this._element[i].key());
				end = System.nanoTime();
				searchTime += (double) (end-start);
			}
			deleteTime = 0.0;
			for(int i=0;i<testSize;i++){
				Integer obj = null;
				start = System.nanoTime();
				obj = dic.removeObjectForKey(this._element[i].key());
				end = System.nanoTime();
				deleteTime += (double) (end-start);
			}
			System.out.println("크기 : " + testSize + "\t삽입 : " + (long) insertTime + "\t검색 : "+(long) searchTime + "\t삭제 : " + (long) deleteTime);
		}
		
	}

	public void testSortedLinkedDictionary() {
		SortedLinkedDictionary<Integer, Integer> dic = new SortedLinkedDictionary<Integer,Integer>(this._maxTestSize+1);
		double insertTime, searchTime, deleteTime;
		long start, end;
		
		for(int i=0; i<this._maxTestSize; i++)
			dic.addKeyandObject(this._element[i].key(), this._element[i].object());
		System.out.println("Start testSortedLinkedDictionary");
		for(int testSize = FirstTestSize ; testSize <= this._maxTestSize; testSize += SizeIncrement){
			insertTime = 0.0;
			for(int i =0;i<testSize;i++){
				start = System.nanoTime();
				dic.addKeyandObject(this._element[i].key(), this._element[i].object());
				end = System.nanoTime();
				insertTime += (double) (end-start);
			}
			searchTime = 0.0;
			for(int i=0;i<testSize;i++){
				Integer obj = null;
				start = System.nanoTime();
				obj = dic.objectForKey(this._element[i].key());
				end = System.nanoTime();
				searchTime += (double) (end-start);
			}
			deleteTime = 0.0;
			for(int i=0;i<testSize;i++){
				Integer obj = null;
				start = System.nanoTime();
				obj = dic.removeObjectForKey(this._element[i].key());
				end = System.nanoTime();
				deleteTime += (double) (end-start);
			}
			System.out.println("크기 : " + testSize + "\t삽입 : " + (long) insertTime + "\t검색 : "+(long) searchTime + "\t삭제 : " + (long) deleteTime);
		}
		
	}

	public void testBinarySearchTreeDictionary() {
		BinarySearchTreeDictionary<Integer, Integer> dic = new BinarySearchTreeDictionary<Integer,Integer>();
		double insertTime, searchTime, deleteTime;
		long start, end;
		
		for(int i=0; i<this._maxTestSize; i++)
			dic.addKeyandObject(this._element[i].key(), this._element[i].object());
		System.out.println("Start testBinarySearchTree");
		for(int testSize = FirstTestSize ; testSize <= this._maxTestSize; testSize += SizeIncrement){
			insertTime = 0.0;
			for(int i =0;i<testSize;i++){
				start = System.nanoTime();
				dic.addKeyandObject(this._element[i].key(), this._element[i].object());
				end = System.nanoTime();
				insertTime += (double) (end-start);
			}
			searchTime = 0.0;
			for(int i=0;i<testSize;i++){
				Integer obj = null;
				start = System.nanoTime();
				obj = dic.objectForKey(this._element[i].key());
				end = System.nanoTime();
				searchTime += (double) (end-start);
			}
			deleteTime = 0.0;
			for(int i=0;i<testSize;i++){
				Integer obj = null;
				start = System.nanoTime();
				obj = dic.removeObjectForKey(this._element[i].key());
				end = System.nanoTime();
				deleteTime += (double) (end-start);
			}
			System.out.println("크기 : " + testSize + "\t삽입 : " + (long) insertTime + "\t검색 : "+(long) searchTime + "\t삭제 : " + (long) deleteTime);
		}
		
	}
}
