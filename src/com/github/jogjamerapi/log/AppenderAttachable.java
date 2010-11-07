
package com.github.jogjamerapi.log;

import java.util.Enumeration;

public interface AppenderAttachable {

	public void addAppender(Appender newAppender);

	public Enumeration getAllAppenders();

	public Appender[] getAppenders();

	public Appender getAppender(String name);

	public boolean isAttached(Appender appender);

	public void removeAllAppenders();

	public void removeAppender(Appender appender);

	public void removeAppender(String name);

}
