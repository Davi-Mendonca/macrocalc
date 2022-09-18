package com.macrocalc.model;

import lombok.Data;

@Data
public class ResponseBody {

    private String max_results;
    private String total_results;
    private String page_number;
    private Food food;
}
