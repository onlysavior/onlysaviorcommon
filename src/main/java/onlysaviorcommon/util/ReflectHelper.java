package onlysaviorcommon.util;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-6
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
public class ReflectHelper {
    public static Class classForName(String className, Class caller) {
        ClassLoader tccl = Thread.currentThread().getContextClassLoader();
        Class rtn = null;
        try {
            rtn =  Class.forName(className, true, tccl);
        } catch (ClassNotFoundException e) {
        }

        if(rtn == null) {
            ClassLoader callerClassLoader = caller.getClassLoader();
            try {
                rtn = Class.forName(className, true, callerClassLoader);
            } catch (ClassNotFoundException e) {
            }
        }

        if(rtn == null) {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            try {
                rtn = Class.forName(className, true, systemClassLoader);
            } catch (ClassNotFoundException e) {
            }
        }

        return rtn;
    }
}
