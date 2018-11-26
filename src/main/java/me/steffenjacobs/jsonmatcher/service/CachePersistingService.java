package me.steffenjacobs.jsonmatcher.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.collections.map.LRUMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @author Steffen Jacobs */
public class CachePersistingService {

	private static final Logger LOG = LoggerFactory.getLogger(CachePersistingService.class);
	private static final String DISK_CACHE_LOCATION = "C:/tmp/cache/";
	private static final String SPLIT_CHAR = "=";

	public void persistCache(LRUMap cache, String file) {

		StringBuilder sb = new StringBuilder();
		@SuppressWarnings("unchecked")
		Set<?> passiveCopyOfKeyset = new HashSet<>(cache.keySet());
		for (Object e : passiveCopyOfKeyset) {
			sb.append(e);
			sb.append(SPLIT_CHAR);
			sb.append(cache.get(e));
			sb.append("\n");
		}

		createDiskCacheLocationIfNecessary();

		try (PrintWriter out = new PrintWriter(DISK_CACHE_LOCATION + file)) {
			out.println(sb.toString());
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private void createDiskCacheLocationIfNecessary() {
		File cacheLocation = new File(DISK_CACHE_LOCATION);
		if (!cacheLocation.exists()) {
			cacheLocation.mkdirs();
		}
	}

	public void load(LRUMap cache, String file, Function<String, Object> keyTransformation, Function<String, Object> valueTransformation) {
		try {
			createDiskCacheLocationIfNecessary();
			File f = new File(DISK_CACHE_LOCATION + file);
			if (!f.exists()) {
				LOG.info("No cache file found for cache {}. It will be created automatically.", file);
				return;
			}
			List<String> lines = Files.readAllLines(Paths.get(DISK_CACHE_LOCATION, file));
			for (String line : lines) {
				if (!line.isEmpty()) {
					String[] val = line.split(SPLIT_CHAR);
					cache.put(keyTransformation.apply(val[0]), valueTransformation.apply(val[1]));
				}
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
