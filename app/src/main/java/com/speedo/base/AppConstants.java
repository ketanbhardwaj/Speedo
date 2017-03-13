package com.speedo.base;

import com.speedo.BuildConfig;

/**
 * Created by Ketan on 4/10/16.
 */

public class AppConstants {

    public static final String APP_PACKAGE_NAME = BuildConfig.APPLICATION_ID;
    public static final String APP_VERSION_NAME = BuildConfig.VERSION_NAME;
    public static final int APP_VERSION_CODE = BuildConfig.VERSION_CODE;
    public static final String SMS_ID = "MM-WAYCHA";

    public static final String SUCCESS = "1";
    public static final String ERROR = "0";

    public static final String BASE_IMAGE_URL = "http://www.uengage.in/images/addo/";
    public static final String BASE_IMAGE_URL_NEW = "http://www.uengage.in/images/";
    public static final String LOGO_IMAGE_URL = "logos/";

    public static final String TARGET_ADD_GUEST = "ag";
    public static final String TARGET_NOTIFICATIONS = "nt";
    public static final String TARGET_RESERVATIONS = "rs";
    public static final String TARGET_FEEDBACK = "fd";
    public static final String TARGET_CAMPAIGN = "cmp";
    public static final String TARGET_PROFILE = "pr";
    public static final String TARGET_OFFERS = "ofr";

    public static final int CAT_WALK_IN = 1;
    public static final int CAT_RESERVATION = 2;
    public static final int CAT_ENQUIRY = 3;

    public static final int TABLE_SQUARE = 1;
    public static final int TABLE_RECTANGLE = 2;
    public static final int TABLE_CIRCLE = 3;

    public static final String CATEGGORY_FOOD = "Food & Beverages";

    public static final int PERMISSION_REQUEST_ID = 100;
    public static final int PAX_REQUEST_ID = 101;
    public static final int EDIT_TABLES_REQUEST_ID = 102;
    public static final int EDIT_OFFER_REQUEST_ID = 103;
    public static final int TABLE_ALLOCATION_REQUEST_ID = 104;
    public static final int TEMPLATE_REQUEST_ID = 105;
    public static final int FEEDBACK_REQUEST_ID = 106;
    public static final int MULTIPLE_TABLE_ALLOCATION_REQUEST_ID = 107;
    public static final int GALLERY_REQUEST_ID = 108;
    public static final int SELECT_ASSIGNEE_REQUEST_ID = 109;

    public static final String PAX_VALUE_KEY = "pax_value";

    public static final int TABLE_LIST_MODE_ALLOCATION = 1;
    public static final int TABLE_LIST_MODE_UPDATION = 2;
    public static final int TABLE_LIST_MODE_DELETION = 3;

    public static final int TIMELINE_ALLOCATE_TABLE = 1;
    public static final int TIMELINE_ADD_CONTACT = 2;
    public static final int TIMELINE_OFFER = 3;
    public static final int TIMELINE_FEEDBACK = 4;

    public static final int REMOVE = 0;
    public static final int ADD = 1;
    public static final int UPDATE = 2;
    public static final int NONE = 3;

    public static final String FRAGMENT_PAGE_CODE_KEY = "page_code";
    public static final String HOME_PAGE_TARGET = "home_page_in";
    public static final String SIGN_IN_PAGE_TARGET = "sign_in_page";
    public static final String JOIN_US_PAGE_TARGET = "join_page";
    public static final String OTP_PAGE_TARGET = "otp_page";

    public static final int POST_CARD = 0;
    public static final int PROFILE_HEADER_CARD = 1;
    public static final int AD_CARD = 2;
    public static final int ORDER_CARD = 3;
    public static final int CART_ORDER_CARD = 4;
    public static final int SIMPLE_TEXT = 5;

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    // Targets
    public static final String TARGET_KEY = "t";
    public static final String TARGET_LOGIN = "lg";
    public static final String TARGET_SIGNUP = "sg";
}
