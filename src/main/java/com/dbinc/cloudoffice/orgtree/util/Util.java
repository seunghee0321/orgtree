package com.dbinc.cloudoffice.orgtree.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Util
{
  public static Map<String, Object> getPagingForSQL(int page, int rows, int total, int records) 
  {
    Map<String, Object> returnMap = new HashMap<String, Object>();

    if(records > 0) 
    {
        if(rows > 0) 
            total = (int)(Math.ceil((double)records / (double)rows));
    }

    if(page > total) 
    {
        page = total;
    }

    if(rows > -1) 
    {
        returnMap.put("page", page);
        returnMap.put("total", total);
        returnMap.put("offset", rows * page - rows);
        returnMap.put("fetchNext", rows);
    }

    return returnMap;
  }
  
  public static String getCurDate()
  {
    SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMdd");
    Date time = new Date();
    return format1.format(time);
  }
  
  public static String getCurYear()
  {
    SimpleDateFormat format1 = new SimpleDateFormat ("yyyy");
    Date time = new Date();
    return format1.format(time);
  }
  
  public static String null2Space (String str)
  {
    if (str == null || str.trim().isEmpty())
      return "";
    else
      return str;
  }
  
  public static String null2Str (String str, String newStr)
  {
    if (str == null || str.trim().isEmpty())
      return newStr;
    else
      return str;
  }
  
  public static String getGuid()
  {
    return UUID.randomUUID().toString();
  }
  
  public static String getUniqueBlobFileName(boolean isCurrentTimeAdd) 
  {
    if (isCurrentTimeAdd) 
    {
        return System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
    } 
    else 
    {
        return UUID.randomUUID().toString();
    }
}
  
  public static String getExtension(String originName) 
  {
    int lastIndexOf = originName.lastIndexOf(".");
    
    if (lastIndexOf == -1) 
    {
        return "";
    }
    
    return originName.substring(lastIndexOf);
  }
}
