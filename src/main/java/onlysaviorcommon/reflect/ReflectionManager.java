package onlysaviorcommon.reflect;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午8:32
 * To change this template use File | Settings | File Templates.
 */
public interface ReflectionManager {
    /**
     * Allows injection of a ClassLoaderDelegate into the ReflectionManager
     *
     * @param delegate The ClassLoaderDelegate to use
     */
    public void injectClassLoaderDelegate(ClassLoaderDelegate delegate);

    /**
     * Access to the ClassLoaderDelegate currently associated with this ReflectionManager
     *
     * @return The current ClassLoaderDelegate
     */
    public ClassLoaderDelegate getClassLoaderDelegate();

    public <T> XClass toXClass(Class<T> clazz);

    public Class toClass(XClass xClazz);

    public Method toMethod(XMethod method);

    public XClass classForName(String name) throws ClassLoadingException;

    public XPackage packageForName(String packageName) throws ClassNotFoundException;

    public <T> boolean equals(XClass class1, Class<T> class2);

    public AnnotationReader buildAnnotationReader(AnnotatedElement annotatedElement);

    public Map getDefaults();
}
