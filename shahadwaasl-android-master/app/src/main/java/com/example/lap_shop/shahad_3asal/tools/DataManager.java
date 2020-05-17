
package com.example.lap_shop.shahad_3asal.tools;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {

	private static SharedPreferences getSharedPreferences(Context context) {
		return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
	}
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private static SharedPreferences getMultiProcessSharedPreferences(Context context) {
		return context.getSharedPreferences(context.getPackageName(), Context.MODE_MULTI_PROCESS);
	}
	
	public static boolean getBoolSetting(Context context, String key, boolean defaultValue) {
		SharedPreferences settings = getSharedPreferences(context);
		return settings.getBoolean(key, defaultValue);
	}

	public static void setBoolSetting(Context context, String key, boolean value) {
		SharedPreferences settings = getSharedPreferences(context);
		Editor editor = settings.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public static int getIntSetting(Context context, String key, int defaultValue) {
		SharedPreferences settings = getSharedPreferences(context);
		return settings.getInt(key, defaultValue);
	}
	public static void setIntSetting(Context context, String key, int value) {
		SharedPreferences settings = getSharedPreferences(context);
		Editor editor = settings.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	public  static void clearItem(Context context,String key){
		SharedPreferences mySPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = mySPrefs.edit();
		editor.remove(key);
		editor.apply();
	}
	public static String getStringSetting(Context context, String key, String defaultValue) {
		SharedPreferences settings = getSharedPreferences(context);
		return settings.getString(key, defaultValue);
	}

	public static void setStringSetting(Context context, String key, String value) {
		SharedPreferences settings = getSharedPreferences(context);
		Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static String[] getStringArSetting(Context context, String key){
		JSONArray items;
		try {
			items = new JSONArray(getStringSetting(context, key, "[]"));
			String[] ar=new String[items.length()];
			for (int i = 0; i < ar.length; i++)
				ar[i]=items.get(i).toString();
			return ar;
		} catch (JSONException e) {
			return new String[0];
		}
	}
	
	public static void setStringArSetting(Context context, String key, String[] value) {
		JSONArray items=new JSONArray();
		for (int i = 0; i < value.length; i++)
			items.put(value[i]);
		setStringSetting(context, key, items.toString());
	}

	public static ArrayList getArrayListSetting(Context context, String key){
		try {
			JSONArray items = getJSONArraySetting(context, key);;
			ArrayList list  = new ArrayList();
			for (int i = 0; i < items.length(); i++)
				list.add(items.get(i));
			return list;
		} catch (JSONException e) {
			return new ArrayList<>();
		}
	}

	public static void setArrayListSetting(Context context, String key, ArrayList list) {
		JSONArray items=new JSONArray();
		for (int i = 0; i < list.size(); i++)
			items.put(list.get(i));
		setJSONArraySetting(context, key, items);
	}
	
	public static HashMap<String, String> getHashMapSetting(Context context, String key) {
		HashMap<String, String> hashMap=new HashMap<String, String>();
		String content=getStringSetting(context, key, "");
		if(content.length()>2){
        	content=content.substring(1, content.length()-1);
	        String[] items=content.split(",");
	        for (int i = 0; i < items.length; i++) {
	            String[] keyValue = items[i].split("=");
	            if(keyValue.length>1)hashMap.put(keyValue[0].trim(), keyValue[1]);
	        }
		}
		return hashMap;
	}
	public static void setHashMapSetting(Context context, String key, HashMap<String, String> value) {
		setStringSetting(context, key, value.toString());
	}
	
	//JSONArray
	public static JSONArray getJSONArraySetting(Context context, String key) {
		SharedPreferences settings = getSharedPreferences(context);
		try {
			return new JSONArray(settings.getString(key, "[]"));
		} catch (JSONException e) {
			return new JSONArray();
		}
	}
	public static void setJSONArraySetting(Context context, String key, JSONArray value) {
		SharedPreferences settings = getSharedPreferences(context);
		Editor editor = settings.edit();
		editor.putString(key, value.toString());
		editor.commit();
	}
	public static void addObjectToJSONArraySetting(Context context, String key, Object value) {
		JSONArray array=getJSONArraySetting(context, key);
		array.put(value);
		setJSONArraySetting(context, key, array);
	}
	
	//JSONObject
	public static JSONObject getJSONObjectSetting(Context context, String key) {
		SharedPreferences settings = getSharedPreferences(context);
		try {
			return new JSONObject(settings.getString(key, "{}"));
		} catch (JSONException e) {
			return new JSONObject();
		}
	}
	public static void setJSONObjectSetting(Context context, String key, JSONObject value) {
		SharedPreferences settings = getSharedPreferences(context);
		Editor editor = settings.edit();
		editor.putString(key, value.toString());
		editor.commit();
	}
	public static void addObjectToJSONObjectSetting(Context context, String key, String objectKey, Object objectValue) {
		JSONObject object=getJSONObjectSetting(context, key);
		try {
			object.put(objectKey, objectValue);
			setJSONObjectSetting(context, key, object);
		} catch (JSONException e) {}
	}
	public static void removeObjectFROMJSONObjectSetting(Context context, String key, String objectKey) {
		JSONObject object=getJSONObjectSetting(context, key);
		try {
			object.remove(objectKey);
			setJSONObjectSetting(context, key, object);
		} catch (Exception e) {}
	}
}
