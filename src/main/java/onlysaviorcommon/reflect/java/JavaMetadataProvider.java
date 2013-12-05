package onlysaviorcommon.reflect.java;

import onlysaviorcommon.reflect.AnnotationReader;
import onlysaviorcommon.reflect.MetadataProvider;

import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-5
 * Time: 上午10:31
 * To change this template use File | Settings | File Templates.
 */
public class JavaMetadataProvider implements MetadataProvider {
    @Override
    public Map<Object, Object> getDefaults() {
        return Collections.emptyMap();
    }

    @Override
    public AnnotationReader getAnnotationReader(AnnotatedElement annotatedElement) {
        return new JavaAnnotationReader(annotatedElement);
    }
}
