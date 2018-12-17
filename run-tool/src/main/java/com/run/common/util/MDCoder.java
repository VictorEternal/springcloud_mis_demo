package com.run.common.util;

import java.security.MessageDigest;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

/**
 * MD消息摘要组件
 *
 */
public abstract class MDCoder {

	/**
	 * MD2消息摘要
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encodeMD2(byte[] data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD2");
		return md.digest(data);
	}



	/**
	 * MD2消息摘要
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD2Hex(byte[] data) throws Exception {
		byte[] b = encodeMD2(data);
		return new String(Hex.encode(b));
	}



	/**
	 * MD2消息摘要
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD2Hex(String data) throws Exception {
		return encodeMD2Hex(data.getBytes());
	}



	/**
	 * MD2消息摘要
	 * 
	 * @param data
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD2Hex(String data, String charset) throws Exception {
		return encodeMD2Hex(data.getBytes(charset));
	}



	/**
	 * MD4消息摘要
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encodeMD4(byte[] data) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		MessageDigest md = MessageDigest.getInstance("MD4");
		return md.digest(data);
	}



	/**
	 * MD4消息摘要
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD4Hex(byte[] data) throws Exception {
		byte[] b = encodeMD4(data);
		return new String(Hex.encode(b));
	}



	/**
	 * MD4消息摘要
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD4Hex(String data) throws Exception {
		return encodeMD4Hex(data.getBytes());
	}



	/**
	 * MD4消息摘要
	 * 
	 * @param data
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD4Hex(String data, String charset) throws Exception {
		return encodeMD4Hex(data.getBytes(charset));
	}



	/**
	 * MD5消息摘要
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encodeMD5(byte[] data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		return md.digest(data);
	}



	/**
	 * MD5消息摘要
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD5Hex(byte[] data) throws Exception {
		byte[] b = encodeMD5(data);
		return new String(Hex.encode(b));
	}



	/**
	 * MD5消息摘要
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD5Hex(String data) throws Exception {
		return encodeMD5Hex(data.getBytes());
	}



	/**
	 * MD5消息摘要(16位)
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD5_16Hex(String data) throws Exception {
		String s = encodeMD5Hex(data.getBytes());
		return s.substring(8, 24);
	}



	/**
	 * MD5消息摘要
	 * 
	 * @param data
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD5Hex(String data, String charset) throws Exception {
		return encodeMD5Hex(data.getBytes(charset));
	}



	/**
	 * MD5消息摘要(16位)
	 * 
	 * @param data
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD5_16Hex(String data, String charset) throws Exception {
		String s = encodeMD5Hex(data.getBytes(charset));
		return s.substring(8, 24);
	}

}
