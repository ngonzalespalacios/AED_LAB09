package graph;
import LinkedList.*;

public class Vertex<E> {
    private E data;
    protected ListLinked<Edge<E>> listAdj;
    private boolean visited; 

    public Vertex(E data) {
        this.data = data;
        this.listAdj = new ListLinked<Edge<E>>();
        this.visited = false;
    }

    public E getData() {
        return data;
    }

    public boolean equals(Object o) {
        if (o instanceof Vertex<?>) {
            Vertex<E> v = (Vertex<E>) o;
            return this.data.equals(v.data);
        }
        return false;
    }
    
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

@Override
    public String toString() {
        return this.data + " --> " + this.listAdj.toString() + "\n";
    }
}
