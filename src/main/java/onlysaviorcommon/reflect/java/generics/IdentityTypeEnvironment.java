package onlysaviorcommon.reflect.java.generics;

import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-4
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public class IdentityTypeEnvironment implements TypeEnvironment {
    private static final IdentityTypeEnvironment INSTANCE = new IdentityTypeEnvironment();

    private IdentityTypeEnvironment(){}

    @Override
    public Type bind(Type type) {
        return type;
    }

    public static IdentityTypeEnvironment getInstance() {
        return INSTANCE;
    }
}
