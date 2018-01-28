package com.mycompany.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

public class TwitterRestCall {
	static String consumerKeyStr = "";
	static String consumerSecretStr = "";
	static String accessTokenStr = "";
	static String accessTokenSecretStr = "";
	OAuthConsumer consumer;
	private JsonConverter jsonConverter;

	public TwitterRestCall() {
		jsonConverter = new JsonConverter();
		consumerKeyStr= readProperties("consumerKey");
		consumerSecretStr=readProperties("consumerSecret");
		accessTokenStr=readProperties("accessToken");
		accessTokenSecretStr=readProperties("accessTokenSecret");
		consumer = new CommonsHttpOAuthConsumer(consumerKeyStr,
				consumerSecretStr);

		consumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);
	}

	private String readProperties(String key) {
		String url = "";
		try {
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(new File(
					"config.properties"));
			properties.load(fileInputStream);
			url = properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public void getTrends() {
		try {
			String url = readProperties("trends");
			HttpGet request = new HttpGet(url);
			consumer.sign(request);
			HttpClient client = new DefaultHttpClient();
			/*client.getParams()
					.setParameter(ConnRoutePNames.DEFAULT_PROXY, host);*/
			HttpResponse response = client.execute(request);

			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println(statusCode + ":"
					+ response.getStatusLine().getReasonPhrase());
			FileOutputStream fileOutputStream = new FileOutputStream(new File(
					"trends.txt"));

			fileOutputStream.write(IOUtils.toString(
					response.getEntity().getContent()).getBytes());
			jsonConverter.convertTrendsValue("trends.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getTweetSearch(String queryName)
	{
		try
		{
			String url = readProperties("search");
			queryName=queryName.replace("#", "");
			queryName=queryName.replace(" ", "+");
			url+="="+queryName+"&count=1000";
			System.out.println(url);
			HttpGet request = new HttpGet(url);
			consumer.sign(request);
			HttpClient client = new DefaultHttpClient();
			/*client.getParams()
					.setParameter(ConnRoutePNames.DEFAULT_PROXY, host);*/
			HttpResponse response = client.execute(request);	
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println(statusCode + ":"
					+ response.getStatusLine().getReasonPhrase());
			FileOutputStream fileOutputStream = new FileOutputStream(new File(
					"search.txt"));

			fileOutputStream.write(IOUtils.toString(
					response.getEntity().getContent()).getBytes());
			jsonConverter.convertSearchValue("search.txt");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void getAggregation()
	{
		try
		{
			jsonConverter.convertAggregationValue("search.txt");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
