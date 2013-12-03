package onlysaviorcommon.reflect;

import java.lang.reflect.AnnotatedElement;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午8:22
 * To change this template use File | Settings | File Templates.
 */
public interface MetadataProvider {
    /**
     * provide default metadata
     */
    Map<Object, Object> getDefaults();

    /**
     * provide metadata for a given annotated element
     */
    AnnotationReader getAnnotationReader(AnnotatedElement annotatedElement);
}
