package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.*;
import onlysaviorcommon.reflect.java.generics.CompoundTypeEnvironment;
import onlysaviorcommon.reflect.java.generics.TypeEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-4
 * Time: 下午2:08
 * To change this template use File | Settings | File Templates.
 */
public class JavaXClass extends JavaXAnnotatedElement implements XClass {
    private TypeEnvironment context;
    private Class clazz;

    public JavaXClass(JavaReflectionManager manager, Class c) {
        super(manager, c);
        this.clazz = c;
    }

    @Override
    public String getName() {
        return toClass().getName();
    }

    @Override
    public XClass getSuperclass() {
        return getJavaReflectionManager()
                .toXClass(toClass().getSuperclass(), CompoundTypeEnvironment.create(context, getJavaReflectionManager().getTypeEnvironment(toClass())));
    }

    @Override
    public XClass[] getInterfaces() {
        Class[] clses = toClass().getInterfaces();
        int length = clses.length;
        XClass[] rtn = new XClass[length];
        if(length > 0) {
            TypeEnvironment compoundTypeEnv =
                    CompoundTypeEnvironment.create(context, getJavaReflectionManager().getTypeEnvironment(toClass()));

            for(int index = 0; index < length; index++) {
                rtn[index] = getJavaReflectionManager().toXClass(clses[index], compoundTypeEnv);
            }
        }
        return rtn;
    }

    @Override
    public boolean isInterface() {
        return toClass().isInterface();
    }

    @Override
    public boolean isAbstract() {
        return Modifier.isAbstract(toClass().getModifiers());
    }

    @Override
    public boolean isPrimitive() {
        return toClass().isPrimitive();
    }

    @Override
    public boolean isEnum() {
        return toClass().isEnum();
    }

    @Override
    public boolean isAssignableFrom(XClass c) {
        return toClass().isAssignableFrom(((JavaXClass)c).toClass());
    }

    @Override
    public List<XProperty> getDeclaredProperties(String accessType) {
        return getDeclaredProperties(accessType, DEFAULT_FILTER);
    }

    @Override
    public List<XProperty> getDeclaredProperties(String accessType, Filter filter) {
        if ( accessType.equals( ACCESS_FIELD ) ) {
            return getDeclaredFieldProperties( filter );
        }
        if ( accessType.equals( ACCESS_PROPERTY ) ) {
            return getDeclaredMethodProperties( filter );
        }
        throw new IllegalArgumentException( "Unknown access type " + accessType );
    }

    private List<XProperty> getDeclaredMethodProperties(Filter filter) {
        List<XProperty> result = new LinkedList<XProperty>();
        for ( Method m : toClass().getDeclaredMethods() ) {
            if ( ReflectionUtil.isProperty( m, context.bind(m.getGenericReturnType()), filter ) ) {
                result.add( getJavaReflectionManager().getXProperty(m, getTypeEnvironment()) );
            }
        }
        return result;
    }

    private List<XProperty> getDeclaredFieldProperties(Filter filter) {
        List<XProperty> result = new LinkedList<XProperty>();
        for ( Field m : toClass().getDeclaredFields() ) {
            if ( ReflectionUtil.isProperty( m, context.bind(m.getGenericType()), filter ) ) {
                result.add( getJavaReflectionManager().getXProperty(m, getTypeEnvironment()) );
            }
        }
        return result;
    }

    @Override
    public List<XMethod> getDeclaredMethods() {
        List<XMethod> rtn = new LinkedList<XMethod>();
        Method[] methods = toClass().getDeclaredMethods();
        for(Method m : methods) {
            rtn.add(getJavaReflectionManager().getXMethod(m,context));
        }
        return rtn;
    }

    public Class<?> toClass() {
        return clazz;
    }

    public TypeEnvironment getTypeEnvironment() {
        return context;
    }
}
