package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GraphListEdge<V,E> {
    ArrayList<VertexObj<V,E>> secVertex;
    ArrayList<EdgeObj<V,E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    public void insertVertex(V v) {
        if (!searchVertex(v)) {
            secVertex.add(new VertexObj<>(v, secVertex.size()));
        }
    }

    public void insertEdge(V v, V z) {
        VertexObj<V,E> vert1 = getVertex(v);
        VertexObj<V,E> vert2 = getVertex(z);

        if (vert1 != null && vert2 != null && !searchEdge(v, z)) {
            secEdge.add(new EdgeObj<>(vert1, vert2, null, secEdge.size()));
        }
    }

    public boolean searchVertex(V v) {
        return getVertex(v) != null;
    }

    public boolean searchEdge(V v, V z) {
        VertexObj<V,E> vert1 = getVertex(v);
        VertexObj<V,E> vert2 = getVertex(z);
        if (vert1 == null || vert2 == null) return false;
        for (EdgeObj<V,E> edge : secEdge) {
            if (edge.connects(vert1, vert2)) return true;
        }
        return false;
    }

    public void bfs(V v) {
        VertexObj<V,E> start = getVertex(v);
        if (start == null) {
            System.out.println("Vértice no encontrado");
            return;
        }

        Set<VertexObj<V,E>> visited = new HashSet<>();
        Queue<VertexObj<V,E>> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            VertexObj<V,E> current = queue.poll();
            System.out.print(current + " ");

            for (EdgeObj<V,E> edge : secEdge) {
                VertexObj<V,E> neighbor = null;
                if (edge.endVertex1.equals(current)) neighbor = edge.endVertex2;
                else if (edge.endVertex2.equals(current)) neighbor = edge.endVertex1;

                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    private VertexObj<V,E> getVertex(V v) {
        for (VertexObj<V,E> vertex : secVertex) {
            if (vertex.info.equals(v)) return vertex;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Vértices: " + secVertex + "\nAristas: " + secEdge;
    }
}
