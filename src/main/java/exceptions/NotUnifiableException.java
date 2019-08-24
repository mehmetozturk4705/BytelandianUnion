package exceptions;

/**
 * If thrower UnionGraph object has multiple components disconnected each other, you will get this exception.
 */
public class NotUnifiableException extends Exception{
    /**
     * Creates NotUnifiableException object which represents not unifiable graph state.
     * @param message Exception message.
     */
    public NotUnifiableException(String message){
        super(message);
    }
}
