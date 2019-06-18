package com.itc.app.network;

public class NetworkConstant {

    // Error Codes
    public static final int RESPONSE_CODE_SUCCESS = 200;        // Success
    public static final int RESPONSE_CODE_UNEXPECTED = 444;    // No Response
    public static final int RESPONSE_CODE_SERVER_ERROR = 500; // Unknown Error
    public static final int RESPONSE_CODE_UNAUTHORIZED = 401; // Unauthorized
    public static final int RESPONSE_CODE_NETWORK_ERROR = 99;
    public static final int RESPONSE_CODE_DEACTIVATED = 403;  // Forbidden
    public static final int RESPONSE_CODE_NOT_FOUND = 404;
    public static final int RESPONSE_CODE_UN_PROCESSABLE_ENTITY = 429;
    public static final int RESPONSE_CODE_UNPROCESSABLE_ENTITY = 422; // Unprocessable Entity
    public static final int RESPONSE_CODE_BAD_REQUEST = 400; // Bad Request
    public static final int RESPONSE_CODE_CONFLICT = 409; // Conflict
    public static final int RESPONSE_CODE_LARGE_FILE = 500;

    public static final int RESPONSE_CODE_NO_DATA = 101; // Locally defined : No data


    // Error Messages
    public static final String ERROR_MESSAGE_UNEXPECTED = "Unexpected Error occurred!";
    public static final String ERROR_MESSAGE_SERVER = "Server Error Occurred!";
    public static final String ERROR_MESSAGE_NETWORK = "Please check your internet connection.";
    public static final String ERROR_MESSAGE_DEACTIVATED = "You have deactivated!";
    public static final String ERROR_MESSAGE_NOT_FOUND = "No Response Found!";
    public static final String ERROR_MESSAGE_UNAUTHORIZED = "Authantication Failed! Please Login Again!";
    public static final String ERROR_MESSAGE_BAD_REQUEST = "Unable to process the request!";
    public static final String ERROR_MESSAGE_LARGE_FILE = "File is too large!";
    public static final String ERROR_MESSAGE_SERVER_NOT_RESPONDING = "Server not responding!";


    // Response status
    public static final String OK = "OK";
    public static final String UNEXPECTED = "UNEXPECTED";
    public static final String SERVER_ERROR = "SERVER_ERROR";
    public static final String NETWORK_ERROR = "NETWORK_ERROR";
    public static final String DEACTIVATED = "DEACTIVATED";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String UNPROCESSABLE_ENTITY = "UNPROCESSABLE_ENTITY";
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String CONFLICT = "CONFLICT";
    public static final String LARGE_FILE = "LARGE_FILE";
}
