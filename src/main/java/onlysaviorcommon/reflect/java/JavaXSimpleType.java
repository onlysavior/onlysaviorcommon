package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.XClass;
import onlysaviorcommon.reflect.java.generics.TypeEnvironment;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-4
 * Time: 下午7:37
 * To change this template use File | Settings | File Templates.
 */
public class JavaXSimpleType extends JavaXType {
    public JavaXSimpleType(Type unboundType, JavaReflectionManager factory, TypeEnvironment context) {
        super(unboundType, factory, context);
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public XClass getElementClass() {
        return toXClass(approximate());
    }

    @Override
    public XClass getClassOrElementClass() {
        return getElementClass();
    }

    @Override
    public Class<? extends Collection> getCollectionClass() {
        return null;
    }

    @Override
    public XClass getMapKey() {
        return null;
    }

    @Override
    public XClass getType() {
        return toXClass(approximate());
    }


}
