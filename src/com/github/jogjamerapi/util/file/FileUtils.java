
package com.github.jogjamerapi.util.file;

import java.util.Enumeration;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;

public class FileUtils {

	protected static final String FILE_PREFIX = "file:///";

	public static boolean createRecursively(String fileName, int mode, boolean isDir) {

		boolean created = false;
		boolean parentCreated = false;

		if ((fileName == null) || fileName.equals("") || !fileName.trim().startsWith(FILE_PREFIX)) {
			// do nothing
		} else {

			fileName = fileName.trim();

			if (isRoot(fileName)) {
				created = true;
			} else {
				parentCreated = createRecursively(parentOf(fileName), mode, true);
				if (parentCreated) {
					created = createFileOrDir(fileName, mode, isDir);
				}
			}

		}

		return created;

	}

	protected static boolean createFileOrDir(String fileName, int mode, boolean isDir) {

		boolean created = false;

		try {
			fileName = fileName.trim();

			if (isDir && !fileName.endsWith("/")) {
				fileName += "/";
			}

			FileConnection fc = (FileConnection) Connector.open(fileName, mode);

			if (fc.exists()) {
				created = true;
			} else {
				if (isDir) {
					fc.mkdir();
				} else {
					fc.create();
				}
				created = true;
			}

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		}

		return created;
	}

	public static String parentOf(String inStr) {
		String result = null;

		if ((inStr != null) && !inStr.trim().equals("")) {
			inStr = inStr.trim();
			int index = inStr.lastIndexOf('/');
			if (index != -1) {
				result = inStr.substring(0, index);
			}
		}

		return result;
	}

	public static boolean isRoot(String pFileName) {
		boolean output = false;
		Enumeration e = FileSystemRegistry.listRoots();
		String fileName = pFileName.trim() + "/";

		while (e.hasMoreElements()) {
			String thisRoot = (String) e.nextElement();
			output = fileName.equals(FILE_PREFIX + thisRoot);
			if (output) {
				break;
			}
		}

		return output;
	}

}