package LinkedList;

public class Nodo<T> {
	private T data;
    private Nodo<T> next;

    public Nodo(T data) {
    	this(data, null);
    }

    public Nodo(T data, Nodo<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Nodo<T> getNext() {
        return this.next;
    }

    public void setNext(Nodo<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "NODO: Data -> " + data;
    }
}
