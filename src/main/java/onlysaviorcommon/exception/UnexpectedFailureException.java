package onlysaviorcommon.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-30
 * Time: 下午9:19
 * To change this template use File | Settings | File Templates.
 */
public class UnexpectedFailureException extends RuntimeException {
    public UnexpectedFailureException() {
    }

    public UnexpectedFailureException(String message) {
        super(message);
    }

    public UnexpectedFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedFailureException(Throwable cause) {
        super(cause);
    }
}
