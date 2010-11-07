
package com.github.jogjamerapi.log;

import java.util.Enumeration;
import java.util.Hashtable;

import com.github.jogjamerapi.util.properties.Properties;
import com.github.jogjamerapi.util.string.StringUtils;






public class AppenderFactory {

	private static final String DEFAULT_TEXT_LOG_FILENAME = "file:///SDCard/log.txt";
	private static final String DEFAULT_RICHTEXT_LOG_FILENAME = "file:///SDCard/log.html";
	private static final String DEFAULT_PROPERTIES_FILENAME = "log4b.properties";
	private static final String APPENDER_PREFFIX = "log4b.appender.";

	private static Hashtable appenders = new Hashtable();
	private static Appender GOD_APPENDER = createAppender("GOD", Appender.CONSOLE, "DEBUG", null);

	private static String propFile;

	public static void load(String pPropFile) {
		if ((pPropFile == null) || pPropFile.trim().equals("")) {
			propFile = DEFAULT_PROPERTIES_FILENAME;
		} else {
			propFile = pPropFile;
		}
		loadAppenders(propFile);
	}

	private static void loadAppenders(String pPropFile) {
		try {
			Properties prop = Properties.loadProperties("/" + pPropFile);
			Enumeration enumeration = prop.getEnumeratedNames();

			while (enumeration.hasMoreElements()) {
				String key = null;
				String value = null;
				String[] values = null;
				String appenderName = null;
				String appenderType = null;
				String appenderThreshold = null;
				String appenderDest = null;

				key = ((String) enumeration.nextElement());

				if ((key != null) && !key.equals("") && key.startsWith(APPENDER_PREFFIX)) {
					key = key.trim();
					appenderName = key.substring(APPENDER_PREFFIX.length());
					value = prop.getProperty(key);
					if ((value != null) && !value.equals("")) {
						values = StringUtils.split(value, ',', 0);
						if ((values != null) && (values.length > 1)) {
							for (int i = 0; i < values.length; i++) {
								if (i == 0) {
									appenderType = values[i].trim();
								} else if (i == 1) {
									appenderThreshold = values[i].trim();
								} else if (i == 2) {
									appenderDest = values[i].trim();
								}
							}

							if (appenderType.equals(Appender.CONSOLE)) {
								appenders.put(appenderName, createAppender(appenderName, Appender.CONSOLE, appenderThreshold, appenderDest));

							} else if (appenderType.equals(Appender.TEXT_FILE)) {
								if ((appenderDest == null) || appenderDest.equals("")) {
									appenderDest = DEFAULT_TEXT_LOG_FILENAME;
								}
								appenders.put(appenderName, createAppender(appenderName, Appender.TEXT_FILE, appenderThreshold, appenderDest));

							} else if (appenderType.equals(Appender.RICH_TEXT_FILE)) {
								if ((appenderDest == null) || appenderDest.equals("")) {
									appenderDest = DEFAULT_RICHTEXT_LOG_FILENAME;
								}
								appenders.put(appenderName, createAppender(appenderName, Appender.RICH_TEXT_FILE, appenderThreshold, appenderDest));

							} else if (appenderType.equals(Appender.SCREEN)) {
								appenders.put(appenderName, createAppender(appenderName, Appender.SCREEN, appenderThreshold, appenderDest));

							} else if (appenderType.equals(Appender.EVENT_LOG)) {
								if ((appenderDest != null) && !appenderDest.equals("")) {
									appenders.put(appenderName, createAppender(appenderName, Appender.EVENT_LOG, appenderThreshold, appenderDest));
								}
							}
						}
					}
				}

			}

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		}
	}

	protected static Appender getGodAppender() {
		return GOD_APPENDER;
	}

	public static Appender getAppender(String name) {
		if ((name != null) && !name.equals("")) {
			if (appenders.containsKey(name)) {
				return (Appender) appenders.get(name);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static void close() {
		Enumeration enumeration = appenders.elements();
		if (enumeration != null) {
			while (enumeration.hasMoreElements()) {
				((Appender) enumeration.nextElement()).close();
			}
		}
		appenders = new Hashtable();
	}

	public static void reset() {
		close();
		load(propFile);
	}

	private static Appender createAppender(String name, String type, String pThreshold, String destination) {

		Appender out = null;
		int threshold = Level.DEBUG;

		if ((pThreshold != null) && !pThreshold.equals("")) {
			pThreshold = pThreshold.trim();
			if (pThreshold.equalsIgnoreCase("DEBUG")) {
				threshold = Level.DEBUG;
			} else if (pThreshold.equalsIgnoreCase("INFO")) {
				threshold = Level.INFO;
			} else if (pThreshold.equalsIgnoreCase("WARN")) {
				threshold = Level.WARN;
			} else if (pThreshold.equalsIgnoreCase("ERROR")) {
				threshold = Level.ERROR;
			} else if (pThreshold.equalsIgnoreCase("FATAL")) {
				threshold = Level.FATAL;
			}
		}

		if (type.trim().equalsIgnoreCase(Appender.CONSOLE)) {
			out = new ConsoleAppender(name, type, threshold, destination);

		} else if (type.trim().equalsIgnoreCase(Appender.TEXT_FILE)) {
			out = new TextFileAppender(name, type, threshold, destination);

		} else if (type.trim().equalsIgnoreCase(Appender.RICH_TEXT_FILE)) {
			out = new RichTextFileAppender(name, type, threshold, destination);

		} else if (type.trim().equalsIgnoreCase(Appender.SCREEN)) {
			out = new ScreenAppender(name, type, threshold, destination);

		} else if (type.trim().equalsIgnoreCase(Appender.EVENT_LOG)) {
			out = new EventLogAppender(name, type, threshold, destination);
		}

		return out;

	}

}
