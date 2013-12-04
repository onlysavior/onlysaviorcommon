package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.ReflectionManager;
import onlysaviorcommon.reflect.XClass;
import onlysaviorcommon.reflect.XMethod;
import onlysaviorcommon.reflect.XProperty;
import onlysaviorcommon.reflect.java.generics.TypeEnvironment;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午8:48
 * To change this template use File | Settings | File Templates.
 */
public abstract class JavaReflectionManager implements ReflectionManager {

    public TypeEnvironment toApproximatingEnvironment(TypeEnvironment context) {
        return null;
    }

    public XClass toXClass(Type type, TypeEnvironment context) {
        return null;
    }

    TypeEnvironment getTypeEnvironment(Type t) {
        return null;
    }

    XMethod getXMethod(Method method, TypeEnvironment typeEnvironment) {
        return null;
    }

    XProperty getXProperty(Member field, TypeEnvironment typeEnvironment) {
        return null;
    }

    JavaXType toXType(Type type, TypeEnvironment typeEnvironment) {
        return null;
    }
}
