package onlysaviorcommon.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-4
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */
public class ReflectionUtil {

    public static boolean isProperty(Method m, Type boundType, Filter filter) {
        return ReflectionUtil.isPropertyType( boundType )
                && !m.isSynthetic()
                && !m.isBridge()
                && ( filter.returnStatic() || !Modifier.isStatic(m.getModifiers()) )
                && m.getParameterTypes().length == 0
                && ( m.getName().startsWith( "get" ) || m.getName().startsWith( "is" ) );
    }

    public static boolean isProperty(Field f, Type boundType, Filter filter) {
        return ( filter.returnStatic() || ! Modifier.isStatic( f.getModifiers() ) )
                && ( filter.returnTransient() || ! Modifier.isTransient( f.getModifiers() ) )
                && !f.isSynthetic()
                && ReflectionUtil.isPropertyType( boundType );
    }

    private static boolean isPropertyType(Type type) {
        return !TypeUtils.isVoid( type );
    }
}
