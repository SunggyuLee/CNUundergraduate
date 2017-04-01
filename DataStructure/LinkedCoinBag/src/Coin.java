public class Coin {
	private int _value;

	public Coin() {
	}

	public Coin(int aValue) {
		this._value = aValue;
	}

	public int value() {
		return this._value;
	}

	public void setValue(int aValue) {
		this._value = aValue;
	}

	public boolean theSameValueAs(Coin givenCoin) {
		return (givenCoin.value() == this._value);

	}

}
