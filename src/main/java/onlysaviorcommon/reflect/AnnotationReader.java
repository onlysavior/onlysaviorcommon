package onlysaviorcommon.reflect;

import java.lang.annotation.Annotation;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午8:17
 * To change this template use File | Settings | File Templates.
 */
public interface AnnotationReader {
    public <T extends Annotation> T getAnnotation(Class<T> annotationType);

    public <T extends Annotation> boolean isAnnotationPresent(Class<T> annotationType);

    public Annotation[] getAnnotations();
}
