package onlysaviorcommon.reflect.java.generics;

import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-4
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class CompoundTypeEnvironment implements TypeEnvironment {
    private final TypeEnvironment f;
    private final TypeEnvironment g;
    private int hashCode;

    public static TypeEnvironment create(TypeEnvironment f,
                                                 TypeEnvironment g) {
        if(f == IdentityTypeEnvironment.getInstance()) {
            return f;
        }

        if(g == IdentityTypeEnvironment.getInstance()) {
            return g;
        }

        CompoundTypeEnvironment compoundTypeEnvironment = new CompoundTypeEnvironment(f,g);
        return compoundTypeEnvironment;
    }

    public CompoundTypeEnvironment(TypeEnvironment f, TypeEnvironment g) {
        this.f = f;
        this.g = g;
        hashCode = doHashCode(f,g);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof CompoundTypeEnvironment))
            return false;
        CompoundTypeEnvironment other = (CompoundTypeEnvironment)obj;
        if(diffrentHashCode(other))
            return false;
        if(!f.equals(other.f)) return false;
        return g.equals(other.g);
    }

    private boolean diffrentHashCode(CompoundTypeEnvironment o) {
        return o.hashCode == hashCode;
    }

    private int doHashCode(TypeEnvironment f, TypeEnvironment g) {
        int hash;
        hash = f.hashCode();
        hash = hash * 29 + g.hashCode();
        return hash;
    }

    @Override
    public Type bind(Type type) {
        return f.bind(g.bind(type));
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return f.toString() +
                "(" + g.toString()
                + ")";
    }
}
