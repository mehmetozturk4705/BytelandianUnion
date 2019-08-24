package exceptions;

/**
 * Exception which represents wrong input.
 */
public class WrongInputException extends Exception {

    /**
     * Creates WrongInputException object which represents wrong input.
     */
    public WrongInputException(){

    }
    public WrongInputException(String message){
        super(message);
    }
}
