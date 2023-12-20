package com.spiritcoderz.prophiusassessmentprepup.commons.config;


public class AppConstants {
    public static final String AUTHORIZATION_HEADER_VALUE = "Authorization";
    public static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
    public static final String USER_REGISTER_SUCCESS_MESSAGE = "success";
    public static final String USER_REGISTER_FAILURE_MESSAGE = "problem encountered while registering user : ";
    public static final String USER_UPDATE_FAILURE_MESSAGE = "problem updating user details : ";
    public static final String USER_UPDATE_SUCCESS_MESSAGE = "success";
    public static final String USER_RETRIEVE_FAILURE_MESSAGE = "error occurred while retrieving user details : ";
    public static final String USER_RETRIEVE_SUCCESS_MESSAGE = "success";
    public static final String EMAIL_ALREADY_EXISTS = "email already used";
    public static final String FOLLOW_REQUEST_SUCCESS = "success";
    public static final String FOLLOW_REQUEST_FAILURE = "your follow invite cannot be sent now. Try later";
    public static final String DELETE_REQUEST_SUCCESS = "success";
    public static final String DELETE_REQUEST_FAILURE = "failure to unregister";
    public static final String POSTLIKE_REQUEST_SUCCESS = "success";
    public static final String POSTLIKE_CREATION_FAILURE = "post cannot be saved at the moment. Try again";
    public static final String COMMENT_CREATION_SUCCESS = "success";
    public static final String COMMENT_CREATION_FAILURE = "comment cannot be saved at the moment. Try again";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static final String POST_CREATION_SUCCESS = "success";
    public static final String POST_CREATION_FAILURE = "post not created at the moment";
    public static final String POST_RETRIEVAL_SUCCESS = "success";

    public static final String POST_RETRIEVAL_FAILURE = "failed to retrieve post";
    public static final String IMAGE_NAME_INVALID = "please give your image a name";
    public static final String USER_IMAGE_UPLOAD_SUCCESSFUL = "success";
    public static final String USER_IMAGE_UPLOAD_FAILURE = "image upload unsuccessful";
    public static final String FILE_STORAGE_PATH = "https://aws://s3/prophius-buckects/";
    public static final String COMMENT_UPDATE_FAILURE = "comment update unsuccessful";
    public static final String COMMENT_UPDATE_SUCCESS = "success";

    public static String USER_CACHE_KEY_PRIMARY = "#email";
    public static String USER_CACHE_KEY_SECONDARY = "#id";

    public static String USER_LIST_KEY = "#users";
    public static String CACHE_NAME = "users";
}
