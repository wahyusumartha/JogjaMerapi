
package com.github.jogjamerapi.log;

public interface Loggable {

	public void debug(String message);

	public void info(String message);

	public void warn(String message);

	public void error(String message);

	public void fatal(String message);

}
