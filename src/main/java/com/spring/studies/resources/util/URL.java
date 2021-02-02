package com.spring.studies.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

public class URL {
	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			return StringUtils.EMPTY;
		}
	}
}
