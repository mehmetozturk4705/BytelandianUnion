import datastructure.UnionGraph;
import exceptions.LogicViolationException;
import exceptions.NotUnifiableException;
import exceptions.WrongInputException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class BytelandUnionExperiment {
    private List<List<Integer>> adjacencyMatrix;
    private Integer numOfCities;

    /**
     * Creates experiment object which represents single experiment.
     * @param numOfCities Number of cities in the current experiment. This parameter should fullfill the condition [2<=numOfCities<=600)
     * @throws WrongInputException
     */
    public BytelandUnionExperiment(Integer numOfCities) throws WrongInputException {
        //Number of cities in this experiment
        if(numOfCities<2 || numOfCities>600){
            throw new WrongInputException("numOfCities should be such that it can fulfill expression 2<=numOfCities<=600");
        }
        this.numOfCities = numOfCities;
        //Capacity pre-allocated for performance.
        this.adjacencyMatrix = new ArrayList<List<Integer>>(numOfCities);
    }

    /**
     * Sets adjacency matrix for given string value.
     * @param matrixString Matrix string
     * @throws WrongInputException
     */
    public void setAdjacencyMatrix(@NotNull String matrixString) throws WrongInputException {
        if(matrixString == null){
            throw new NullPointerException("matrixString cannot be null!");
        }
        //Parsing of value string
        String[] matrixValueStrings = matrixString.trim().split("( )+");
        List<Integer> matrixValues = new LinkedList<Integer>();
        for(String valueString:matrixValueStrings){
            try {
                matrixValues.add(Integer.parseInt(valueString.trim()));
            }
            catch (NumberFormatException e){
                throw new WrongInputException("Value string should consists of integer values which spaced eachother.");
            }
        }
        setAdjacencyMatrix(matrixValues.toArray(new Integer[matrixValues.size()]));

    }

    /**
     * Sets adjacency matrix for given array.
     * @param matrixValues Matrix values.
     * @throws WrongInputException
     */
    public void setAdjacencyMatrix(@NotNull Integer[] matrixValues) throws WrongInputException {

        if(matrixValues.length != this.numOfCities-1){
            throw new WrongInputException(String.format("Number of relations should be equal to %s", this.numOfCities-1));
        }
        List<List<Integer>> adjacencyMatrix = new ArrayList<List<Integer>>(numOfCities);

        for(int i=0; i<numOfCities; i++){
            adjacencyMatrix.add(new LinkedList<Integer>());
        }
        for(int cityNum=0; cityNum<matrixValues.length; cityNum++){
            Integer city1 = cityNum+1;
            Integer city2 = matrixValues[cityNum];
            adjacencyMatrix.get(city1).add(city2);
            adjacencyMatrix.get(city2).add(city1);
        }
        setAdjacencyMatrix(adjacencyMatrix);
    }

    /**
     * Sets adjacency matrix.
     * @param adjacencyMatrix Adjacency matrix.
     * @throws WrongInputException
     */
    public void setAdjacencyMatrix(@NotNull List<List<Integer>> adjacencyMatrix) throws WrongInputException {
        if(adjacencyMatrix.size() != this.numOfCities){
            throw new WrongInputException("Wrong number of cities! First dimension length of list should be (numOfCities-1)");
        }
        this.adjacencyMatrix = adjacencyMatrix;
    }

    /**
     * Calculates minimum number of steps needed for achievement of whole unified state.
     * @return Step count
     */
    public Integer calculateMinUnionCount() throws LogicViolationException, NotUnifiableException {
        if(this.adjacencyMatrix==null){
            throw new NullPointerException("Adjacency matrix is not set yet");
        }
        UnionGraph graph = new UnionGraph(this.adjacencyMatrix);

        return graph.unifyAndReturnStepNumber();
    }

    /**
     * Gets adjacency matrix
     * @return Adjacency matrix.
     */
    public List<List<Integer>> getAdjacencyMatrix(){
        return this.adjacencyMatrix;
    }


}
