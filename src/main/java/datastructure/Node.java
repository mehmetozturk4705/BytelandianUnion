package datastructure;

/**
 * Node is point of information in UnionGraph.
 * @param <T> Type of information which will be stored in Node
 */
public class Node<T> {
    private T nodeValue;//Value
    private boolean unified=false;//Unified state in UnionGraph

    /**
     * Creates a Node object.
     * @param nodeValue Value which will be stored in Node.
     */
    public Node(T nodeValue){
        this.nodeValue = nodeValue;
    }

    /**
     * Gets unified state in UnionGraph.
     * @return Unified state.
     */
    public boolean isUnified(){
        return unified;
    }

    /**
     * Sets unified state in UnionGraph.
     * @param unified Unified state.
     */
    public void setUnified(boolean unified){
        this.unified = unified;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<T> node = (Node<T>) o;

        return nodeValue.equals(node.nodeValue);

    }

    @Override
    public int hashCode() {
        return nodeValue.hashCode();
    }

    /**
     * Retrieves node value.
     * @return Node value stored in Node.
     */
    public T getNodeValue() {
        return nodeValue;
    }

    /**
     * Sets node value.
     * @param nodeValue Node value which will be stored.
     */
    public void setNodeValue(T nodeValue) {
        this.nodeValue = nodeValue;
    }

}
