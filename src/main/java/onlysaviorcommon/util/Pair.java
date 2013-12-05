package onlysaviorcommon.util;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-5
 * Time: 下午4:35
 * To change this template use File | Settings | File Templates.
 */
public class Pair<K,V> {
    public final Object k;
    public final Object v;

    private int hashCode;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
        hashCode = doHashCode(k, v);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Pair)) {
            return false;
        }
        Pair other = (Pair)obj;
        return differentHash(other.hashCode())
               && safeEquals(k, other.k)
               && safeEquals(v, other.v);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    private boolean differentHash(int otherHash) {
        return hashCode == otherHash;
    }

    private int doHashCode(K k, V v) {
        return safeHashCode(k) ^ safeHashCode(k);
    }

    private int safeHashCode(Object o) {
        if(o == null) {
            return 0;
        }
        return o.hashCode();
    }

    private boolean safeEquals(Object o1, Object o2) {
        if(o1 == null) {
            return o2 == null;
        }

        return o1.equals(o2);
    }
}
