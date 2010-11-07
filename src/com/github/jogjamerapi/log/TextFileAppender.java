package com.github.jogjamerapi.log;

import java.io.IOException;
import java.io.OutputStream;

import javax.microedition.content.Invocation;
import javax.microedition.content.Registry;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

import com.github.jogjamerapi.util.file.FileUtils;



public class TextFileAppender extends AbstractAppender {

	protected FileConnection fc = null;
	protected OutputStream os = null;

	public TextFileAppender(String pName, String pType, int pThreshold,
			String pDestination) {
		super(pName, pType, pThreshold, pDestination);
	}

	public void writeLine(int level, String message, final int fg,
			final int bg, final boolean bold) {
		try {
			if ((fc == null) || !fc.exists() || !fc.canWrite()) {
				// Start over again
				try {
					if (fc != null) {
						try {
							fc.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						} finally {
							fc = null;
						}
					}

					fc = (FileConnection) Connector.open(getDestination(),
							Connector.READ_WRITE);
					if (!fc.exists()) {
						FileUtils.createRecursively(getDestination(),
								Connector.READ_WRITE, false);
					}

				} catch (Throwable t) {
					t.printStackTrace();
				}
			}

			if ((fc != null) && fc.exists() && fc.canWrite()) {
				os = fc.openOutputStream(fc.fileSize());
				message = message + "\n";
				byte[] lineArray = message.getBytes("UTF-8");
				os.write(lineArray, 0, lineArray.length);
			}

		} catch (IOException ex) {
			ex.printStackTrace();

		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					os = null;
				}
			}
		}
	}

	public void clear() {
		if (fc == null) {
			try {
				fc = (FileConnection) Connector.open(getDestination(),
						Connector.READ_WRITE);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if ((fc != null) && fc.exists()) {
			try {
				fc.delete();
				fc.create();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void show() {
		try {
			Registry.getRegistry(
					"net.rim.device.api.content.BlackBerryContentHandler")
					.invoke(new Invocation(getDestination()));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void close() {
		if (os != null) {
			try {
				os.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				os = null;
			}
		}
		if (fc != null) {
			try {
				fc.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				fc = null;
			}
		}
	}

	public void reset() {
	}

}