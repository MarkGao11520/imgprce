/**
 * RandomUtils.java
 * classes:com.bdqx.provider.utils.RandomUtils
 * 2015年10月21日
 */
package com.hw.xyls.service.sms;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @class: RandomUtils
 * @description:
 * @author ms
 * @date 2015年10月21日上午10:18:45
 * @record
 */
@Service
public class RandomUtils {

	public static String msgCode() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < 6; i++)
			result = result * 10 + array[i];
		if(String.valueOf(result).length()<6){
			return msgCode();
		}
		return String.valueOf(result);
	}
}
