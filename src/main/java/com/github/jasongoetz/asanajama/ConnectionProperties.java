package com.github.jasongoetz.asanajama;

public class ConnectionProperties {

    private static final String jama_url = "http://localhost:8080/contour";
    private static final String jama_username = "jgoetz";
    private static final String jama_password = "password";

    private static final String asana_api_key = "77H6ILO3.8RMTGiDJECdEpVpF3ZcMSb8";

    public static String getJamaURL() {
        return ConnectionProperties.jama_url;
    }

    public static String getJamaUsername() {
        return jama_username;
    }

    public static String getJamaPassword() {
        return jama_password;
    }

    public static String getAsanaAPIKey() {
        return asana_api_key;
    }
}
