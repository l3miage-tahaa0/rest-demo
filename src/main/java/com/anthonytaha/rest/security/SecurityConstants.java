package com.anthonytaha.rest.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {


    public static final long EXPIRATION_TIME=864000000;
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_STRING="Authorization";
    public static final String SIGN_UP_URL="/users";
    public static final String SIGN_IN_URL="/users/login";

    //public static String TOKEN_SECRET="d194cdd3cbe6666e3834cda943817b31c206c40a7fa14f7ce03cd405b7c4a20970a0650f8b75ed617913f380d209ea4f6fb9731ca921dde76139e9a6fda62bba2622c103c3b803745753b64e61624ca756eb65284a62c9cf682048bbadd0d05ba597fedc512191ee7c45b53a86ae5f3b4190613bb1d480e6fea2efb9fbb34d0ca195440629c143770ae4965fd6590c9ccb1fd8d92af47abf5e1b530f12ff802f364b7e13cae9e9f3f5705171af452546abb300d9ea3b3bd8bdf3bbbc99fd2eb12d5f036fafe6742849133cac63a7d4565d91c23643b66edbc76b1ed76490205f8f5f29333448ee66ec3409485f1f445382bc32bf2e23b1549cc2bb4c7c2daacc";

}
