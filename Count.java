package Homework4;
/**
 *����һ����Count��ʵ��ͳ���ı��ļ��и����ַ����ַ����ĸ����Ĺ��ܣ�Ҫ��ʵ�֣� 
 *���ַ�ͳ�ƣ���������ַ�������
 *������ͳ�ƣ�����������ʵ�����
*/

import java.io.*;
import java.util.*;

public class Count {

	private int number_w;
	private String filename;
	private int[] num_upper = new int[26]; // ���26����д��ĸ�ĸ���
	private int[] num_lower = new int[26]; // ���26��Сд��ĸ�ĸ�����java��int��ʼ���Զ�Ϊ0�������ֶ�����
	private HashMap<String, Integer> CountWord = new HashMap<String, Integer>();// ����ͳ�Ƶ���
	private ArrayList<String> Words = new ArrayList<String>();// ���ڱ��浥��

	public Count(String str) {
		filename = str;
	}

	public void countChar() throws IOException {
		/**
		 * ������ͳ���ַ�����
		 */
		int a;
		char ch, c;
		FileReader in = new FileReader(filename); // �ַ���������
		while ((a = in.read()) != -1) {
			ch = (char) a;
			for (int i = 0; i < 26; i++) {
				// Сд��ĸ
				c = (char) ('a' + i);
				if (ch == c) {
					num_lower[i]++; // �ж����ĸ���ĸ��������Ӧ��ĸ�ĸ�����1
					break;
				}
				// ��д��ĸ
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
		 * ������Ӧ�ַ��ĸ���
		 */
		if (c >= 'a' & c <= 'z')
			return num_lower[c - 'a'];
		else if (c >= 'A' & c <= 'Z')
			return num_upper[c - 'A'];
		return 0;
	}

	public void AddNewWord(String word) {
		/**
		 * ����һ���µ��� ������������������ �ڹ�ϣ�������øõ��ʸ���Ϊ1
		 */
		Words.add(word);// �ڵ����б��м����������
		CountWord.put(word, 1);// �ѵ��ʼ���HashMap
	}

	public void AddOldWord(String word) {
		/**
		 * ����һ���ɵĵ��� ��ϣ���иõ��ʵ�����+1
		 */
		CountWord.put(word, CountWord.get(word) + 1);// �ѵ��ʸ���+1
	}

	public int getTotalWord() {
		return number_w;
	}

	public int getNumofWord(String word) {
		return CountWord.get(word);
	}

	public void countWord() throws IOException {
		/**
		 * ������ͳ�Ƶ��ʸ���
		 */
		char ch;
		File myFile = new File(filename);
		BufferedReader in = new BufferedReader(new FileReader(myFile));
		String str;
		String str1 = new String(""); // ���ı��ļ��е��ַ���ȫ�������ڴ��У��˴�����str1�����ȫ���ַ��������ڲ���
		while ((str = in.readLine()) != null) {
			str1 += str;
		}
		in.close();

		/* ͳ���ܵĵ��ʸ�������ȡ���е��� */
		String word = new String();
		int sign_w = 1;
		number_w = 0;
		for (int i = 0; i < str1.length(); i++) {
			ch = str1.charAt(i);
			if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
				word += ch;
				sign_w = 0;
			} else if (sign_w == 0) {// ��ʱ���һ������
				String word_copy = new String(word);
				// word_copy=word;
				number_w++;
				sign_w = 1;
				if (!CountWord.containsKey(word_copy))// ���û�а����������
				{
					AddNewWord(word_copy);
				} else// ����������������
				{
					AddOldWord(word_copy);
				}
				word = "";// ����word�������
			}
		}
	}

	public void showResult() {
		/**
		 * ��ͳ�ƽ��������� �������Ӧ����ĸ���� ��������ʵ�ͳ��
		 */
		System.out.println("Result of letter counting:");
		char c;
		for (int i = 0; i < 26; i++) {
			c = (char) ('a' + i);
			System.out.print((i + 1) % 5 == 0 ? c + ":" + getNumofChar(c) + "\n" : c + ":" + getNumofChar(c) + "\t"); // ÿ�з�5��
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
