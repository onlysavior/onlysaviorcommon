package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.XMethod;
import onlysaviorcommon.reflect.java.generics.TypeEnvironment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-4
 * Time: 下午5:37
 * To change this template use File | Settings | File Templates.
 */
public class JavaXMethod extends JavaXMember implements XMethod {
    private Member member;

    static JavaXMethod create(Member member, TypeEnvironment context, JavaReflectionManager factory) {
        final Type type = typeOf(member, context);
        JavaXType javaXType = factory.toXType(type, context);
        return new JavaXMethod(member,type,context, factory, javaXType);
    }

    public JavaXMethod(Member member, Type type, TypeEnvironment environment, JavaReflectionManager manager, JavaXType xType) {
        super(member, type, environment, manager, xType);
        this.member = member;
    }

    @Override
    public String getName() {
        return getMember().getName();
    }

    @Override
    public Object invoke(Object target, Object... parameters) {
        try {
            return ((Method)member).invoke(target,parameters);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Member getMember() {
        return member;
    }
}
