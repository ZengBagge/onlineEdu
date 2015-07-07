package com.ws.common;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class commonUtil {
	

	public static boolean contain(String[] arr, String targetValue) {
	    for (String s : arr) {
	        if (s.equals(targetValue)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
	 * 读取excel2003文件，返回数组
	 * @param path
	 * @return
	 */
	public static String[][]  excelTopicForm(String path){
		
		 try {   
	            // 打开文件   
	            Workbook book = Workbook.getWorkbook(new File(path));   
	            // 获得第一个表的工作对象，“0”表示第一个表   
	            Sheet sheet = book.getSheet(0);   
	            int rows = sheet.getRows();   
	            int cols = sheet.getColumns();   
	            String[][] topicResult=new String[rows][cols];
	            // 得到第一列，第一行的单元格（0，0）   
	            for (int i = 0; i < rows; i++) {   
	                for (int j = 0; j < cols; j++) {   
	                    Cell cell = sheet.getCell(j, i);   
	                    String result = cell.getContents();    
	                    topicResult[i][j]=result;
	                    System.out.print("  " +  topicResult[i][j] + "  ");   
	                }   
	                System.out.println();   
	            }   
	            book.close();   
	        	return topicResult;   
	        } catch (IOException e) {   
	            // TODO Auto-generated catch block   
	            e.printStackTrace();   
	            return null;
	        } catch (BiffException e) {   
	            // TODO Auto-generated catch block   
	            e.printStackTrace();   
	            return null;
	        }
	
	}
	/**
	 * 打印函数
	 * @param object
	 */
	public static void p(Object object){
		System.out.println(object);
	}
	
	public static void removeDuplicate(List list) {  
	      HashSet h = new HashSet(list);  
	      list.clear();  
	      list.addAll(h);  
	}  

	/**
	 * 去除字符串前count位
	 * @param origin
	 * @param count
	 * @return
	 */
	public static String truncateHeadString(String origin, int count) {
        if (origin == null || origin.length() < count) {
            return null;
        }
         
        char[] arr = origin.toCharArray();
        char[] ret = new char[arr.length - count];
        for (int i = 0; i < ret.length; i ++) {
            ret[i] = arr[i + count];
        }
         
        return String.copyValueOf(ret);
    }
	
	/**
	 * 随机生成字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length){  
        Random random = new Random();  
          
        StringBuffer sb = new StringBuffer();  
          
        for(int i = 0; i < length; ++i){  
            int number = random.nextInt(3);  
            long result = 0;  
              
            switch(number){  
            case 0:  
                result = Math.round(Math.random() * 25 + 65);  
                sb.append(String.valueOf((char)result));  
                break;  
            case 1:  
                result = Math.round(Math.random() * 25 + 97);  
                sb.append(String.valueOf((char)result));  
                break;  
            case 2:  
                sb.append(String.valueOf(new Random().nextInt(10)));  
                break;  
            }  
        }  
        return sb.toString();     
    }  
	
	
	/***
     * 去掉字符串前后的空间，中间的空格保留
     * @param str
     * @return
     */
    public static String trimInnerSpaceStr(String str){
         str = str.trim(); //Returns a string whose value is this string, with any leading(前导) and trailing(尾随) whitespace removed
        while(str.startsWith(" ")){
        str = str.substring(1,str.length()).trim();
        }
        while(str.endsWith(" ")){
        str = str.substring(0,str.length()-1).trim();
        }
        return str;
    }
	/**
	 * md5加密
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getMD5Str(String str) {  
        MessageDigest messageDigest = null;  
  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");    
            messageDigest.reset();  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
            System.exit(-1);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString();  
    }
}
