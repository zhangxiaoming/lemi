package com.sample.libs.image.cache.disc.impl;

import java.io.File;

import com.sample.libs.image.cache.disc.BaseDiscCache;
import com.sample.libs.image.cache.disc.DiscCacheAware;
import com.sample.libs.image.cache.disc.naming.FileNameGenerator;
import com.sample.libs.image.core.DefaultConfigurationFactory;

/**
 * Default implementation of {@linkplain DiscCacheAware disc cache}. Cache size is unlimited.
 * 
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 * @see BaseDiscCache
 */
public class UnlimitedDiscCache extends BaseDiscCache {
	/**
	 * @param cacheDir
	 *            Directory for file caching
	 */
	public UnlimitedDiscCache(File cacheDir) {
		this(cacheDir, DefaultConfigurationFactory.createFileNameGenerator());
	}

	/**
	 * @param cacheDir
	 *            Directory for file caching
	 * @param fileNameGenerator
	 *            Name generator for cached files
	 */
	public UnlimitedDiscCache(File cacheDir, FileNameGenerator fileNameGenerator) {
		super(cacheDir, fileNameGenerator);
	}

	@Override
	public void put(String key, File file) {
		// Do nothing
	}
}
