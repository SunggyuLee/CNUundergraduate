public class ArraySet {

	private static final int DEFAULT_MAX_SIZE = 100;
	private static final int DEFAULT_MAX_COORDINATE_SIZE = 100; // 좌표의 max값
	private int _maxSize;
	private int _size;
	private Star[] _stars; // ArraySet의 원소들을 담을 java 배열

	public ArraySet() {
		this._size = 0;
		this._maxSize = DEFAULT_MAX_SIZE;
		this._stars = new Star[DEFAULT_MAX_SIZE];
	}

	public ArraySet(int givenMaxSize) {
		this._size = 0;
		this._maxSize = givenMaxSize;
		this._stars = new Star[givenMaxSize];
	}

	public int size() {
		return this._size;
	}

	public boolean isEmpty() {
		return this._size == 0;

	}

	public boolean isFull() {
		return this._size == this._maxSize;
	}

	public boolean doesContain(Star givenStar) {
		boolean found = false;

		for (int i = 0; i < this._size; i++) {
			if (this._stars[i].theSameValueAs(givenStar)) {
				found = true;
				break;
			} else
				found = false;
		}
		return found;
	}

	public boolean add(Star aStar) {
		if (this.isFull())
			return false;
		else if (Math.abs(aStar.xCoordinate()) <= DEFAULT_MAX_COORDINATE_SIZE 
				&& Math.abs(aStar.yCoordinate()) <= DEFAULT_MAX_COORDINATE_SIZE
				&& (this.doesContain(aStar))==false){
			if(this.isEmpty()){
				this._stars[0] = aStar;
				this._size++;
				System.out.println("[ADD]"+ aStar.xCoordinate()+","+aStar.yCoordinate()+","+aStar.starName());
				return true;
			} else {
				this._stars[this._size] = aStar;
				this._size++;
				System.out.println("[ADD]"+ aStar.xCoordinate()+","+aStar.yCoordinate()+","+aStar.starName());
				return true;
			}
		} else {
			System.out.println("[오류] 입력 실패");
			return false;
		}
	}

	public boolean remove(Star givenStar) {
		int foundIndex;
		boolean found = false;
		for (foundIndex = 0; foundIndex < this._maxSize && !found; foundIndex++) {
			if (this._stars[foundIndex].theSameValueAs(givenStar)) {
				found = true;
			}
		}
		foundIndex--;
		if (!found) { // !found는 여기서 false를 의미
			return false;
		} else {
			for (int j = foundIndex; j < this._size - 1; j++) {
				this._stars[j] = this._stars[j + 1];
			}
			this._size--;
			System.out.println("[REMOVE]"+ this._stars[foundIndex].xCoordinate()+","
			+this._stars[foundIndex].yCoordinate()+","+this._stars[foundIndex].starName());
			return true;
		}
	}

	public boolean removeAny() {
		this._stars[this._size-1] = null;
		this._size--;
		System.out.println("[REMOVE] 임의의 별 삭제");
		return true;
	}

	public void clear() {
		for (int i = 0; i < this._size; i++) {
			this._stars[i] = null;
		}
		this._size = 0;
	}
	
}
