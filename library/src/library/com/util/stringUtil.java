package library.com.util;

public class stringUtil {
	/*
	 * ÅĞ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {// trim() º¯ÊıÒÆ³ı×Ö·û´®Á½²àµÄ¿Õ°××Ö·û»òÆäËûÔ¤¶¨Òå×Ö·û
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

}
