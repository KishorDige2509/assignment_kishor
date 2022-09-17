package com.recipe.util;

public class LogUtil {
	
	private LogUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static String presentationLogger(String hanlderName) {
		return "Reached  url : " + hanlderName;
	}

	public static String startLog(String className) {
		final int CURRENT_METHOD = 2;
		return "Entering Class " + className + " into method "
				+ Thread.currentThread().getStackTrace()[CURRENT_METHOD].getMethodName();
	}

	public static String exitLog(String className) {
		final int CURRENT_METHOD = 2;
		return "Exiting Class " + className + " from method  "
				+ Thread.currentThread().getStackTrace()[CURRENT_METHOD].getMethodName();
	}
	
	public static String errorLog(Throwable e) {
		StackTraceElement error = e.getStackTrace()[0];
		return System.getProperty("line.separator") + e + " occured, Please check line number " + error.getLineNumber()
				+ " in file " + error.getClassName() + " inside " + error.getMethodName() + " method";
	}

}
