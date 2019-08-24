package datastructure;

import exceptions.LogicViolationException;
import exceptions.NotUnifiableException;

import java.util.*;

/**
 * Graph object which calculates minimum number of steps needed to unite all nodes in a graph.
 * This class uses graph theory principles in order to give solution.
 */
public class UnionGraph {
    private int unificationStep = 0;
    private List<List<Integer>> adjacencyMatrix;
    private boolean graphBuilt = false;

    /**
     * Creates new UnionGraph object with given adjacency matrix.
     * @param adjacencyMatrix Adjacency matrix represents neighbourhood between Integer valued nodes.
     *                        It uses fashion below:
     *                               [ [1, 2], [0, 2], [0, 1] ]
     *                               Matrix above represents 0's neighbours are 1 and 2; 1's neighbours are 0 and 2; 2's neighbours are 0 and 1.
     * @throws LogicViolationException
     */
    public UnionGraph(List<List<Integer>> adjacencyMatrix) throws LogicViolationException{
        this.adjacencyMatrix = adjacencyMatrix;
        buildGraph();
    }
    private Set<Relation> relations;
    private Set<Node<Integer>> nodes;

    /**
     * Sets adjacency matrix and build graph respecting to the adjacency matrix.
     * @param adjacencyMatrix Adjacency matrix.
     * @throws LogicViolationException
     */
    public void setAdjacencyMatrix(List<List<Integer>> adjacencyMatrix) throws LogicViolationException{
        this.adjacencyMatrix = adjacencyMatrix;
        buildGraph();
    }
    private void buildGraph() throws LogicViolationException {
        if(this.adjacencyMatrix==null){
            throw new NullPointerException("Adjacency matrix is not set yet.");
        }
        this.relations = new HashSet<Relation>();//Fresh relations.
        this.nodes = new HashSet<Node<Integer>>();//Fresh nodes.
        for(int i = 0; i<adjacencyMatrix.size(); i++){
            List<Integer> currentRow = adjacencyMatrix.get(i);
            Node<Integer> firstNode = new Node<Integer>(i);
            addNode(firstNode);
            for(Integer second: currentRow){
                Node<Integer> secondNode = new Node<Integer>(second);
                addNode(secondNode);
                //Checking if there is connection loop
                if(!firstNode.equals(secondNode))
                    //Nodes are retrieved from context because address blocks should be same as node object itself.
                    addRelation(new Relation(retrieveNode(firstNode), retrieveNode(secondNode)));//Adds nodes and relations
            }
        }
    }
    private Node<Integer> getBestNodeToUnify(Node<Integer> node){
        /*
        Cost is calculated respecting to number of relations of neighbour.
         */
        Integer minCost=null;
        Node<Integer> nodeWithMinCost = null;
        List<Node<Integer>> neighbours = getNotUnifiedNeighbours(node);
        //Find minimum costed Node.
        for(Node<Integer> currentNode: neighbours){
            int numberOfNeighbours = getNeighbours(currentNode).size();
            if(minCost==null){
                minCost=numberOfNeighbours;
                nodeWithMinCost = currentNode;
            }
            else if(numberOfNeighbours<minCost){
                minCost=numberOfNeighbours;
                nodeWithMinCost = currentNode;
            }
        }
        return nodeWithMinCost;
    }

    /**
     * Retrieves node with node id.
     * @param nodeId Node id.
     * @return
     */
    public Node<Integer> retrieveNode(int nodeId){
        return retrieveNode(new Node<Integer>(nodeId));
    }

    /**
     * Retrieves node with Node object.
     * @param node Node object.
     * @return
     */
    public Node<Integer> retrieveNode(Node<Integer> node){
        Node<Integer> foundNode = null;
        for(Node<Integer> currentNode: getNodes()){
            if(currentNode.equals(node)){
                foundNode = currentNode;
                break;
            }
        }
        return foundNode;
    }

