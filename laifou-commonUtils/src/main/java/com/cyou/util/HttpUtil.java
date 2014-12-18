package com.cyou.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Http操作相关工具类
 * @author jyz
 */
public class HttpUtil {

  private static HttpClient httpClient = null;

  public static HttpClient getClient() {
    if(httpClient == null) {
      HttpParams params = new BasicHttpParams();
      HttpConnectionParams.setConnectionTimeout(params, 500000);
      HttpConnectionParams.setSoTimeout(params, 500000);
      HttpConnectionParams.setLinger(params, 0);

      SchemeRegistry schemeRegistry = new SchemeRegistry();
      schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
      schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

      PoolingClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
      cm.setDefaultMaxPerRoute(100);
      cm.setMaxTotal(200);
      httpClient = new DefaultHttpClient(cm, params);
    }
    return httpClient;
  }

  public static String syncPost(String url, Map<String, String> params, String encode) throws Exception {
    StringBuffer respStr = new StringBuffer();
    if(StringUtils.isNotBlank(url)) {
      if(StringUtils.isBlank(encode)) {
        encode = "UTF-8";
      }
      HttpPost httpPost = null;
      try {
        httpPost = new HttpPost(url);
        if(params != null && params.size() > 0) {
          List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
          for(Map.Entry<String, String> entry : params.entrySet()) {
            paramsList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
          }
          httpPost.setEntity(new UrlEncodedFormEntity(paramsList, encode));
        }
        HttpResponse httpResp = getClient().execute(httpPost);
        if(httpResp != null && httpResp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
          BufferedReader bufReader = new BufferedReader(new InputStreamReader(httpResp.getEntity().getContent()));
          String line = "";
          if(bufReader != null) {
            while((line = bufReader.readLine()) != null) {
              respStr.append(line);
            }
          }
        }
      }
      finally {
        if(httpPost != null) {
          httpPost.releaseConnection();
        }
      }
    }
    return respStr.toString();
  }

  public static String syncGet(String url, Map<String, String> params, String paramEncode, String readerEncode) throws Exception {
    String respStr = "";
    if(StringUtils.isNotBlank(url)) {
      if(StringUtils.isBlank(paramEncode)) {
        paramEncode = "UTF-8";
      }
      if(StringUtils.isBlank(readerEncode)) {
        readerEncode = "UTF-8";
      }
      HttpGet httpGet = null;
      try {
        if(params != null && params.size() > 0) {
          url += (url.indexOf("?") == -1 ? "?" : "&") + getParamsUrl(params, paramEncode);
        }
        httpGet = new HttpGet(url);
        HttpResponse httpResp = getClient().execute(httpGet);
        if(httpResp != null && httpResp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
          BufferedReader bufReader = new BufferedReader(new InputStreamReader(httpResp.getEntity().getContent(),
              readerEncode));
          String line = "";
          if(bufReader != null) {
            while((line = bufReader.readLine()) != null) {
              respStr += line;
            }
          }
        }
      }
      finally {
        if(httpGet != null) {
          httpGet.releaseConnection();
        }
      }
    }
    return respStr;
  }

  public static String getParamsUrl(Map<String, String> params, String encode) throws Exception {
    StringBuffer url = new StringBuffer();
    if(params != null && params.size() > 0) {
      if(StringUtils.isBlank(encode)) {
        encode = "UTF-8";
      }
      for(Map.Entry<String, String> entry : params.entrySet()) {
        url.append(entry.getKey());
        url.append("=");
        url.append(entry.getValue() == null ? "" : URLEncoder.encode(entry.getValue(), encode));
        url.append("&");
      }
    }
    return url.toString().replaceAll("&$", "");
  }

  public static String getIP(HttpServletRequest request) {
    String ip = null;
    if(request != null) {
      ip = request.getHeader("X-Forwarded-For");
      if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
      }
      if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
      }
      if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_CLIENT_IP");
      }
      if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
      }
      if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
      }
      if(StringUtils.isBlank(ip)) {
        return "";
      }
      if(ip.contains(",")) {
        String[] ips = StringUtils.split(ip, ",");
        for(String real : ips) {
          if(! "unknown".equalsIgnoreCase(StringUtils.trim(real))) {
            ip = StringUtils.trim(real);
            break;
          }
        }
      }
    }
    return ip;
  }

 
}
