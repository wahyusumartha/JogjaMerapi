package com.github.jogjamerapi;

public class AppSetting {

	// private static final String JALIN_MERAPI_STREAM =
	// "http://search.twitter.com/search.json?q=jalinmerapi";
	//
	// private String mapsUrl = "http://maps.google.com/maps/api/staticmap";

	private String url = "http://search.twitter.com/search.json?q=jalinmerapi";

	public AppSetting(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
