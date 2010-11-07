
package com.github.jogjamerapi.log;

public interface Appender extends Appendable {

	public static String CONSOLE = "CONSOLE";
	public static String TEXT_FILE = "TEXT_FILE";
	public static String RICH_TEXT_FILE = "RICH_TEXT_FILE";
	public static String SCREEN = "SCREEN";
	public static String EVENT_LOG = "EVENT_LOG";

	public String getName();

	public String getType();

	public int getThreshold();

	public String getDestination();

}
