package com.github.jogjamerapi.network;

import java.io.InputStream;

import javax.microedition.io.HttpConnection;

import com.github.jogjamerapi.log.Logger;

public class HttpClient {

	private HttpConnectionFactory factory;

	protected Logger log = Logger.getLogger(getClass());

	public HttpClient(HttpConnectionFactory factory) {
		this.factory = factory;
	}

	public StringBuffer doGet(String url) throws Exception {
		return doGet(url, "", factory);
	}

	public StringBuffer doGet(String url, String parameter,
			HttpConnectionFactory hFactory) throws Exception {
		return doGet(url + parameter, hFactory);
	}

	public StringBuffer doGet(String url, HttpConnectionFactory hFactory)
			throws Exception {
		HttpConnection connection = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (url == null || url.length() == 0 || url.equals("")) {
				log.debug(getClass().getName()
						+ " : Url Not Initialized (URL NULL)");
				return null;
			}

			connection = factory.getHttpConnection(url);

			switch (connection.getResponseCode()) {
			case HttpConnection.HTTP_OK: {
				InputStream inputStream = connection.openInputStream();
				int c;
				while ((c = inputStream.read()) != -1) {
					buffer.append((char) c);
				}
				inputStream.close();
				break;
			}

			case HttpConnection.HTTP_TEMP_REDIRECT:
			case HttpConnection.HTTP_MOVED_TEMP:
			case HttpConnection.HTTP_MOVED_PERM: {
				url = connection.getHeaderField("Location");
				buffer = doGet(url, hFactory);
				break;
			}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return buffer;
	}
}
