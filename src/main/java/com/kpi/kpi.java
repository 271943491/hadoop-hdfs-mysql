package com.kpi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class kpi {

	public static vo parser(String inputdata) throws ParseException {

		vo kpi = new vo();

		String[] arr = inputdata.split(" ");

		// 格式化时间
		SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
		String time = arr[3].substring(1);
		java.util.Date timestamp = df.parse(time);
		df = new SimpleDateFormat("yyyyMMddHHmmss");
		time = df.format(timestamp);

		// 判断用户访问结果是否为HTTP 200 OK
		if (!arr[8].equals("200")) {
			kpi.setValid(false);
		}

		kpi.setRemote_addr(arr[0]);
		kpi.setRemote_user(arr[1]);
		kpi.setTime_local(time);
		kpi.setRequest(arr[6]);
		kpi.setStatus(arr[8]);
		kpi.setBody_bytes_sent(arr[9]);
		kpi.setHttp_referer(arr[10]);
		kpi.setHttp_user_agent(arr[11].substring(1) + arr[12] + arr[13] + arr[14]);

		return kpi;

	}
}
