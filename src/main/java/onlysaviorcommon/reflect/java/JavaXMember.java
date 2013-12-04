package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.XClass;
import onlysaviorcommon.reflect.XMember;
import onlysaviorcommon.reflect.java.generics.TypeEnvironment;

import java.lang.reflect.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-4
 * Time: 上午11:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class JavaXMember extends JavaXAnnotatedElement implements XMember {
    private Type type;
    private TypeEnvironment typeEnvironment;
    private JavaXType xType;

    protected static Type typeOf(Member member, TypeEnvironment env) {
        if ( member instanceof Field) {
            return env.bind( ( (Field) member ).getGenericType() );
        }
        if ( member instanceof Method ) {
            return env.bind( ( (Method) member ).getGenericReturnType() );
        }
        throw new IllegalArgumentException( "Member " + member + " is neither a field nor a method" );
    }

    public JavaXMember(Member member,
                       Type type,
                       TypeEnvironment environment,
                       JavaReflectionManager manager,
                       JavaXType xType) {
        super(manager, (AnnotatedElement)member);
        this.type = type;
        this.typeEnvironment = environment;
        this.xType = xType;
    }

    @Override
    public XClass getDeclaringClass() {
        return getJavaReflectionManager()
                .toXClass(getMember().getDeclaringClass());
    }

    public abstract String getName();

    @Override
    public boolean isCollection() {
       return xType.isCollection();
    }

    @Override
    public boolean isArray() {
        return xType.isArray();
    }

    @Override
    public Class<? extends Collection> getCollectionClass() {
        return xType.getCollectionClass();
    }

    @Override
    public XClass getType() {
        return xType.getType();
    }

    public Type getJavaType() {
        return typeEnvironment.bind(type);
    }

    @Override
    public XClass getElementClass() {
        return xType.getElementClass();
    }

    @Override
    public XClass getClassOrElementClass() {
        return xType.getClassOrElementClass();
    }

    @Override
    public XClass getMapKey() {
        return xType.getMapKey();
    }

    @Override
    public int getModifiers() {
        return getMember().getModifiers();
    }

    @Override
    public void setAccessible(boolean accessible) {
        ((AccessibleObject)getMember()).setAccessible(accessible);
    }

    @Override
    public boolean isTypeResolved() {
        return xType.isResolved();
    }

    public Member getMember() {
        return (Member)getElement();
    }
}
