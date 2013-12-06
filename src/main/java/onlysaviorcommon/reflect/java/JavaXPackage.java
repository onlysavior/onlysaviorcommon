package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.XPackage;

import java.lang.reflect.AnnotatedElement;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-6
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */
public class JavaXPackage extends JavaXAnnotatedElement implements XPackage {
    private AnnotatedElement e;

    public JavaXPackage(JavaReflectionManager manager, AnnotatedElement e) {
        super(manager, e);
    }

    @Override
    public String getName() {
        return ((Package)e).getName();
    }
}
