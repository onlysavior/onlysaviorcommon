package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.AnnotationReader;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-5
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
public class JavaAnnotationReader implements AnnotationReader {
    private AnnotatedElement element;

    public JavaAnnotationReader(AnnotatedElement element) {
        this.element = element;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return element.getAnnotation(annotationType);
    }

    @Override
    public <T extends Annotation> boolean isAnnotationPresent(Class<T> annotationType) {
        return element.isAnnotationPresent(annotationType);
    }

    @Override
    public Annotation[] getAnnotations() {
        return element.getAnnotations();
    }
}
