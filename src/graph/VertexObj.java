package graph;

public class VertexObj<V,E> {
    protected V info;
    protected int position;

    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof VertexObj<?,?> other) {
            return this.info.equals(other.info);
        }
        return false;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}
