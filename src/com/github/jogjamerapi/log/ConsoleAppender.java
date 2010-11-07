
package com.github.jogjamerapi.log;

public class ConsoleAppender extends AbstractAppender {

	public ConsoleAppender(String pName, String pType, int pThreshold, String pDestination) {
		super(pName, pType, pThreshold, pDestination);
	}

	public void writeLine(int level, String message, final int fg, final int bg, final boolean bold) {
		System.out.println(message);
	}

	public void show() {
	}

	public void close() {
	}

	public void clear() {
	}

	public void reset() {
	}

}
