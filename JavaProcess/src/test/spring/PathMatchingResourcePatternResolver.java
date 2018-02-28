package test.spring;

import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

public class PathMatchingResourcePatternResolver {

	private final ResourceLoader resourceLoader;
	
	/**
	 * Create a new PathMatchingResourcePatternResolver.
	 * <p>ClassLoader access will happen via the thread context class loader.
	 * @param resourceLoader the ResourceLoader to load root directories and
	 * actual resources with
	 */
	public PathMatchingResourcePatternResolver(ResourceLoader resourceLoader) {
		Assert.notNull(resourceLoader, "ResourceLoader must not be null");
		this.resourceLoader = resourceLoader;
	}
}
