package com.example.resizeimage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllChangeTools {

    public String changeLines(String lines, int size) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new StringReader(lines));
        String line = "";
        while ((line = br.readLine()) != null) {
            int idx = line.indexOf("![image]");
            if (idx > -1) {
                result.append(line, 0, idx);
                result.append(applySize(reUrl(line), size)).append("\n");
                continue;
            }
            result.append(line).append("\n");
        }
        return result.substring(0, result.length()-1);
    }

    public String reUrl(String url){

        String re = "(ftp|http|https):\\/\\/(\\w+:{0,1}\\w*@)?(\\S+)(:[0-9]+)?(\\/|\\/([\\w#!:.?+=&%@!\\-\\/]))?[^)]";
        Pattern p = Pattern.compile(re);
        Matcher m = p.matcher(url);
        if (!m.find()) {
            return "";
        }
        return m.group();
    }

    public String applySize(String url, int size) {
        String strSize = Integer.toString(size);
        return "<img src=" + url +
                " width=\"" + strSize +
                "%\" height=\"" + strSize +
                "%\"/><br>";
    }
}
