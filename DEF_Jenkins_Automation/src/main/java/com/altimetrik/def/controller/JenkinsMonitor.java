/*package com.altimetrik.def.controller;

import org.apache.http.auth.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;

import com.google.gson.Gson;

public class JenkinsMonitor {

    public static void main(String[] args) throws Exception {

        String protocol = "https";
        String host = "pipeline.altimetrik.com";
        int port = 8080;
        String usernName = "platformdef";
        String password = "P@ssw0rd123";

        DefaultHttpClient httpclient = new DefaultHttpClient();
        httpclient.getCredentialsProvider().setCredentials(
                new AuthScope(host, port), 
                new UsernamePasswordCredentials(usernName, password));

        String jenkinsUrl = protocol + "://" + host + ":" + port + "/jenkins/";

        try {
            // get the crumb from Jenkins
            // do this only once per HTTP session
            // keep the crumb for every coming request
            System.out.println("... issue crumb");
            HttpGet httpGet = new HttpGet(jenkinsUrl + "crumbIssuer/api/json");
            String crumbResponse= toString(httpclient, httpGet);
            CrumbJson crumbJson = new Gson()
                .fromJson(crumbResponse, CrumbJson.class);

            // add the issued crumb to each request header
            // the header field name is also contained in the json response
            System.out.println("... issue rss of latest builds");
            HttpPost httpost = new HttpPost(jenkinsUrl + "rssLatest");
            httpost.addHeader(crumbJson.crumbRequestField, crumbJson.crumb);
            toString(httpclient, httpost);

        } finally {
            httpclient.getConnectionManager().shutdown();
        }

    }

    // helper construct to deserialize crumb json into 
    public static class CrumbJson {
        public String crumb;
        public String crumbRequestField;
    }

    private static String toString(DefaultHttpClient client, 
        HttpRequestBase request) throws Exception {
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = client.execute(request, responseHandler);
        System.out.println(responseBody + "\n");
        return responseBody;
    }

}
*/