package com.recipe.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.This;

@Slf4j
public class LanguageUtil {

	public static final String CLASSNAME = This.class.getSimpleName();

	public static String decodeValue(String value) {
		log.info(LogUtil.startLog(CLASSNAME));

		try {
			log.info(LogUtil.exitLog(CLASSNAME));
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (Exception ex) {
			throw new RuntimeException(ex.getCause());
		}

	}

}
