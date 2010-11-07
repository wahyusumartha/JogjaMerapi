package com.github.jogjamerapi;

import com.github.jogjamerapi.json.JSONObject;
import com.github.jogjamerapi.json.JSONTokener;
import com.github.jogjamerapi.network.HttpClient;
import com.github.jogjamerapi.network.HttpConnectionFactory;

public class ServiceClient {
	private HttpConnectionFactory factory;

	private HttpClient httpClient;

	private String parameter;

	private AppSetting settings;

	private JalinMerapi jalinMerapi = null;

	public ServiceClient(AppSetting appSetting, HttpConnectionFactory hFactory) {
		settings = appSetting;
		factory = hFactory;
		httpClient = new HttpClient(factory);
	}

	public JSONObject read(String parameter) throws ServiceClientException {
		StringBuffer buffer = new StringBuffer();

		try {
			buffer = httpClient.doGet(settings.getUrl(), parameter, factory);
			if (buffer.length() == 0) {
				new ServiceClientException("No Responds");
			}
			return new JSONObject(new JSONTokener(buffer.toString()));

		} catch (ServiceClientException e) {
			throw e;
		} catch (Exception e) {
			new ServiceClientException(e.getMessage());
		}
		return null;
	}

	public HttpConnectionFactory getFactory() {
		return factory;
	}

	public void setFactory(HttpConnectionFactory factory) {
		this.factory = factory;
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public JalinMerapi getJalinMerapi() {
		if (jalinMerapi == null) {
			jalinMerapi = new JalinMerapiImpl(this);
		}
		return jalinMerapi;
	}

}
