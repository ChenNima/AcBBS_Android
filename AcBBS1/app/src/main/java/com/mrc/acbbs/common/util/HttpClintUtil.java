package com.mrc.acbbs.common.util;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.mrc.acbbs.common.Constant;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClintUtil {

	static File targetFile;
	static InputStream inputStream;

	public static String getUrlCon(String url, Map<String, String> mapParams){
		URL mURL = null;
		try {
			mURL = new URL(url);
			URLConnection urlConnection = mURL.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			if(mapParams!=null){
				DataOutputStream out = new DataOutputStream(urlConnection
						.getOutputStream());
				String params="";
				for (Map.Entry<String, String> entry : mapParams.entrySet()) {
					params+="&"+entry.getKey()+"="+ URLEncoder.encode(entry.getValue(),"UTF-8");
				}
				out.writeBytes(params);
				out.flush();
				out.close();
			}
			inputStream = urlConnection.getInputStream();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return StreamToString(inputStream);
	}

	private static String StreamToString(InputStream inputStream) {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuilder result = new StringBuilder();
		String line = null;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStreamReader.close();
				inputStream.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}
	public static String getUrl(String urlSpec, Map<String, String> mapParams)
			throws IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter("http.protocol.single-cookie-header", Boolean.valueOf(true));
		client.getParams().setParameter("http.useragent", "HAvfun Client");
		HttpPost post = new HttpPost(urlSpec);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (mapParams != null && !mapParams.isEmpty()) {
			for (Map.Entry<String, String> entry : mapParams.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}
		post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		HttpResponse response = client.execute(post);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			InputStream stream = response.getEntity().getContent();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			int i = -1;
			while ((i = stream.read()) != -1) {
				outStream.write(i);
			}
			Log.i("下载数据", "完成下载URL："+urlSpec);
			String result = new String(outStream.toByteArray(), "utf-8");
			return result;
		}
		return null;
	}

	public static String uploadForString(String urlSpec,
			Map<String, String> mapParams) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(urlSpec);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (mapParams != null && !mapParams.isEmpty()) {
			for (Map.Entry<String, String> entry : mapParams.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}

		post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		HttpResponse response = client.execute(post);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			InputStream stream = response.getEntity().getContent();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			int i = -1;
			while ((i = stream.read()) != -1) {
				outStream.write(i);
			}
			String json = new String(outStream.toByteArray(), "utf-8");
			return json;
		}
		return null;
	}

	public static String imageUpload(Bitmap bitmap, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		MultipartEntity multipartEntity = new MultipartEntity();
		HttpPost httppost = new HttpPost(url);
		HttpResponse httpResponse;
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		File sdcardDir = Environment.getExternalStorageDirectory();
		File destDir = new File(sdcardDir + Constant.CACHE_DIR);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		try {
			targetFile = new File(destDir.getCanonicalPath() + "/" + "file.jpg");
			FileOutputStream fos = new FileOutputStream(targetFile);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
			fos.close();
			multipartEntity.addPart("file", new FileBody(targetFile));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		httppost.setEntity(multipartEntity);
		try {
			httpResponse = httpclient.execute(httppost);
			String response = EntityUtils.toString(httpResponse.getEntity(),
					HTTP.UTF_8);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
