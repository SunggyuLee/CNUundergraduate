
public interface PriorityQueue<Element> {
	
	public boolean add(Element anElement);
	public Element max();
	public Element removeMax();
	public int size();
	public boolean isEmpty();
	public boolean isFull();
}
