package onlysaviorcommon.reflect;

import java.lang.annotation.Annotation;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午5:35
 * To change this template use File | Settings | File Templates.
 */
public interface XAnnotionedElement {
    <T extends Annotation> T getAnnotation(Class<T> annotationType);

    <T extends Annotation> boolean isAnnotationPresent(Class<T> annotationType);

    Annotation[] getAnnotations();

    /**
     * Returns true if the underlying artefact
     * is the same
     */
    boolean equals(Object x);
}
