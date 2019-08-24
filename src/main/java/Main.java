import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Solution of Byteland Union problem.
 * @author Mehmet Öztürk
 */
public class Main {
    /**
     * Maximum experiment number.
     */
    private static Integer EXPERIMENT_LIMIT = 1000;

    /**
     * Gets integer value from specified BufferedReader object.
     * @param reader Stream object.
     * @return Parsed integer value.
     * @throws IOException
     */
    private static Integer getInteger(BufferedReader reader) throws IOException{
        Integer result=null;
        while(result==null) {
            try {
                result = Integer.parseInt(reader.readLine().trim());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("You should feed integer for this input.");
            }
        }
        return result;
    }

    /**
     * Gets a line of string from specified BufferedReader object
     * @param reader
     * @return Line of string
     * @throws IOException
     */
    private static String getString(BufferedReader reader) throws IOException{
        String result=null;
        while(result==null) {
                result = reader.readLine();
        }
        return result.trim();
    }

    public static void main(String[] args) throws Exception{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //Getting number of experiment
        int numOfExperiment=0;
        try {
            numOfExperiment=getInteger(reader);
        }
        catch (NumberFormatException e){
            System.err.println(e.getMessage());
        }

        //Checking if experiment number is less than 1000
        if(numOfExperiment>=EXPERIMENT_LIMIT){
            System.err.println(String.format("BytelandUnionExperiment number should be less than %s!", EXPERIMENT_LIMIT) );
            System.exit(1);
        }
        //Getting experiment values
        int num = 0;
        while(num<numOfExperiment){
            //Getting number of cities
            int numOfCities=getInteger(reader);
            try{
                BytelandUnionExperiment bytelandUnionExperiment = new BytelandUnionExperiment(numOfCities);
                bytelandUnionExperiment.setAdjacencyMatrix(getString(reader).trim());
                System.out.println(bytelandUnionExperiment.calculateMinUnionCount());//Output of minimum step for unification
            }
            catch (Exception e){
                System.err.println(e.getMessage());
                continue;
            }

            num++;

        }
    }

}
