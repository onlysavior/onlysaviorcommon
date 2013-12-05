package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.TypeUtils;
import onlysaviorcommon.reflect.XClass;
import onlysaviorcommon.reflect.java.generics.TypeEnvironment;
import onlysaviorcommon.reflect.java.generics.TypeSwitch;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-5
 * Time: 下午12:05
 * To change this template use File | Settings | File Templates.
 */
public class JavaCollectionType extends JavaXType {

    public JavaCollectionType(Type unboundType, JavaReflectionManager factory, TypeEnvironment context) {
        super(unboundType, factory, context);
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isCollection() {
        return true;
    }

    @Override
    public XClass getElementClass() {
        return new TypeSwitch<XClass>() {
            @Override
            public XClass caseParameterizedType(ParameterizedType parameterizedType) {
                Type[] args = parameterizedType.getActualTypeArguments();
                Type componentType;
                Class<? extends Collection> collectionClass = getCollectionClass();
                if ( Map.class.isAssignableFrom( collectionClass )
                        || SortedMap.class.isAssignableFrom( collectionClass ) ) {
                    componentType = args[1];
                }
                else {
                    componentType = args[0];
                }
                return toXClass(componentType);
            }
        }.doSwitch(approximate());
    }

    @Override
    public XClass getClassOrElementClass() {
        return toXClass( approximate() );
    }

    @Override
    public Class<? extends Collection> getCollectionClass() {
        return TypeUtils.getCollectionClass(approximate());
    }

    @Override
    public XClass getMapKey() {
        return new TypeSwitch<XClass>() {
            @Override
            public XClass caseParameterizedType(ParameterizedType parameterizedType) {
                if ( Map.class.isAssignableFrom( getCollectionClass() ) ) {
                    return toXClass( parameterizedType.getActualTypeArguments()[0] );
                }
                return null;
            }
        }.doSwitch( approximate() );
    }

    @Override
    public XClass getType() {
        return toXClass(approximate());
    }
}
