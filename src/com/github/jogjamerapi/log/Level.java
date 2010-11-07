
package com.github.jogjamerapi.log;

public class Level {

	public static final int MIN_VALUE = 0;
	public static final int MAX_VALUE = 4;

	public static final int DEBUG = 0;
	public static final int INFO = 1;
	public static final int WARN = 2;
	public static final int ERROR = 3;
	public static final int FATAL = 4;

	public static boolean isGreaterOrEqual(int level1, int level2) {
		return level1 >= level2;
	}

	public static boolean isGreater(int level1, int level2) {
		return level1 > level2;
	}

}
