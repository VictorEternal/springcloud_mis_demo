package com.run.apidoc.utils;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * 
 * JS类
 * 
 * @author: lyc
 * @version: 1.0, 2017年2月13日
 */
public class JSRunner {

	private Context		ct;
	private Scriptable	scope;



	public JSRunner() {
		this.ct = Context.enter();
		this.scope = ct.initStandardObjects();
	}



	public static void main(String[] args) {
		JSRunner runner = new JSRunner();
		System.out.println(runner.run("1 + 2"));
	}



	public Context getContext() {
		return this.ct;
	}



	public String run(String code) {
		Object result;
		try {
			result = ct.evaluateString(scope, code, null, 1, null);
		} catch (Exception e) {
			e.printStackTrace();
			return "JS_ERROR";
		}
		return result == null ? null : result.toString();
	}
}
