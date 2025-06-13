package LinkedList;

import graph.Vertex;

public class ListLinked<T> implements TDAList<T>{
	private Nodo<T> head;

    public ListLinked() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null; 
    }

    public int lenght() {
        Nodo<T> aux = this.head;
        int cont = 0;
        while (aux != null) {
            cont++;
            aux = aux.getNext();
        }
        return cont;
    }


    public void destroyList() {
        this.head = null;
    }


    public int search(T searchItem) {
        int index = 0;
        Nodo<T> aux = this.head;
        while (aux != null) {
            if (aux.getData().equals(searchItem)) {
                return index;
            }
            index++;
            aux = aux.getNext();
        }
        return -1;
    }
    
    public T searchList(T item) {
        Nodo<T> aux = this.head;
        while (aux != null) {
            if (aux.getData().equals(item)) {
                return aux.getData();
            }
            aux = aux.getNext();
        }
        return null;
    }

    public T searchNode(T x) {
        Nodo<T> aux = this.head;
        for (; aux != null; aux = aux.getNext()) {
            if (aux.getData().equals(x))
                return aux.getData();
        }
        return null;
    }


    public void insertFirst(T newItem) {
        Nodo<T> nodo = new Nodo<T>(newItem);
        nodo.setNext(head);
        this.head = nodo;

    }

    public void insertLast(T newItem) {
        Nodo<T> aux = this.head; 
        Nodo<T> nodo = new Nodo<T>(newItem); 
        if (this.isEmpty()) { 
            this.head = nodo; 
            return; 
        }

        while (aux.getNext() != null) { 
            aux = aux.getNext(); 

        }
        aux.setNext(nodo); 
    }


    public void remove(T deleteItem) {
        Nodo<T> aux = this.head; 

        // primer elemento
        if (this.head.getData().equals(deleteItem)) {
            this.head = this.head.getNext();
            return;
        }

        while (aux.getNext() != null) {
            if (aux.getNext().getData().equals(deleteItem)) {
                aux.setNext(aux.getNext().getNext());
                return;
            }
            aux = aux.getNext();
        }
    }

    @Override
    public String toString() {
        String s = "";
        Nodo<T> aux = this.head;
        for (; aux != null; aux = aux.getNext()) {
            s += aux.getData().toString();
        }
        return s;
    }

    public Nodo<T> getHead() {
        return this.head;
    }
}
