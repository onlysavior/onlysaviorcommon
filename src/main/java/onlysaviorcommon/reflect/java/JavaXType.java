package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.TypeUtils;
import onlysaviorcommon.reflect.XClass;
import onlysaviorcommon.reflect.java.generics.TypeEnvironment;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-4
 * Time: 上午10:18
 * To change this template use File | Settings | File Templates.
 */
public abstract class JavaXType {
    private JavaReflectionManager factory;
    private TypeEnvironment typeEnvironment;
    private Type boundType;
    private Type approximatedType;

    public JavaXType(Type unboundType, JavaReflectionManager factory, TypeEnvironment context) {
        this.factory = factory;
        this.typeEnvironment = context;
        this.boundType = context.bind(unboundType);
        this.approximatedType = factory.toApproximatingEnvironment(context).bind(unboundType);
    }

    abstract public boolean isArray();

    abstract public boolean isCollection();

    abstract public XClass getElementClass();

    abstract public XClass getClassOrElementClass();

    abstract public Class<? extends Collection> getCollectionClass();

    abstract public XClass getMapKey();

    abstract public XClass getType();

    public boolean isResolved() {
        return TypeUtils.isResolved(boundType);
    }

    protected Type approximate() {
        return approximatedType;
    }

    protected XClass toXClass(Type type) {
        return factory.toXClass( type, typeEnvironment );
    }
}
