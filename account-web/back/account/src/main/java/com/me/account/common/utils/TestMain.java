package com.me.account.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String result = MD5.encrypt("000000", "admin");
//		System.out.println("result="+result);
		  String str1="com.sillycat.easyaop.dao.impl.RoleDAOImpl.save";
		  String str2="com.sillycat.easyaop.dao.impl.UserDAOImpl.save";
		  String str3="com.sillycat.easyaop.dao.impl.BaseDAOImpl.save";
		  Pattern pattern=Pattern.compile(".*[^(Role)]DAOImpl[.]save.*");
		  Matcher matcher1=pattern.matcher(str1);
		  Matcher matcher2=pattern.matcher(str2);
		  Matcher matcher3=pattern.matcher(str3);
		  System.out.println(matcher1.matches());
		  System.out.println(matcher2.matches());
		  System.out.println(matcher3.matches());

	}

}
