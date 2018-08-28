package com.zjq.euraka_core.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;

@SuppressWarnings("deprecation")
public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	static{
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
		//设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		mapper.getDeserializationConfig().set(
				org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	public static String  toJson(Object o){
		String s = null;
		try {
			s = mapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static <T> T fromJson(String json, Class<T> c){
		T t = null;
		try {
			t = mapper.readValue(json, c);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String json, TypeReference<T> tr){
		T t = null;
		try {
			t = (T) mapper.readValue(json, tr);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (T) t;
	}
}
