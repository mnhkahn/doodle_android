package com.cyeam.doodle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Doodle {
	private final static String DOODLE_CYEAM = "http://doodle.cyeam.com/";
	private HttpClient client;
	private HttpGet req;
	private HttpResponse resp;
	private String strResult;

	public Doodle() {
		client = new DefaultHttpClient();

	}

	public com.cyeam.doodle.model.Doodle GetDoodle() {
		req = new HttpGet(DOODLE_CYEAM);
		com.cyeam.doodle.model.Doodle doodle = null;
		try {
			resp = client.execute(req);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 取得返回的字符串
				strResult = EntityUtils.toString(resp.getEntity());

				JSONObject jsonObject = new JSONObject(strResult);
				doodle = new com.cyeam.doodle.model.Doodle(
						jsonObject.getString("title"),
						jsonObject.getString("doodle"));
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doodle;
	}

	public Bitmap returnBitMap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	public static Bitmap getPicFromBytes(byte[] bytes) {
		Bitmap bitmap = BitmapFactory.decodeByteArray(bytes , 0, bytes.length);
		return bitmap;
	}
}
