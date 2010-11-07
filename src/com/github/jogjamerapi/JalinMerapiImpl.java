package com.github.jogjamerapi;

import com.github.jogjamerapi.json.JSONArray;
import com.github.jogjamerapi.json.JSONException;
import com.github.jogjamerapi.json.JSONObject;
import com.github.jogjamerapi.log.Logger;

public class JalinMerapiImpl implements JalinMerapi {

	private JSONObject jsonObject = null;
	private ServiceClient serviceClient = null;
	protected Logger log = Logger.getLogger(getClass());

	public JalinMerapiImpl(ServiceClient serviceClient) {
		this.serviceClient = serviceClient;
		this.jsonObject = new JSONObject();
	}

	public JalinMerapiImpl(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public String fromUser() {
		try {
			return jsonObject.getString("from_user");
		} catch (JSONException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public String getCreatedAt() {
		try {
			return jsonObject.getString("created_at");
		} catch (JSONException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public JalinMerapi[] getResults() {
		JalinMerapi[] results = null;

		try {
			JSONArray array = serviceClient.read(serviceClient.getParameter())
					.getJSONArray("results");
			results = new JalinMerapi[array.length()];

			for (int i = 0; i < results.length; i++) {
				JSONObject object = array.getJSONObject(i);
				results[i] = new JalinMerapiImpl(object);
				log.debug(object.toString());
			}

		} catch (JSONException e) {
			log.error(getClass().getName() + " : " + e.getMessage());
		} catch (ServiceClientException e) {
			log.error(getClass().getName() + " : " + e.getMessage());
		}
		return results;

	}

	public String getText() {
		try {
			return jsonObject.getString("text");
		} catch (JSONException e) {
			log.error(e.getMessage());
		}
		return null;

	}

}
