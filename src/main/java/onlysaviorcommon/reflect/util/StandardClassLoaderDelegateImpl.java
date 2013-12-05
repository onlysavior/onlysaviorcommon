package onlysaviorcommon.reflect.util;

import onlysaviorcommon.reflect.ClassLoaderDelegate;
import onlysaviorcommon.reflect.ClassLoadingException;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-5
 * Time: 下午1:23
 * To change this template use File | Settings | File Templates.
 */
public class StandardClassLoaderDelegateImpl implements ClassLoaderDelegate {
    public static final StandardClassLoaderDelegateImpl INSTANCE = new StandardClassLoaderDelegateImpl();

    private StandardClassLoaderDelegateImpl(){}

    @Override
    public <T> Class<T> classForName(String className) throws ClassLoadingException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try {
            if(cl != null) {
                return (Class<T>)Class.forName(className, true, cl);
            }
        } catch (ClassNotFoundException ingoreOrLog) {
        }

        try {
            return (Class<T>)Class.forName(className);
        } catch (ClassNotFoundException e1) {
            throw new RuntimeException(e1);
        }
    }
}
