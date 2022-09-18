package com.macrocalc.model;

import lombok.Data;

@Data
public class AccessResponse {
     private String access_token;
     private String expires_in;
     private String token_type;
     private String scope;
}
