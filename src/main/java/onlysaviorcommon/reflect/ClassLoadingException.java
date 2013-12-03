package onlysaviorcommon.reflect;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午8:21
 * To change this template use File | Settings | File Templates.
 */
public class ClassLoadingException extends RuntimeException {
    public ClassLoadingException(String message) {
        super(message);
    }

    public ClassLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
