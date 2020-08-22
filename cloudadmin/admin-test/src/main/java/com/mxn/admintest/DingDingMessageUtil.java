package com.mxn.admintest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

public class DingDingMessageUtil {
	//https://oapi.dingtalk.com/robot/send?access_token=d0b2ed443957a13efbc4371c259bbe144b12e2a807ce813a1395f7fb8ab2ab34
	///只需要access_token
	public static String access_token = "d0b2ed443957a13efbc4371c259bbe144b12e2a807ce813a1395f7fb8ab2ab34";

	public static void sendTextMessage(String msg) {
		try {
			Message message = new Message();
			message.setMsgtype("text");
			message.setText(new MessageInfo(msg));
			URL url = new URL("https://oapi.dingtalk.com/robot/send?access_token=" + access_token);
			// 建立 http 连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
			conn.connect();
			OutputStream out = conn.getOutputStream();
			String textMessage = JSONObject.toJSONString(message);
			byte[] data = textMessage.getBytes();
			out.write(data);
			out.flush();
			out.close();
			InputStream in = conn.getInputStream();
			byte[] data1 = new byte[in.available()];
			in.read(data1);
			System.out.println(new String(data1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
