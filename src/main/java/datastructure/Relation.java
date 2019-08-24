package datastructure;

import exceptions.LogicViolationException;

/**
 * Relation represents connections in UnionGraph.
 */
public class Relation {
    private Node<Integer> firstNode;
    private Node<Integer> secondNode;

    /**
     * Creates a Relation which will represent a graph relation between firstNode and secondNode.
     * @param firstNode Node object.
     * @param secondNode Node object.
     * @throws LogicViolationException
     */
    public Relation(Node<Integer> firstNode, Node<Integer> secondNode) throws LogicViolationException{
        if(firstNode.equals(secondNode))
            throw new LogicViolationException("A Node cannot be connected to itself.");
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }

    /**
     * Gets the first node of relation.
     * @return Node object.
     */
    public Node<Integer> getFirstNode() {
        return firstNode;
    }

    /**
     * Gets the second node of relation.
     * @return Node object.
     */
    public Node<Integer> getSecondNode() {
        return secondNode;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relation relation = (Relation) o;

        return (firstNode.equals(relation.firstNode)&&secondNode.equals(relation.secondNode))||
                (firstNode.equals(relation.secondNode)&&(secondNode.equals(relation.firstNode))) ;

    }

    @Override
    public int hashCode() {
        return Math.min(firstNode.getNodeValue(), secondNode.getNodeValue()) +
                3100*Math.max(firstNode.getNodeValue(), secondNode.getNodeValue());
    }
}
