package graph;
import java.util.LinkedList;
public class Vertex<E> {
    private E data;
    private LinkedList<Vertex<E>> adjacentVertices;

    public Vertex(E data) {
        this.data = data;
        this.adjacentVertices = new LinkedList<>();
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public LinkedList<Vertex<E>> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void addAdjacentVertex(Vertex<E> vertex) {
        adjacentVertices.add(vertex);
    }

    @Override
    public String toString() {
        return "Vertex{" + "data=" + data + '}';
    }
}
