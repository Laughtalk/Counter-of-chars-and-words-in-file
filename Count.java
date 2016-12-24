package Homework4;
/**
 *创建一个类Count，实现统计文本文件中各类字符和字符串的个数的功能，要求实现： 
 *按字符统计，输出各个字符的数量
 *按单词统计，输出各个单词的数量
*/

import java.io.*;
import java.util.*;

public class Count {

	private int number_w;
	private String filename;
	private int[] num_upper = new int[26]; // 存放26个大写字母的个数
	private int[] num_lower = new int[26]; // 存放26个小写字母的个数，java中int初始化自动为0，不用手动设置
	private HashMap<String, Integer> CountWord = new HashMap<String, Integer>();// 用于统计单词
	private ArrayList<String> Words = new ArrayList<String>();// 用于保存单词

	public Count(String str) {
		filename = str;
	}

	public void countChar() throws IOException {
		/**
		 * 方法，统计字符个数
		 */
		int a;
		char ch, c;
		FileReader in = new FileReader(filename); // 字符流，输入
		while ((a = in.read()) != -1) {
			ch = (char) a;
			for (int i = 0; i < 26; i++) {
				// 小写字母
				c = (char) ('a' + i);
				if (ch == c) {
					num_lower[i]++; // 判断是哪个字母，并给对应字母的个数加1
					break;
				}
				// 大写字母
				c = (char) ('A' + i);
				if (ch == c) {
					num_upper[i]++;
					break;
				}
			}
		}
		in.close();
	}

	public int getNumofChar(char c) {
		/**
		 * 返回相应字符的个数
		 */
		if (c >= 'a' & c <= 'z')
			return num_lower[c - 'a'];
		else if (c >= 'A' & c <= 'Z')
			return num_upper[c - 'A'];
		return 0;
	}

	public void AddNewWord(String word) {
		/**
		 * 处理一个新单词 在链表中添加这个单词 在哈希表中设置该单词个数为1
		 */
		Words.add(word);// 在单词列表中加入这个单词
		CountWord.put(word, 1);// 把单词加入HashMap
	}

	public void AddOldWord(String word) {
		/**
		 * 处理一个旧的单词 哈希表中该单词的数量+1
		 */
		CountWord.put(word, CountWord.get(word) + 1);// 把单词个数+1
	}

	public int getTotalWord() {
		return number_w;
	}

	public int getNumofWord(String word) {
		return CountWord.get(word);
	}

	public void countWord() throws IOException {
		/**
		 * 方法，统计单词个数
		 */
		char ch;
		File myFile = new File(filename);
		BufferedReader in = new BufferedReader(new FileReader(myFile));
		String str;
		String str1 = new String(""); // 将文本文件中的字符串全部放入内存中，此处定义str1，存放全部字符串，便于操作
		while ((str = in.readLine()) != null) {
			str1 += str;
		}
		in.close();

		/* 统计总的单词个数并获取所有单词 */
		String word = new String();
		int sign_w = 1;
		number_w = 0;
		for (int i = 0; i < str1.length(); i++) {
			ch = str1.charAt(i);
			if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
				word += ch;
				sign_w = 0;
			} else if (sign_w == 0) {// 此时获得一个单词
				String word_copy = new String(word);
				// word_copy=word;
				number_w++;
				sign_w = 1;
				if (!CountWord.containsKey(word_copy))// 如果没有包含这个单词
				{
					AddNewWord(word_copy);
				} else// 如果包含了这个单词
				{
					AddOldWord(word_copy);
				}
				word = "";// 清理word里的内容
			}
		}
	}

	public void showResult() {
		/**
		 * 对统计结果进行输出 先输出相应的字母数量 再输出单词的统计
		 */
		System.out.println("Result of letter counting:");
		char c;
		for (int i = 0; i < 26; i++) {
			c = (char) ('a' + i);
			System.out.print((i + 1) % 5 == 0 ? c + ":" + getNumofChar(c) + "\n" : c + ":" + getNumofChar(c) + "\t"); // 每行放5个
		}
		System.out.println();

		for (int i = 0; i < 26; i++) {
			c = (char) ('A' + i);
			System.out.print((i + 1) % 5 == 0 ? c + ":" + getNumofChar(c) + "\n" : c + ":" + getNumofChar(c) + "\t");
		}
		System.out.println();

		System.out.println("\nTotal words:" + getTotalWord());
		Iterator<String> t = Words.iterator();
		System.out.println("Word:\tCount");
		while (t.hasNext()) {
			String word = t.next();
			System.out.println(word + "\t" + getNumofWord(word));
		}
	}
}
