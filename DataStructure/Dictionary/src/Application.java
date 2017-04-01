
public class Application {
	PMDictionary _pmDictionary;
	
	void run() {
		this._pmDictionary = new PMDictionary();
		this._pmDictionary.generateData();
		this._pmDictionary.testSortedArrayDictionary();
		this._pmDictionary.testSortedLinkedDictionary();
		this._pmDictionary.testBinarySearchTreeDictionary();
	}
}
