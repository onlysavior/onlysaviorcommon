package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.AnnotationReader;
import onlysaviorcommon.reflect.XAnnotionedElement;
import static onlysaviorcommon.util.Assert.*;

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

    protected JavaReflectionManager getJavaReflectionManager() {
        return javaReflectionManager;
    }

    private AnnotationReader buildAnnotationReader() {
        return javaReflectionManager.buildAnnotationReader(element);
    }

    public JavaXAnnotatedElement(JavaReflectionManager manager, AnnotatedElement e) {
        this.javaReflectionManager = assertNotNull(manager);
        this.element = assertNotNull(e);
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return buildAnnotationReader().getAnnotation(annotationType);
    }

    @Override
    public <T extends Annotation> boolean isAnnotationPresent(Class<T> annotationType) {
        return buildAnnotationReader().isAnnotationPresent(annotationType);
    }

    @Override
    public Annotation[] getAnnotations() {
        return buildAnnotationReader().getAnnotations();
    }

    public AnnotatedElement getElement() {
        return element;
    }
}
