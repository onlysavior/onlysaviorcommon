package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.*;
import onlysaviorcommon.reflect.java.generics.TypeEnvironment;
import onlysaviorcommon.reflect.util.StandardClassLoaderDelegateImpl;
import onlysaviorcommon.util.Pair;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午8:48
 * To change this template use File | Settings | File Templates.
 */
public abstract class JavaReflectionManager implements ReflectionManager {
    private MetadataProvider metadataProvider;
    private ClassLoaderDelegate classLoaderDelegate = StandardClassLoaderDelegateImpl.INSTANCE;

    public MetadataProvider getMetadataProvider() {
        if (metadataProvider == null) {
            setMetadataProvider(new JavaMetadataProvider());
        }
        return metadataProvider;
    }

    @Override
    public void injectClassLoaderDelegate(ClassLoaderDelegate delegate) {
        if(delegate == null) {
            classLoaderDelegate = StandardClassLoaderDelegateImpl.INSTANCE;
        } else {
            classLoaderDelegate = delegate;
        }
    }

    @Override
    public ClassLoaderDelegate getClassLoaderDelegate() {
        return classLoaderDelegate;
    }

    private static class TypeKey extends Pair<Type, TypeEnvironment> {
        TypeKey(Type t, TypeEnvironment context) {
            super( t, context );
        }
    }

    private static class MemberKey extends Pair<Member, TypeEnvironment> {
        MemberKey(Member member, TypeEnvironment context) {
            super( member, context );
        }
    }



    public void setMetadataProvider(MetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
    }

    public TypeEnvironment toApproximatingEnvironment(TypeEnvironment context) {
        return null;
    }

    public XClass toXClass(Type type, TypeEnvironment context) {
        return null;
    }

    TypeEnvironment getTypeEnvironment(Type t) {
        return null;
    }

    XMethod getXMethod(Method method, TypeEnvironment typeEnvironment) {
        return null;
    }

    XProperty getXProperty(Member field, TypeEnvironment typeEnvironment) {
        return null;
    }

    JavaXType toXType(Type type, TypeEnvironment typeEnvironment) {
        return null;
    }
}
