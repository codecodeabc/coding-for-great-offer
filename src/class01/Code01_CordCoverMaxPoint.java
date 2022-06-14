package class01;

import java.util.Arrays;

/**
 * 给定一个有序数组arr，代表坐落在X轴上的点，给定一个正数K，代表绳子的长度，返回绳子最多压中几个点？ 即使绳子边缘处盖住点也算盖住
 *
 * 思路: 对节点的范围进行二分搜索
 */
public class Code01_CordCoverMaxPoint {

	public static int maxPoint1(int[] arr, int L) {
		int res = 1;
		for (int i = 0; i < arr.length; i++) {
			int nearest = nearestIndex(arr, i, arr[i] - L);
			res = Math.max(res, i - nearest + 1);
		}
		return res;
	}

	/**
	 * 获取距离value最近的的节点
	 *
	 * 通过遍历右范围，计算出左端点位置（arr[i] - L）,在计算距离左端点最近的位置，该位置的节点就是包含在L范围内的最左节点
	 */
	public static int nearestIndex(int[] arr, int R, int value) {
		int L = 0;
		int index = R;
		// 在L ~ R 范围内进行二分法
		while (L <= R) {
			// 使用二分法
			// 求出中间节点
			int mid = L + ((R - L) >> 1);
			// 中间节点 >= value, 说明最近节点还在左边
			if (arr[mid] >= value) {
				// 先赋值， 因为左边可能没有节点了，即  arr[mid] == value
				index = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return index;
	}

	/**
	 * 暴力解法
	 */
	public static int maxPoint2(int[] arr, int L) {
		int left = 0;
		int right = 0;
		int N = arr.length;
		int max = 0;
		// 遍历左范围
		while (left < N) {
			// L的长度能覆盖左右范围，则该点符合要求
			while (right < N && arr[right] - arr[left] <= L) {
				right++;
			}
			// left++ ，绳子向右移动，此时right从上一次循环开始
			max = Math.max(max, right - (left++));
		}
		return max;
	}

	// for test
	public static int test(int[] arr, int L) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			int pre = i - 1;
			while (pre >= 0 && arr[i] - arr[pre] <= L) {
				pre--;
			}
			max = Math.max(max, i - pre);
		}
		return max;
	}

	// for test
	public static int[] generateArray(int len, int max) {
		int[] ans = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * max);
		}
		Arrays.sort(ans);
		return ans;
	}

	public static void main(String[] args) {
		int len = 100;
		int max = 1000;
		int testTime = 100000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int L = (int) (Math.random() * max);
			int[] arr = generateArray(len, max);
			int ans1 = maxPoint1(arr, L);
			int ans2 = maxPoint2(arr, L);
			int ans3 = test(arr, L);
			if (ans1 != ans2 || ans2 != ans3) {
				System.out.println("oops!");
				break;
			}
		}

	}

}
