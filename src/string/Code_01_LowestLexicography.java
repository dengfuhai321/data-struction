package string;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 问题：如何连接字符串，使组成的这个字符串是最小的字符串(字符串长度不一样的短的默认补0)
 * 误区：不能两两比较字符串，小的就在前面，大的就排到后面，例：b,ba   bba  bab  这里b小ba大，然而bba大于bab，所以不能直接两两字符串比较大小
 * 如何比较，就是先两个字符串一前一后，一后一前连接起来，然后比较，小的放前面，打的放后面
 * 
 * 连接两个字符串a，b的实质就是前一个字符串a向左位移了b的长度这么多位，然后加上b的值
 * ab   bsc   分别代表一个阿斯玛值  12  23  连接后1223就是12*10^2+23
 * 
 */
public class Code_01_LowestLexicography {
	// 这里实现了比较器
	public static class MyComparator implements Comparator<String> {
		// 这是字符串排序的规则，小的排前面，大的排后面
		@Override
		public int compare(String a, String b) {// 正数返回的是b前a后,负数返回的是a前b后
			return (a + b).compareTo(b + a);
		}
	}

	public static String lowestString(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		Arrays.sort(strs, new MyComparator());// 把字符串数组排序
		String res = "";
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	public static void main(String[] args) {
		String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
		System.out.println(lowestString(strs1));

		String[] strs2 = { "ba", "b" };
		System.out.println(lowestString(strs2));
		System.out.println("a".compareTo("b"));

	}

}
