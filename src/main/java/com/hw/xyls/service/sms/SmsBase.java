/**
 * SmsBase.java
 * classes:com.bdqx.provider.utils.SmsBase
 * 2015年10月21日
 */
package com.hw.xyls.service.sms;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @class: SmsBase
 * @description:
 * @author ms
 * @date 2015年10月21日上午9:41:32
 * @record
 */
@Service
public class SmsBase {
	private Integer x_eid = 12377;
	private String x_uid = "passby";
	private String x_pwd_md5 = EncryptionUtil.encrypt("123456", "MD5");
	private Integer x_gate_id = 300;


	public String SendSms(String mobile, String content)
			throws UnsupportedEncodingException {
		Integer x_ac = 12;// 发送信息
		HttpURLConnection httpconn = null;
		String result = "-20";
		String memo = content.trim();
		StringBuilder sb = new StringBuilder();
		sb.append("http://gateway.woxp.cn:6630/utf8/web_api/?x_eid=");
		sb.append(x_eid);
		sb.append("&x_uid=").append(x_uid);
		sb.append("&x_pwd_md5=").append(x_pwd_md5);
		sb.append("&x_ac=").append(x_ac);
		sb.append("&x_gate_id=").append(x_gate_id);
		sb.append("&x_target_no=").append(mobile);
		sb.append("&x_memo=").append(URLEncoder.encode(memo, "utf-8"));
		//sb.append("&x_memo=").append(content);
		try {
//			System.out.println("sb:"+sb.toString());
			URL url = new URL(sb.toString());
			httpconn = (HttpURLConnection) url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					httpconn.getInputStream()));
			result = rd.readLine();
//			System.out.println("result:"+result);
			rd.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpconn != null) {
				httpconn.disconnect();
				httpconn = null;
			}

		}
		return result;
	}
}
