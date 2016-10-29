package com.a2a.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class QueryTokenizer {

    private static final Pattern REGEXP = Pattern.compile("\"([^\"]*)\"|(\\S+)");
    

    public static List<String> tokens(String query) {
        if (!StringUtils.hasText(query)) {
            return Collections.emptyList();
        }
        
        Matcher m = REGEXP.matcher(query);
        if (!m.find()) {
            return Collections.singletonList(query);
        }
        
        List<String> list = new ArrayList<>();
        do {
            if (m.group(1) != null) {
                list.add(m.group(1));
            } else {
                list.add(m.group(2));
            }
        } while (m.find());       
        return list;
    }

}
