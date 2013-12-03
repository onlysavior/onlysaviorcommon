package onlysaviorcommon.reflect;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-3
 * Time: 下午8:23
 * To change this template use File | Settings | File Templates.
 */
public interface MetadataProviderInjector {
    MetadataProvider getMetadataProvider();

    /**
     * Defines the metadata provider for a given Reflection Manager
     */
    void setMetadataProvider(MetadataProvider metadataProvider);
}
