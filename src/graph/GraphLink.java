package graph;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import org.w3c.dom.Node;

import LinkedList.*;

public class GraphLink<E> {
    private ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<Vertex<E>>();
    }

    public void inserVertex(E data) {
        Vertex<E> v = new Vertex<E>(data);
        if (this.listVertex.search(v) != -1)
            System.out.println("El vertice ta fue insertado previamente ...");
        else
            this.listVertex.insertFirst(v);
    }
    
    public void insertEdge(E verOri, E verDes) {
        Vertex<E> ori = this.listVertex.searchNode(new Vertex<E>(verOri));
        Vertex<E> des = this.listVertex.searchNode(new Vertex<E>(verDes));

        if (ori == null || des == null) {
            System.out.println("Alguno de los vertices no existe ...");
        } else {
            Edge<E> e = new Edge<E>(des);
            if (ori.listAdj.search(e) != -1)
                System.out.println("Arista ya fue insertada previamente ...");
            else
                ori.listAdj.insertFirst(e);
        }
    }

    public boolean searchVertex(E data) {
        return listVertex.search(new Vertex<>(data)) != -1;
    }
    
    public boolean searchEdge(E dataOri, E dataDes) {
        Vertex<E> ori = listVertex.searchNode(new Vertex<>(dataOri));
        Vertex<E> des = listVertex.searchNode(new Vertex<>(dataDes));

        if (ori == null || des == null) {
            return false;
        }

        Edge<E> edge = new Edge<>(des);
        return ori.listAdj.search(edge) != -1;
    }
    
    public void removeVertex(E data) {
        Vertex<E> toRemove = listVertex.searchNode(new Vertex<>(data));
        if (toRemove == null) {
            System.out.println("El vértice no existe...");
            return;
        }
        // Recorrer todos los vértices y eliminar las aristas que apunten al vértice que se va a eliminar
        Nodo<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            current.getData().listAdj.remove(new Edge<>(toRemove));
            current = current.getNext();
        }
        // Eliminar el vértice de la lista de vértices
        listVertex.remove(toRemove);
    }
    
    public void removeEdge(E dataOri, E dataDes) {
        Vertex<E> origin = listVertex.searchNode(new Vertex<>(dataOri));
        Vertex<E> dest = listVertex.searchNode(new Vertex<>(dataDes));

        if (origin == null || dest == null) {
            System.out.println("Alguno de los vértices no existe...");
            return;
        }

        origin.listAdj.remove(new Edge<>(dest));
    }
    
    public void dfs(E v) {
        // Reinicia todos los visited en false
        Nodo<Vertex<E>> aux = listVertex.getHead();
        while (aux != null) {
            aux.getData().setVisited(false);
            aux = aux.getNext();
        }
        
        // Buscar el vértice inicial
        Vertex<E> start = listVertex.searchNode(new Vertex<>(v));
        if (start == null) {
            System.out.println("El vértice no existe...");
            return;
        }
        System.out.println("Recorrido DFS desde: " + v);
        dfsRecursive(start);
    }
    private void dfsRecursive(Vertex<E> v) {
        System.out.println(v.getData());
        v.setVisited(true);
        Nodo<Edge<E>> adj = v.listAdj.getHead();
        while (adj != null) {
            Vertex<E> neighbor = adj.getData().refDest;
            if (!neighbor.isVisited()) {
                dfsRecursive(neighbor);
            }
            adj = adj.getNext();
        }
    }

        
    public void bfs(E v) {
        // Reiniciar todos los visitados
        Nodo<Vertex<E>> aux = listVertex.getHead();
        while (aux != null) {
            aux.getData().setVisited(false);
            aux = aux.getNext();
        }
    
        // Buscar el vértice inicial
        Vertex<E> start = listVertex.searchNode(new Vertex<>(v));
        if (start == null) {
            System.out.println("El vértice no existe...");
            return;
        }
    
        Queue<Vertex<E>> queue = new LinkedList<>();
        start.setVisited(true);
        queue.offer(start);
    
        System.out.println("Recorrido BFS desde: " + v);
        
        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            System.out.println(current.getData());
    
            Nodo<Edge<E>> edgeNode = current.listAdj.getHead();
            while (edgeNode != null) {
                Vertex<E> neighbor = edgeNode.getData().refDest;
                if (!neighbor.isVisited()) {
                    neighbor.setVisited(true);
                    queue.offer(neighbor);
                }
                edgeNode = edgeNode.getNext();
            }
        }
    }
    
    public ArrayList<E> bfsPath(E v, E z) {
        int n = listVertex.lenght();
        Vertex<E>[] vertices = new Vertex[n];
        boolean[] visited = new boolean[n];
        int[] prev = new int[n];
        
        // Inicializar estructuras
        Nodo<Vertex<E>> aux = listVertex.getHead();
        for (int i = 0; i < n; i++) {
            vertices[i] = aux.getData();
            visited[i] = false;
            prev[i] = -1;
            aux = aux.getNext();
        }

        int start = indexOf(v, vertices);
        int end = indexOf(z, vertices);
        if (start == -1 || end == -1) return new ArrayList<>(); // No existe uno de los vértices

        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            Vertex<E> vertex = vertices[current];

            Nodo<Edge<E>> adj = vertex.listAdj.getHead();
            while (adj != null) {
                Vertex<E> neighbor = adj.getData().refDest;
                int idx = indexOf(neighbor.getData(), vertices);
                if (!visited[idx]) {
                    visited[idx] = true;
                    prev[idx] = current;
                    queue.offer(idx);
                    if (idx == end) break; // se encontró el destino
                }
                adj = adj.getNext();
            }
        }

        // reconstruir camino
        ArrayList<E> path = new ArrayList<>();
        for (int at = end; at != -1; at = prev[at]) {
            path.add(0, vertices[at].getData());
        }

        if (!visited[end]) return new ArrayList<>(); // No se llegó al destino
        return path;
    }

    public void insertEdgeWeight(E v, E z, int w) {
        Vertex<E> ori = listVertex.searchNode(new Vertex<>(v));
        Vertex<E> des = listVertex.searchNode(new Vertex<>(z));
        if (ori == null || des == null) {
            System.out.println("Alguno de los vértices no existe.");
            return;
        }
        
        Edge<E> edge1 = new Edge<>(des, w);
        if (ori.listAdj.search(edge1) == -1) {
            ori.listAdj.insertFirst(edge1);
        }
        
        Edge<E> edge2 = new Edge<>(ori, w);
        if (des.listAdj.search(edge2) == -1) {
            des.listAdj.insertFirst(edge2);
        }
    }

    public ArrayList<E> shortPath(E v, E z) {
        int n = listVertex.lenght();
        Vertex<E>[] vertices = new Vertex[n];
        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];

        Nodo<Vertex<E>> aux = listVertex.getHead();
        for (int i = 0; i < n; i++) {
            vertices[i] = aux.getData();
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            visited[i] = false;
            aux = aux.getNext();
        }

        int start = indexOf(v, vertices);
        int end = indexOf(z, vertices);
        if (start == -1 || end == -1) return new ArrayList<>();

        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            int u = minVertex(dist, visited);
            if (u == -1) break;
            visited[u] = true;

            Nodo<Edge<E>> edge = vertices[u].listAdj.getHead();
            while (edge != null) {
                Vertex<E> neighbor = edge.getData().refDest;
                int idx = indexOf(neighbor.getData(), vertices);
                int peso = edge.getData().weight;
                if (!visited[idx] && dist[u] + peso < dist[idx]) {
                    dist[idx] = dist[u] + peso;
                    prev[idx] = u;
                }
                edge = edge.getNext();
            }
        }

        // reconstruir camino
        ArrayList<E> path = new ArrayList<>();
        for (int at = end; at != -1; at = prev[at])
            path.add(0, vertices[at].getData());

        if (dist[end] == Integer.MAX_VALUE) return new ArrayList<>(); // no hay camino
        return path;
    }

    private int minVertex(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }

    public boolean isConexo() {
        Nodo<Vertex<E>> aux = listVertex.getHead();
        while (aux != null) {
            aux.getData().setVisited(false);
            aux = aux.getNext();
        }

        Vertex<E> inicio = listVertex.getHead().getData();
        dfsRecursive(inicio);

        aux = listVertex.getHead();
        while (aux != null) {
            if (!aux.getData().isVisited())
                return false;
            aux = aux.getNext();
        }
        return true;
    }

    public Stack<E> dijkstra(E v, E z) {
        Stack<E> stack = new Stack<>();

        int n = listVertex.lenght();
        Vertex<E>[] vertices = new Vertex[n];
        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];

        Nodo<Vertex<E>> aux = listVertex.getHead();
        for (int i = 0; i < n; i++) {
            vertices[i] = aux.getData();
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            visited[i] = false;
            aux = aux.getNext();
        }

        int start = indexOf(v, vertices);
        int end = indexOf(z, vertices);
        if (start == -1 || end == -1) return stack;

        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            int u = minVertex(dist, visited);
            if (u == -1) break;
            visited[u] = true;

            Nodo<Edge<E>> edge = vertices[u].listAdj.getHead();
            while (edge != null) {
                Vertex<E> neighbor = edge.getData().refDest;
                int idx = indexOf(neighbor.getData(), vertices);
                int peso = edge.getData().weight;
                if (!visited[idx] && dist[u] + peso < dist[idx]) {
                    dist[idx] = dist[u] + peso;
                    prev[idx] = u;
                }
                edge = edge.getNext();
            }
        }

        for (int at = end; at != -1; at = prev[at])
            stack.push(vertices[at].getData());

        // invertir para obtener v → z
        Stack<E> result = new Stack<>();
        while (!stack.isEmpty()) result.push(stack.pop());
        return result;
    }


    private int indexOf(E data, Vertex<E>[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getData().equals(data))
                return i;
        }
        return -1;
    }


    @Override
    public String toString() {
        return this.listVertex.toString();
    }
}