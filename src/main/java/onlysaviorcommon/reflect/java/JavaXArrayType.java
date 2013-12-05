package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.XClass;
import onlysaviorcommon.reflect.java.generics.TypeEnvironment;
import onlysaviorcommon.reflect.java.generics.TypeSwitch;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-5
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public class JavaXArrayType extends JavaXType {

    public JavaXArrayType(Type unboundType, JavaReflectionManager factory, TypeEnvironment context) {
        super(unboundType, factory, context);
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public XClass getElementClass() {
        return toXClass(getElementType());
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
        Type boundType = getElementType();
        if ( boundType instanceof Class) {
            boundType = arrayTypeOf((Class)boundType);
        }
        return toXClass(boundType);
    }

    private Type getElementType() {
        return new TypeSwitch<Type>(){
            @Override
            public Type caseClass(Class classType) {
                return classType.getComponentType();
            }

            @Override
            public Type caseGenericArrayType(GenericArrayType genericArrayType) {
                return genericArrayType.getGenericComponentType();
            }

            @Override
            public Type defaultCase(Type t) {
                throw new IllegalArgumentException( t + " is not an array type");
            }
        }.doSwitch(approximate());
    }



    private Class<? extends Object> arrayTypeOf(Class componentType) {
        return Array.newInstance(componentType, 0).getClass();
    }
}
