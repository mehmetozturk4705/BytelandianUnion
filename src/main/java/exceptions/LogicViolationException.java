package exceptions;

/**
 * Exception which represents logical compliance error.
 */
public class LogicViolationException extends Exception {
    /**
     * Creates LogicViolationException object which represents exception in logical compliance.
     * @param message Exception message.
     */
    public LogicViolationException(String message){
        super(message);
    }
}
