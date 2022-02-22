package com.vognev.textilewebproject.util;

import java.util.ArrayList;
import java.util.List;

/**
 * textilewebproject_3  05/11/2021-18:48
 */
public final class ConvertJsonToList {

    public static List<String> convertArrayToList(String[]json){

        boolean con=false;

        StringBuilder str=new StringBuilder();

        List<String> list=new ArrayList<>();

        for(String s:json){

            s=s.replace('[',' ');

            if(s.contains("{")){
                con=true;
            }
            if(s.contains("}")||s.contains("}]")){

                if(s.indexOf("]")>0) {

                    s=s.substring(0,s.indexOf("]"));
                }

                con=false;
                str.append(s);
                list.add(str.toString());
                str.setLength(0);
            }
            if(con){

                str.append(s);
                str.append(',');
            }
        }
        return list;
    }
}
