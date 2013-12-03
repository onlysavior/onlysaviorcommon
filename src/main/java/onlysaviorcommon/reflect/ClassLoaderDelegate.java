package onlysaviorcommon.reflect;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午8:20
 * To change this template use File | Settings | File Templates.
 */
public interface ClassLoaderDelegate {
    /**
     * Locate a class by name.
     *
     * @param className The name of the class to locate
     * @param <T> The returned class type.
     *
     * @return The class reference
     */
    public <T> Class<T> classForName(String className) throws ClassLoadingException;
}
