package br.com.netflics.core.json;

public class JsonOk {

	private final Object resp;

	public JsonOk(Object resp) {
		this.resp = resp;

	}

	public Object getResp() {
		return resp;
	}
}