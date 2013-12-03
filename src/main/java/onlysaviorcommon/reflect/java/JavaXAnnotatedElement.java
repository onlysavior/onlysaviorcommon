package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.XAnnotionedElement;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午8:45
 * To change this template use File | Settings | File Templates.
 */
public class JavaXAnnotatedElement implements XAnnotionedElement {
    private JavaReflectionManager javaReflectionManager;
    private AnnotatedElement element;

    public JavaXAnnotatedElement(JavaReflectionManager manager, AnnotatedElement e) {
        this.javaReflectionManager = manager;
        this.element = e;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return null;
    }

    @Override
    public <T extends Annotation> boolean isAnnotationPresent(Class<T> annotationType) {
        return false;
    }

    @Override
    public Annotation[] getAnnotations() {
        return new Annotation[0];
    }
}
