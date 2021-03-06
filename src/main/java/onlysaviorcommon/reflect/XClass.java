package onlysaviorcommon.reflect;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午7:01
 * To change this template use File | Settings | File Templates.
 */
public interface XClass extends XAnnotionedElement{
    public static final String ACCESS_PROPERTY = "property";
    public static final String ACCESS_FIELD = "field";

    static final Filter DEFAULT_FILTER = new Filter() {

        public boolean returnStatic() {
            return false;
        }

        public boolean returnTransient() {
            return false;
        }
    };

    String getName();

    /**
     * @see Class#getSuperclass()
     */
    XClass getSuperclass();

    /**
     * @see Class#getInterfaces()
     */
    XClass[] getInterfaces();

    /**
     * see Class#isInterface()
     */
    boolean isInterface();

    boolean isAbstract();

    boolean isPrimitive();

    boolean isEnum();

    boolean isAssignableFrom(XClass c);

    List<XProperty> getDeclaredProperties(String accessType);

    List<XProperty> getDeclaredProperties(String accessType, Filter filter);

    /**
     * Returns the <tt>Method</tt>s defined by this class.
     */
    List<XMethod> getDeclaredMethods();

}
