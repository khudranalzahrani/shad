package com.example.lap_shop.shahad_3asal.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class LanguageManager {

	final private static String LANGUAGE_PREF = "language";
	final public static String LANGUAGE_ENGLISH = "en";
	final public static String LANGUAGE_ARABIC = "ar";

	public static void setLocale(Activity activity, String lang) {
		Locale myLocale = new Locale(lang);
		Resources res = activity.getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		res.updateConfiguration(conf, dm);
		Intent refresh = new Intent(activity, activity.getClass());
		activity.startActivity(refresh);
		activity.finish();
	}

	public static boolean setLanguage(Context context, String lang){
		Log.e("currentlang", "lang:"+getLanguage(context)+","+lang);
		if(!getLanguage(context).equalsIgnoreCase(lang)){
			SharedPreferences.Editor preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE).edit();
			preferences.putString(LANGUAGE_PREF, lang);
			preferences.commit();
			changeLanguage(context, lang);

			return true;
		}else return false;
	}

	public static String getLanguage(Context context){
		return context.getSharedPreferences("preferences", Context.MODE_PRIVATE).getString(LANGUAGE_PREF, "");
	}

	public static String getUsedLanguage(Context context) {
		return context.getSharedPreferences("preferences", Context.MODE_PRIVATE).getString(LANGUAGE_PREF, "ar");

	}

	public static void changeLanguage(Context context, String lang){
		if(lang.length()>0){
			Log.e("changeLanguage", lang);
			Configuration config = new Configuration();
			config.locale = new Locale(lang);
			context.getResources().updateConfiguration(config, null);
		}
	}

	public static void setLocale(String lang,Context context) {
		Locale myLocale = new Locale(lang);
		Resources res = context.getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		res.updateConfiguration(conf, dm);
	}

	public static void setAppLanguage(Context context)
	{
		System.out.println(getUsedLanguage(context)+"oooo");
		setLanguage(context,getUsedLanguage(context));
        Locale.setDefault(new Locale(LanguageManager.LANGUAGE_ARABIC));
		if(Locale.getDefault().getLanguage().equalsIgnoreCase("ar") && LanguageManager.getLanguage(context).length()==0){
			LanguageManager.setLanguage(context, LanguageManager.LANGUAGE_ARABIC);
			LanguageManager.changeLanguage(context, LanguageManager.LANGUAGE_ARABIC);
		}else{
			if(LanguageManager.getLanguage(context).length()==0)LanguageManager.setLanguage(context, LanguageManager.LANGUAGE_ENGLISH);
			LanguageManager.changeLanguage(context, LanguageManager.getLanguage(context));
		}
        //setLocale(getUsedLanguage(context),context);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






	public static void loadLocale(Context context)
	{
		String langPref = LANGUAGE_PREF;
		SharedPreferences prefs = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
		String language = prefs.getString(langPref, "");
		Locale locale = new Locale(language);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		context.getApplicationContext().getResources().updateConfiguration(config, null);
		//changeLang(language,context);
	}
	public static void saveLocale(String lang,Context context)
	{
		String langPref = LANGUAGE_PREF;
		SharedPreferences prefs = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(langPref, lang);
		editor.commit();
	}
	public static void changeLang(String lang,Context context)
	{
		if (lang.equalsIgnoreCase(""))
			return;
		Locale myLocale=myLocale = new Locale(lang);
		saveLocale(lang,context);
		Locale.setDefault(myLocale);
		android.content.res.Configuration config = new android.content.res.Configuration();
		config.locale = myLocale;
		context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());

	}


}