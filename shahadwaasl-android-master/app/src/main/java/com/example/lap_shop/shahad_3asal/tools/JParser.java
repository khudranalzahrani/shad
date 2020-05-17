package com.example.lap_shop.shahad_3asal.tools;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

public class JParser {

    final String TAG = "JParser.java";

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    
    public JSONObject getJSONFromUrl(String url, List<NameValuePair> params2) {
    	String jsonText = "";
    	try{
      	  HttpParams params = new BasicHttpParams();
      	  HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
      	  HttpProtocolParams.setContentCharset(params, "UTF-8");
      	  params.setBooleanParameter("http.protocol.expect-continue", false);
      	  HttpClient httpclient = new DefaultHttpClient(params);

      	  HttpPost httppost = new HttpPost(url);
      	  httppost.setEntity(new UrlEncodedFormEntity(params2, "UTF-8"));
      	  HttpResponse http_response= httpclient.execute(httppost);

      	  HttpEntity entity = http_response.getEntity();
      	  jsonText = EntityUtils.toString(entity, HTTP.UTF_8);
			Log.e("Response ", "SendSMS:  " + jsonText);
      	  jObj = new JSONObject(jsonText);
    	}
    	catch(Exception e){
    		return null;
    	}


        // return JSON String
        return jObj;
    	  //Log.i("Response", jsonText);
    }

//    public JSONObject getJSONFromUrl(String url, List<NameValuePair> params) {
//
//        // make HTTP request
//        try {
//
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(url);
//            
//            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//            
//            HttpResponse httpResponse = httpClient.execute(httpPost);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            is = httpEntity.getContent();           
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            is.close();
//            json = sb.toString();
//
//        } catch (Exception e) {
//            Log.e(TAG, "Error converting result " + e.toString());
//        }
//
//        // try parse the string to a JSON object
//        try {
//            jObj = new JSONObject(json);
//        } catch (JSONException e) {
//            Log.e(TAG, "Error parsing data " + e.toString());
//        }
//
//        // return JSON String
//        return jObj;
//    }
}