    /**
     * Adds relation to context.
     * @param relation
     */
    public void addRelation(Relation relation){
        if(retrieveNode(relation.getFirstNode())==null||retrieveNode(relation.getSecondNode())==null)
            throw new NullPointerException("One of the nodes is not found.");
        this.relations.add(relation);
    }

    /**
     * Get nodes of context.
     * @return Set of Node object.
     */
    public Set<Node<Integer>> getNodes(){
        return this.nodes;
    }

    /**
     * Get relations of context.
     * @return Set of Relation object.
     */
    public Set<Relation> getRelations(){
        return this.relations;
    }

    /**
     * Adds a node to the context.
     * @param node Node object.
     */
    public void addNode(Node<Integer> node){
        this.nodes.add(node);
    }
    /**
     * Removes a node from context.
     * @param node Node object.
     */
    public void removeNode(Node<Integer> node){
        for(Relation relation:new HashSet<Relation>(getRelations())){
            if(relation.getFirstNode().equals(node)||relation.getSecondNode().equals(node)){
                removeRelation(relation);
            }
        }
        this.nodes.remove(node);
    }

    /**
     * Removes a relation from th context.
     * @param relation Relation object.
     */
    public void removeRelation(Relation relation){
        this.relations.remove(relation);
    }
    private List<Node<Integer>> getNeighbours(Node<Integer> node){
        List<Node<Integer>> neighbours = new ArrayList<Node<Integer>>();
        for(Relation relation: this.relations){
            if(relation.getFirstNode().equals(node)){
                neighbours.add(relation.getSecondNode());
            }
            else if(relation.getSecondNode().equals(node)){
                neighbours.add(relation.getFirstNode());
            }
        }
        return neighbours;
    }
    private List<Node<Integer>> getNotUnifiedNeighbours(Node<Integer> node){
        //Searches for not unified nodes.
        List<Node<Integer>> neighbours = new ArrayList<Node<Integer>>();
        for(Relation relation: this.relations){
            if(relation.getFirstNode().equals(node)&&!relation.getSecondNode().isUnified()){
                neighbours.add(relation.getSecondNode());
            }
            else if(relation.getSecondNode().equals(node)&&!relation.getFirstNode().isUnified()){
                neighbours.add(relation.getFirstNode());
            }
        }
        return neighbours;
    }
    private void clearUnifiedState(){
        for(Node<Integer> node: nodes){
            node.setUnified(false);
        }
    }

    /**
     * Unifies all nodes respecting to restrictions in Byteland Union problem given in assessment.
     * @return Minimum number of steps to unify all nodes.
     * @throws NotUnifiableException
     * @throws LogicViolationException
     */
    public int unifyAndReturnStepNumber() throws NotUnifiableException, LogicViolationException{
        /*
        If there is no relation left, and there are multiple nodes, structure is not unifiable.
         */
        if(getNodes().size()>1 && getRelations().size()==0){
            throw new NotUnifiableException("This structure is not unifiable.");
        }
        if(getNodes().size()==1){
            return this.unificationStep;
        }
        else {
            Set<Node<Integer>> copyOfNodes = new HashSet<Node<Integer>>(nodes);
            for(Node<Integer> node:copyOfNodes){
                if(!node.isUnified()){
                    Node<Integer> otherNode = getBestNodeToUnify(node);
                    if(otherNode != null){
                        node.setUnified(true);
                        otherNode.setUnified(true);
                        unifyNodes(node, otherNode);
                    }
                }
            }
            clearUnifiedState();
            this.unificationStep++;
            return unifyAndReturnStepNumber();
        }
    }
    private void unifyNodes(Node<Integer> firstNode, Node<Integer> secondNode) throws LogicViolationException{
        if(firstNode.equals(secondNode)){
            throw new LogicViolationException("First and second node are same");
        }
        for(Node node: getNeighbours(secondNode)){
            if(!firstNode.equals(node))
                addRelation(new Relation(firstNode, node));
        }
        removeNode(secondNode);
    }
}
