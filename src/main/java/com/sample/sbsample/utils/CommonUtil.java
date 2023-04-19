package com.sample.sbsample.utils;

import java.io.BufferedReader;
import java.util.HashMap;

import com.google.gson.Gson;

public class CommonUtil {

	public static HashMap<String, Object> jsonStrToMap(String jsonStr) {
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap<String, Object>)gson.fromJson(jsonStr, map.getClass());
		return map;
	}
	
	public static HashMap<String, Object> jsonParamToMap(BufferedReader bufferedReader) {
		HashMap<String, Object> inParam = null;
		try {
			StringBuffer reqPostDataBuffer = new StringBuffer();
			String reqPostData = "";
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				reqPostDataBuffer.append(line);
			}
			reqPostData = reqPostDataBuffer == null ? null : reqPostDataBuffer.toString();
			inParam = CommonUtil.jsonStrToMap(reqPostData);
		} catch (Exception e) {
		}
		return inParam;
	}
}
