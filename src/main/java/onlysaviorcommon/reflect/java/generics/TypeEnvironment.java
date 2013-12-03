package onlysaviorcommon.reflect.java.generics;

import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午8:38
 * To change this template use File | Settings | File Templates.
 */
public interface TypeEnvironment {
    public Type bind(Type type);
}
