package library.com.util;

public class stringUtil {
	/*
	 * �ж��ַ����Ƿ�Ϊ��
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {// trim() �����Ƴ��ַ�������Ŀհ��ַ�������Ԥ�����ַ�
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
