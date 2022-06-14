package class01;

import java.io.File;
import java.util.Stack;

/**
 * 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回，隐藏文件也算，但是文件夹不算
 *
 * 思路： 递归，使用栈来代替函数递归栈
 */
public class Code02_CountFiles {

	// 注意这个函数也会统计隐藏文件
	public static int getFileNumber(String folderPath) {
		File root = new File(folderPath);
		// 不是文件夹或不是文件的，返回 0  , 在linux下可能是软链接或者其它
		if (!root.isDirectory() && !root.isFile()) {
			return 0;
		}
		if (root.isFile()) {
			return 1;
		}
		// 文件夹递归搜索
		Stack<File> stack = new Stack<>();
		// 压栈根节点
		stack.add(root);
		int files = 0;
		// 不断压栈和出栈,知道栈空即遍历完文件夹
		while (!stack.isEmpty()) {
			File folder = stack.pop();
			for (File next : folder.listFiles()) {
				if (next.isFile()) {
					files++;
				}
				// 文件夹就继续压栈
				if (next.isDirectory()) {
					stack.push(next);
				}
			}
		}
		return files;
	}

	public static void main(String[] args) {
		// 你可以自己更改目录
		String path = "/Users/zuochengyun/Desktop/";
		System.out.println(getFileNumber(path));
	}

}
