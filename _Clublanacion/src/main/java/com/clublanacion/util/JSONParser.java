package com.clublanacion.util;

import com.clublanacion.exception.ConnectionClosed;
import com.clublanacion.exception.ConnectionTimeoutException;
import com.clublanacion.exception.OfflineException;
import com.clublanacion.exception.UnkownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class JSONParser {
    private InputStream is = null;
    private JSONObject jObj = null;
    private String json = "";

    // constructor
    public JSONParser() {
    }

    public JSONObject getJSONFromUrl(String url, int timeOutInMilli,
                                     List<NameValuePair> params) throws OfflineException {

        try {
            HttpParams my_httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(my_httpParams,
                    timeOutInMilli);
            HttpConnectionParams.setSoTimeout(my_httpParams, timeOutInMilli);
            // Making HTTP request
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient(my_httpParams);

            StringBuilder bUrl = new StringBuilder(url);
            bUrl.append("?");
            for (NameValuePair param : params) {
                if (param != null) {
                    bUrl.append(param.getName());
                    bUrl.append("=");
                    bUrl.append(URLEncoder.encode(param.getValue(), "UTF-8"));

                    bUrl.append("&");
                }
            }


            HttpPost httpPost = new HttpPost(bUrl.toString());
            // httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            HttpResponse httpResponse = null;
            httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            json = sb.toString();
            // Log.e("JSON", json); //TODO quitar

            // try parse the string to a JSON object
            jObj = new JSONObject(json);

            // return JSON String
            return jObj;
        } catch (org.apache.http.ConnectionClosedException ex) {
            throw new ConnectionClosed(
                    "JSonParser::getJsonFromURL()::ConnectionClosed" + ex.getMessage());
        } catch (java.net.UnknownHostException ex) {
            throw new UnkownHostException(
                    "JSonParser::getJsonFromURL()::UnkownHost" + ex.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new OfflineException("JSonParser::getJsonFromURL()::BadEncoding");
        } catch (org.apache.http.conn.ConnectTimeoutException e) {
            throw new ConnectionTimeoutException(
                    "JSonParser::getJsonFromURL()::ConnectionTimeout" + e.getMessage());
        } catch (JSONException e) {
            throw new OfflineException("JSonParser::getJsonFromURL()::ErrorParsingData"
                    + e.getMessage());
        } catch (IllegalStateException e) {
            throw new OfflineException(e.getMessage());
        } catch (IOException e) {
            throw new OfflineException(e.getMessage());
        } catch (Exception e) {
            throw new OfflineException(e.getMessage());
        }
    }

    public JSONObject getJSONFromUrl(String url, int timeOutInMilli)
            throws OfflineException {
        try {
            HttpParams my_httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(my_httpParams,
                    timeOutInMilli);
            HttpConnectionParams.setSoTimeout(my_httpParams, timeOutInMilli);
            // Making HTTP request
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient(my_httpParams);
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            json = sb.toString();

            // try parse the string to a JSON object
            jObj = new JSONObject(json);

            // return JSON String
            return jObj;
        } catch (org.apache.http.ConnectionClosedException ex) {
            throw new ConnectionClosed(
                    "JSonParser::getJsonFromURL()::ConnectionClosed" + ex.getMessage());
        } catch (java.net.UnknownHostException ex) {
            throw new UnkownHostException(
                    "JSonParser::getJsonFromURL()::UnkownHost" + ex.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new OfflineException("JSonParser::getJsonFromURL()::BadEncoding");
        } catch (org.apache.http.conn.ConnectTimeoutException e) {
            throw new ConnectionTimeoutException(
                    "JSonParser::getJsonFromURL()::ConnectionTimeout" + e.getMessage());
        } catch (JSONException e) {
            throw new OfflineException("JSonParser::getJsonFromURL()::ErrorParsingData"
                    + e.getMessage());
        } catch (IllegalStateException e) {
            throw new OfflineException(e.getMessage());
        } catch (IOException e) {
            throw new OfflineException(e.getMessage());
        } catch (Exception e) {
            throw new OfflineException(e.getMessage());
        }
    }

    public String getStringFromUrl(String url, int timeOutInMilli)
            throws OfflineException {
        try {
            HttpParams my_httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(my_httpParams,
                    timeOutInMilli);
            HttpConnectionParams.setSoTimeout(my_httpParams, timeOutInMilli);
            // Making HTTP request
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient(my_httpParams);
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = null;

            httpResponse = httpClient.execute(httpGet);

            HttpEntity httpEntity = httpResponse.getEntity();

            is = httpEntity.getContent();

            BufferedReader reader;
            reader = new BufferedReader(
                    new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            return sb.toString();
        } catch (org.apache.http.ConnectionClosedException ex) {
            throw new ConnectionClosed(
                    "JSonParser::getJsonFromURL()::ConnectionClosed" + ex.getMessage());
        } catch (java.net.UnknownHostException ex) {
            throw new UnkownHostException(
                    "JSonParser::getJsonFromURL()::UnkownHost" + ex.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new OfflineException("JSonParser::getJsonFromURL()::BadEncoding");
        } catch (org.apache.http.conn.ConnectTimeoutException e) {
            throw new ConnectionTimeoutException(
                    "JSonParser::getJsonFromURL()::ConnectionTimeout" + e.getMessage());
        } catch (IllegalStateException e) {
            throw new OfflineException(e.getMessage());
        } catch (IOException e) {
            throw new OfflineException(e.getMessage());
        } catch (Exception e) {
            throw new OfflineException(e.getMessage());
        }
    }
}
