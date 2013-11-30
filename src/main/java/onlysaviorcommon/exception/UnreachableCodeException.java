package onlysaviorcommon.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-30
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */
public class UnreachableCodeException extends RuntimeException{
    public UnreachableCodeException() {
        super();
    }

    public UnreachableCodeException(String message) {
        super(message);
    }

    public UnreachableCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnreachableCodeException(Throwable cause) {
        super(cause);
    }
}
