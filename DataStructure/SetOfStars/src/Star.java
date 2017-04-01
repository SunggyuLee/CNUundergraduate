public class Star {
	private int _xCoordinate; // X ÁÂÇ¥
	private int _yCoordinate; // Y ÁÂÇ¥
	private String _starName; // º°ÀÇ ÀÌ¸§

	public Star() {
		this._starName = null;
		this._xCoordinate = 0;
		this._yCoordinate = 0;
	}

	public Star(int aX, int aY) {
		this._starName = null;
		this._xCoordinate = aX;
		this._yCoordinate = aY;
	}

	public Star(String aStarName) {
		this._starName = aStarName;
		this._xCoordinate = 0;
		this._yCoordinate = 0;
	}

	public Star(int aX, int aY, String aStarName) {
		this._starName = aStarName;
		this._xCoordinate = aX;
		this._yCoordinate = aY;
	}

	public int xCoordinate() {
		return this._xCoordinate;
	}

	public int yCoordinate() {
		return this._yCoordinate;
	}

	public String starName() {
		return this._starName;
	}

	public void setXCoordinate(int aX) {
		this._xCoordinate = aX;
	}

	public void setYCoordinate(int aY) {
		this._yCoordinate = aY;
	}

	public void setStarName(String aStarName) {
		this._starName = aStarName;
	}

	public boolean theSameValueAs(Star givenStar) {
		if (this._xCoordinate == givenStar._xCoordinate && this._yCoordinate == givenStar._yCoordinate)
			return true;
		else if (this._starName != null && this._starName.equals(givenStar._starName))
			return true;
		else
			return false;
	}
}
