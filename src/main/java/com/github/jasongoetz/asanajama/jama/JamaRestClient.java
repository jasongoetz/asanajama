package com.github.jasongoetz.asanajama.jama;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.jersey.core.util.Base64;
import com.github.jasongoetz.asanajama.exception.GatewayException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JamaRestClient {

    private static final String JAMA_REST_VERSION = "v1";

    private static final String REST_API_PATH = "/rest/" + JAMA_REST_VERSION + "/";

    private static Logger logger = Logger.getLogger(JamaRestClient.class);

    private HttpClient httpClient;
    private MultiThreadedHttpConnectionManager connMgr;

    //Used for authentication
    private String username;
    private String password;
    private String jamaURL;

    private HttpClient getHttpClient() {
        HttpClient httpClient = new HttpClient(connMgr);
        httpClient.getState().setCredentials(
                new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM),
                new UsernamePasswordCredentials(username, password));
        httpClient.getParams().setSoTimeout(30000);
        return httpClient;
    }

    private void init(String username, String password, String url) throws GatewayException {
        this.username = username;
        this.password = password;
        this.jamaURL = url.trim();
        if (jamaURL.endsWith("/")) {
            jamaURL = jamaURL.substring(0, jamaURL.length() - 1);
        }
        connMgr = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams connMgrParams = new HttpConnectionManagerParams();
        connMgrParams.setDefaultMaxConnectionsPerHost(1);
        connMgr.setParams(connMgrParams);
        this.httpClient = getHttpClient();
    }

    public static JamaRestClient create(String username, String password, String url) throws GatewayException {
        JamaRestClient client = new JamaRestClient();
        client.init(username, password, url);
        return client;
    }

    public String get(String url) throws GatewayException {
        if (url == null) {
            return null;
        }
        return makeRestCall(new GetMethod(getCompleteUrlForMethod(url)));
    }

    public String delete(String url) throws GatewayException {
        if (url == null) {
            return null;
        }
        return makeRestCall(new DeleteMethod(getCompleteUrlForMethod(url)));
    }

    public String post(String url, String json) throws GatewayException {
        if (url == null) {
            return null;
        }
        PostMethod postMethod = new PostMethod(getCompleteUrlForMethod(url));
        try {
            StringRequestEntity requestEntity = new StringRequestEntity(json, "application/json", "UTF-8");
            postMethod.setRequestEntity(requestEntity);
            return makeRestCall(postMethod);
        } catch (UnsupportedEncodingException e) {
            logger.error("Error executing POST, UnsupportedEncodingException thrown.");
            throw new GatewayException("Error executing POST, UnsupportedEncodingException thrown.", e);
        }
    }

    public String put(String url, String json) throws GatewayException {
        if (url == null) {
            return null;
        }
        PutMethod putMethod = new PutMethod(getCompleteUrlForMethod(url));
        try {
            StringRequestEntity requestEntity = new StringRequestEntity(json, "application/json", "UTF-8");
            putMethod.setRequestEntity(requestEntity);
            return makeRestCall(putMethod);
        } catch (UnsupportedEncodingException e) {
            logger.error("Error executing PUT, UnsupportedEncodingException thrown.");
            throw new GatewayException("Error executing PUT, UnsupportedEncodingException thrown.", e);
        }
    }

    public String makeRestCall(HttpMethod method) throws GatewayException {
        String response = null;
        try {
            //method.setDoAuthentication(true);
            byte[] api_key = Base64.encode(String.format("%s:%s", username, password));
            method.addRequestHeader("authorization", String.format("Basic %s", new String(api_key)));

            logger.debug("Calling Jama REST " + method.getName() + " " + method.getPath());
            int statusCode = httpClient.executeMethod(method);
            if (statusCode == HttpStatus.SC_UNAUTHORIZED) {
                throw new GatewayException(String.format("Connection Error: \"%s\"", "Unauthorized"));
            }
            if (!isAcceptableStatusCodeForMethod(statusCode, method)) {
                logger.error("Error executing " + method.getName() + ", " + method.getStatusLine());
                throw new GatewayException(String.format("Status Code: %s\n%s", statusCode, responseBodyStreamToString(method.getResponseBodyAsStream())));
            }
            response = method instanceof PostMethod && method.getResponseHeader("Location") != null ? method.getResponseHeader("Location").getValue() : responseBodyStreamToString(method.getResponseBodyAsStream());
        } catch (HttpException e) {
            throw new GatewayException(String.format("Connection Error: \"%s\"", "Connection Error"), e);
        } catch (IOException e) {
            throw new GatewayException(String.format("Connection Error: \"%s\"", "Connection Error"), e);
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
        logger.debug("Jama REST response: " + response);
        return response;
    }

    private String responseBodyStreamToString(InputStream inputStream) throws IOException {
        return inputStream == null ? null : IOUtils.toString(inputStream, "UTF-8");
    }

    public boolean isAcceptableStatusCodeForMethod(int statusCode, HttpMethod method) {
        return (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_NO_CONTENT || statusCode == HttpStatus.SC_CREATED);
    }

    public <T> List<T> readList(final TypeReference<T> type, final String json) throws IOException {
        List<T> dataList = new ArrayList<T>();

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = (ArrayNode) mapper.readValue(json, JsonNode.class);
        for (int i = 0; i < arrayNode.size(); i++) {
            dataList.add((T) mapper.readValue(arrayNode.get(i).toString(), type));
        }

        return dataList;
    }

    private String getCompleteUrlForMethod(String url) {
        if (url.startsWith(jamaURL)) {
            return url;
        } else {
            return jamaURL + REST_API_PATH + url;
        }
    }

}
