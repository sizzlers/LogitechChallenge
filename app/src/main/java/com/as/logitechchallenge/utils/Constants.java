package com.as.logitechchallenge.utils;

/**
 * Created by suresh on 16-01-2016.
 */
public class Constants {


        public static final long TIMEOUT = 30;
        public static final String BUSINESS = "LOGITECHCHALLANGE";
        public static final String PREFERENCE_BASE_URL = "BASE_URL";
        //Service URLs
        public static class ServiceAPIURLs{
           // https://s3.amazonaws.com/harmony-recruit/devices.json
            public static String BASE_URL = "https://s3.amazonaws.com/harmony-recruit/";

            public static final String URL_FETCH_LIST =  "/devices.json";

            public static String getBaseURL(){
                return BASE_URL;
            }
        }
        public static class UserInterface{
            public static final int TOAST_DURATION = 15000;
        }

}
