
public class Element<Key,Obj> {
	private Key _key;
	private Obj _object;
	
	public Element (){
		this._key = null;
		this._object = null;
	}
	
	public Element (Key aKey){
		this._key = aKey;
	}
	
	public Element (Key aKey, Obj anObject){
		this._key = aKey;
		this._object = anObject;
	}
	
	public Key key(){
		return this._key;
	}
	
	public void setKey(Key givenKey){
		this._key = givenKey;
	}
	
	public Obj object (){
		return this._object;
	}
	
	public void setObject (Obj anObject){
		this._object = anObject;
	}
	
	public int compareTo(Key givenKey){
		return (Integer.parseInt(this._key.toString())-Integer.parseInt(givenKey.toString()));
	}
	
}
