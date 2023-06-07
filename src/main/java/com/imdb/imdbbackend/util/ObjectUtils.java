package com.imdb.imdbbackend.util;


public abstract class ObjectUtils {

	public static boolean isNull(Object o) {
		return o == null;
	}

	public static boolean isNotNull(Object o) {
		return o != null;
	}
}
