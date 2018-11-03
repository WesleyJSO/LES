package br.com.les.backend.navigator;

import java.util.Map;

public interface INavigatorContext {

	public Map<String, Object> getAttributes();

	public void setAttribute(String key, Object attribute);
	
	public void setAttributes(Map<String, Object> attributes);

	public <R> R getAttribute(String key);

}
