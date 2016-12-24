package Homework4;

import java.io.IOException;

public class test4 {

	public static void main(String[] args) throws IOException {
		/**
		 * 参数传入文件名
		 */
		Count cu = new Count(args[0]);
		cu.countChar();
		cu.countWord();
		cu.showResult();
	}
}
