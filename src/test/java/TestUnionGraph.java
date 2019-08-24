import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestUnionGraph {
    BytelandUnionExperiment experiment;

    /**
     * Test for lines in assessment.
     * @throws Exception
     */
    @Test
    public void TestCalculation() throws Exception{
        //Line 1
        this.experiment = new BytelandUnionExperiment(4);
        this.experiment.setAdjacencyMatrix("0 1 2");
        Assertions.assertEquals((int)this.experiment.calculateMinUnionCount(), 2);
        //Line 2
        this.experiment = new BytelandUnionExperiment(8);
        this.experiment.setAdjacencyMatrix("0 1 2 0 0 3 3");
        Assertions.assertEquals((int)this.experiment.calculateMinUnionCount(), 4);
        //Line 3
        this.experiment = new BytelandUnionExperiment(9);
        this.experiment.setAdjacencyMatrix("0 1 1 1 1 0 2 2");
        Assertions.assertEquals((int)this.experiment.calculateMinUnionCount(), 5);
    }
}
