package com.imdb.imdbbackend.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Request utils class
 */
public final class RequestUtil {

	private static final int PAGE_DIFFERENCE = 1;
	public static Pageable getPageableFromRequest(com.imdb.imdbbackend.dto.request.Pageable pageable) {
		return getPageableFromRequest(pageable, true);
	}

	public static Pageable getPageableFromRequest(com.imdb.imdbbackend.dto.request.Pageable pageable, boolean needsSort) {
		if (needsSort) {
			return PageRequest.of(pageable.getPage() - PAGE_DIFFERENCE, pageable.getSize(), getSort(pageable.getSort()));
		} else {
			return PageRequest.of(pageable.getPage() - PAGE_DIFFERENCE, pageable.getSize());
		}
	}

	public static Sort getSort(com.imdb.imdbbackend.dto.request.Sort sort) {
		return sort.getDirection().getValue().equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sort.getBy()).ascending()
				: Sort.by(sort.getBy()).descending();
	}
}
