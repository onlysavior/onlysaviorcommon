package onlysaviorcommon.reflect;

import onlysaviorcommon.reflect.java.generics.TypeSwitch;

import java.lang.reflect.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-4
 * Time: 上午10:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class TypeUtils {
    public static boolean isResolved(Type type) {
        return new TypeSwitch<Boolean>() {
            @Override
            public Boolean caseWildcardType(WildcardType wildcardType) {
                return areResolved(wildcardType.getLowerBounds())
                        && areResolved(wildcardType.getUpperBounds());
            }

            @Override
            public Boolean caseTypeVariable(TypeVariable typeVariable) {
                return false;
            }

            @Override
            public Boolean caseClass(Class classType) {
                return true;
            }

            @Override
            public Boolean caseGenericArrayType(GenericArrayType genericArrayType) {
                return isResolved(genericArrayType.getGenericComponentType());
            }

            @Override
            public Boolean caseParameterizedType(ParameterizedType parameterizedType) {
                Type[] types = parameterizedType.getActualTypeArguments();
                return areResolved(types) && isResolved(parameterizedType.getRawType());
            }
        }.doSwitch(type);
    }

    public static boolean areResolved(Type[] types) {
        for(Type t : types) {
            if(!isResolved(t)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isVoid(Type type) {
        return void.class.equals( type );
    }

    public static Class<? extends Collection>  getCollectionClass(Type type) {
        return new TypeSwitch<Class<? extends Collection>>() {
            @Override
            public Class<? extends Collection> caseWildcardType(WildcardType wildcardType) {
                Type[] upbouds = wildcardType.getUpperBounds();
                if(upbouds.length == 0) {
                    return null;
                }

                return getCollectionClass(upbouds[0]);
            }

            @Override
            public Class<? extends Collection> caseClass(Class classType) {
                return isCollectionClass(classType) ? (Class<? extends Collection>)classType : null;
            }

            @Override
            public Class<? extends Collection> caseParameterizedType(ParameterizedType parameterizedType) {
                return getCollectionClass(parameterizedType.getRawType());
            }
        }.doSwitch(type);
    }

    public  static boolean isCollectionClass(Class<?> clazz) {
        return clazz == Collection.class
                || clazz == java.util.List.class
                || clazz == java.util.Set.class
                || clazz == java.util.Map.class
                || clazz == java.util.SortedSet.class // extension to the specs
                || clazz == java.util.SortedMap.class; // extension to the specs
    }

    public static boolean isCollection(ParameterizedType parameterizedType) {
       return getCollectionClass(parameterizedType) != null;
    }

}
