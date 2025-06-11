package LinkedList;

public interface TDAList<T>{
	boolean isEmpty();
    int lenght();
    void destroyList();
    int search(T x);
    void insertFirst(T x);
    void insertLast(T x);
    void remove(T x);
}
