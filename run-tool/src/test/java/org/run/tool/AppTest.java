package org.run.tool;

import com.run.common.util.ParamChecker;

/**
 * Unit test for simple App.
 */
public class AppTest{
	public static void main(String[] args) {
		String str="{  'token':'string','quInfo': { 'newPassword': 'string', 'password': 'string', 'userId': 'string'  }, 'uscInfo': { 'member': 'string','tenant': 'string'} }";
		System.out.println(ParamChecker.isNotMatchJson(str));
	}
}
