package com.fl.util;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class CommonHeader {
    private String randomString;//随机字符串
    private long time;//时间戳
    private MD5Project md5Project;


    public String getRandomString() {
        this.randomString=randomString();
        return randomString;
    }

    public String getMD5(Object parame) {
        md5Project = paramsMD5(randomString, String.valueOf(time), parame);
        return md5Project.getMd5();
    }

    public long getTime() {
        this.time=System.currentTimeMillis();//时间戳
        return time;
    }

    public MD5Project getMd5Project() {
        return md5Project;
    }

    //随机字符串8位
    public String randomString()
    {
        String[] s={"1","2","3","4","5","6","7","8","9","0","A","B","C","D","E","F","G","H","I","J","K","L","M",
                "N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int size=s.length;
        String string="";
        Random random=new Random();
        for(int i=0;i<8;i++)
        {
            string+=s[random.nextInt(size)];
        }
        return string;
    }

    //MD5加密
    public MD5Project paramsMD5(String nonce, String timestamp,String params[])
    {
        MD5Project md5Project=new MD5Project();

        String footerString=null;

        for(int i=0;i<params.length;i++)
        {
            footerString=footerString+params[i];
        }



        String headstring="nonce="+nonce+"&timestamp="+timestamp+(footerString.length()!=0?"&":"");
        md5Project.setSource(headstring+footerString);
        md5Project.setMd5(md5(headstring+footerString));
        return  md5Project;
    }

    public MD5Project paramsMD5(String nonce, String timestamp,Object params)
    {
        MD5Project md5Project=new MD5Project();

        String footerString="";

        if(params instanceof String)
            footerString=(String) params;
        else
        {
            Class c=params.getClass();
            //反射获取参数名
            Field[] fields=c.getDeclaredFields();
            //参数个数
            int paramNameSize=fields.length;

            //参数名转为String
            for(int i=0;i<paramNameSize;i++)
            {
                String paramValue=null;//属性值
                String paramName=null;//属性名

                fields[i].setAccessible(true);
                paramName=fields[i].getName();//获取属性名
                try {
                    paramValue=String.valueOf(fields[i].get(params));//获取实现值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                footerString=footerString+paramName+"="+paramValue;
                if(i!=(paramNameSize-1))
                    footerString+="&";
            }
        }

        System.out.println("MD5签名:"+footerString);

        String headstring="nonce="+nonce+"&timestamp="+timestamp+(footerString.length()!=0?"&":"");
        md5Project.setSource(headstring+footerString);
        md5Project.setMd5(md5(headstring+footerString));
        return  md5Project;
    }


    public  String md5(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
