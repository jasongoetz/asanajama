package com.github.jasongoetz.asanajama.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasongoetz.asanajama.exception.GatewayException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Array;

import static org.junit.Assert.fail;

public class JsonUtil {

    public static String getJsonStringFromObject(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(object);
        }
        catch (Exception exc) {
            fail("Error processing JSON");
        }
        return jsonString;
    }

    /**
     * Get the JSONArray contained within the "data" parameter of a rest json response.
     */
    public static JSONArray getResponseDataJsonArray(String restResponse) throws JSONException {
        if(StringUtils.isEmpty(restResponse) || !restResponse.contains("data") || restResponse.contains("data\":null")){
            return null;
        }
        JSONObject jsonObject = new JSONObject(restResponse);

        // "data" is a JSONObject if it only contains 1 item and otherwise is a JSONArray
        JSONArray dataArray;
        try {
            dataArray = new JSONArray();
            dataArray.put(jsonObject.getJSONObject("data"));
        }
        catch (JSONException e) {
            dataArray = jsonObject.getJSONArray("data");
        }
        return dataArray;
    }

    public static JSONObject getResponseDataJsonObject(String restResponse) throws JSONException {
        if(StringUtils.isEmpty(restResponse) || !restResponse.contains("data") || restResponse.contains("data\":null")){
            return null;
        }
        JSONObject jsonObject = new JSONObject(restResponse);
        return jsonObject.getJSONObject("data");
    }

    public static <T> T getDomainObject(String jsonResponse, Class clz) throws GatewayException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject responseDataJsonObject = null;
        try {
            responseDataJsonObject = getResponseDataJsonObject(jsonResponse);
        } catch (JSONException e) {
            throw new GatewayException("Could not parse json", e);
        }
        try {
            return (T)mapper.readValue(responseDataJsonObject.toString(), clz);
        } catch (IOException e) {
            throw new GatewayException("Could not parse json", e);
        }
    }

    public static <T> T[] getDomainObjects(String jsonResponse, Class clz) throws GatewayException {
        T[] domainObjects;
        ObjectMapper mapper = new ObjectMapper();
        try {
            JSONArray array = getResponseDataJsonArray(jsonResponse);
            if(array == null) {
                return null;
            }
            domainObjects = (T[]) Array.newInstance(clz, array.length());
            for(int i=0;i<array.length();i++) {
                JSONObject objectJson = array.getJSONObject(i);
                try {
                    domainObjects[i] = (T)mapper.readValue(objectJson.toString(), clz);
                } catch (IOException e) {
                    throw new GatewayException("Could not parse json", e);
                }
            }
        }
        catch (JSONException e){
            return null;
        }
        return domainObjects;
    }

}
