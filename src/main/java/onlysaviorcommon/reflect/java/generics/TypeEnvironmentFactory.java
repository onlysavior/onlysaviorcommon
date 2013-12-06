package onlysaviorcommon.reflect.java.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-6
 * Time: 下午2:15
 * To change this template use File | Settings | File Templates.
 */
public class TypeEnvironmentFactory {

    /**
     * @return Returns a type environment suitable for resolving types occurring
     *         in subclasses of the context class.
     */
    public TypeEnvironment getEnvironment(Class context) {
        if ( context == null ) {
            return IdentityTypeEnvironment.getInstance();
        }
        return createEnvironment( context );
    }

    public TypeEnvironment getEnvironment(Type context) {
        if ( context == null ) {
            return IdentityTypeEnvironment.getInstance();
        }
        return createEnvironment( context );
    }

    public TypeEnvironment getEnvironment(Type t, TypeEnvironment context) {
        return CompoundTypeEnvironment.create( getEnvironment(t), context );
    }

    public TypeEnvironment toApproximatingEnvironment(TypeEnvironment context) {
        return CompoundTypeEnvironment.create( new ApproximatingTypeEnvironment(), context );
    }

    private TypeEnvironment createEnvironment(Type context) {
        return new TypeSwitch<TypeEnvironment>() {
            @Override
            public TypeEnvironment caseClass(Class classType) {
                return CompoundTypeEnvironment.create(
                        createSuperTypeEnvironment( classType ),
                        getEnvironment( classType.getSuperclass() )
                );
            }

            @Override
            public TypeEnvironment caseParameterizedType(ParameterizedType parameterizedType) {
                return createEnvironment( parameterizedType );
            }

            @Override
            public TypeEnvironment defaultCase(Type t) {
                throw new IllegalArgumentException( "Invalid type for generating environment: " + t );
            }
        }.doSwitch( context );
    }

    private TypeEnvironment createSuperTypeEnvironment(Class clazz) {
        Class superclass = clazz.getSuperclass();
        if ( superclass == null ) {
            return IdentityTypeEnvironment.getInstance();
        }

        Type[] formalArgs = superclass.getTypeParameters();
        Type genericSuperclass = clazz.getGenericSuperclass();

        if ( genericSuperclass instanceof Class ) {
            return IdentityTypeEnvironment.getInstance();
        }

        if ( genericSuperclass instanceof ParameterizedType) {
            Type[] actualArgs = ( (ParameterizedType) genericSuperclass ).getActualTypeArguments();
            return new SimpleTypeEnvironment( formalArgs, actualArgs );
        }

        throw new AssertionError( "Should be unreachable" );
    }

    private TypeEnvironment createEnvironment(ParameterizedType t) {
        Type[] tactuals = t.getActualTypeArguments();
        Type rawType = t.getRawType();
        if ( rawType instanceof Class ) {
            TypeVariable[] tparms = ( (Class) rawType ).getTypeParameters();
            return new SimpleTypeEnvironment( tparms, tactuals );
        }
        return IdentityTypeEnvironment.getInstance();
    }
}