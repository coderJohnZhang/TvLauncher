package com.gotech.tv.launcher.util;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class DesEncryptUtil {
	
	Key key;
	
	//加密
	  public String encrypt(String s,String key){
	    String str = "";
	    int ch;
	    if(key.length() == 0){
	        return s;
	    }
	    else if(!s.equals(null)){
	        for(int i = 0,j = 0;i < s.length();i++,j++){
	          if(j > key.length() - 1){
	            j = j % key.length();
	          }
	          ch = s.codePointAt(i) + key.codePointAt(j);
	          if(ch > 65535){
	            ch = ch % 65535;//ch - 33 = (ch - 33) % 95 ;
	          }
	          str += (char)ch;
	        }
	    }
	    return str;

	  } 
	  //解密
	  public String decode(String s,String key){
	    String str = "";
	    int ch;
	    if(key.length() == 0){
	        return s;
	    }
	    else if(!s.equals(key)){
	        for(int i = 0,j = 0;i < s.length();i++,j++){
	          if(j > key.length() - 1){
	            j = j % key.length();
	          }
	          ch = (s.codePointAt(i) + 65535 - key.codePointAt(j));
	          if(ch > 65535){
	            ch = ch % 65535;//ch - 33 = (ch - 33) % 95 ;
	          }
	          str += (char)ch;
	        }
	    }
	    return str;
	  }

	/**
	 * 根据参数生成KEY
	 * 
	 * @param strKey
	 */
	public void getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(strKey.getBytes()));
			this.key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密String明文输入,String密文输出
	 * 
	 * @param strMing
	 * @return
	 */
	public String getEncString(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		try {
			return byte2hex(getEncCode(strMing.getBytes()));

			// byteMing = strMing.getBytes("UTF8");
			// byteMi = this.getEncCode(byteMing);
			// strMi = new String( byteMi,"UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public String getDesString(String strMi) {
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			return new String(getDesCode(hex2byte(strMi.getBytes())));

			// byteMing = this.getDesCode(byteMi);
			// strMing = new String(byteMing,"UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) { // 一个字节的数，
		// 转成16进制字符串
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			// 整数转成十六进制表示
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase(); // 转成大写
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2)
		{
			String item = new String(b, n, 2);
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}

		return b2;
	}

}
