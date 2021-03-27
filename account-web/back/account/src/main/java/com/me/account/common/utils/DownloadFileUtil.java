package com.me.account.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
/**
 * 下载文件工具类
 * @author lindr
 *
 */
public class DownloadFileUtil {
	
	/**
	 * 普通下载
	 * @param path
	 * @param response
	 */
	public static  void download(String path, HttpServletResponse response) {
        try  {
            //  path是指欲下载的文件的路径。 
           File file  =   new  File(path);
            //  取得文件名。 
           String filename  =  file.getName();
            //  取得文件的后缀名。 
           String ext  =  filename.substring(filename.lastIndexOf(".")+1).toUpperCase();

            //  以流的形式下载文件。 
           InputStream fis  =   new  BufferedInputStream(new  FileInputStream(path));
           byte [] buffer  =   new  byte[fis.available()];
           fis.read(buffer);
           fis.close();
            //  清空response 
           response.reset();
            //  设置response的Header 
           response.addHeader("Content-Disposition" ,"attachment;filename="+ URLEncoder.encode(filename,"UTF-8"));
           response.addHeader("Content-Length" ,""   +  file.length());
           OutputStream toClient  =   new  BufferedOutputStream(response.getOutputStream());
           response.setContentType("application/octet-stream");
           toClient.write(buffer);
           toClient.flush();
           toClient.close();
       }  catch  (IOException ex) {
            
       }
   }
	/**
	 * 下载本地文件
	 * @param path
	 * @param response
	 * @throws FileNotFoundException
	 */
	public static  void  downloadLocal(String path,HttpServletResponse response)  throws  FileNotFoundException {
		path = path.replace("%20", " ");
        //文件名称 
	    String fileName = path.split("/")[path.split("/").length - 1];
        //读到流中 
        InputStream inStream  =   new  FileInputStream(path); //  文件的存放路径
        //  清空response 
        response.reset();
        response.setContentType("bin");
        try  {
        	response.addHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
	        //  循环取出流中的数据 
	        byte [] b  =   new   byte [ 100 ];
	        int  len;
	        while  ((len  =  inStream.read(b))  >   0 )
	        	response.getOutputStream().write(b,  0 , len);
            inStream.close();
        }  catch  (Exception e) {
           //e.printStackTrace();
        }
	}
	
	 public static  void  downloadNet(HttpServletResponse response)  throws  MalformedURLException {
         //  下载网络文件 
         int  bytesum  =   0 ;
         int  byteread  =   0 ;

         URL url  =   new  URL( " windine.blogdriver.com/logo.gif " );

         try  {
            URLConnection conn  =  url.openConnection();
            InputStream inStream  =  conn.getInputStream();
            FileOutputStream fs  =   new  FileOutputStream( " c:/abc.gif " );

             byte [] buffer  =   new   byte [ 1204 ];
             int  length;
             while  ((byteread  =  inStream.read(buffer))  !=   - 1 ) {
                bytesum  +=  byteread;
                System.out.println(bytesum);
                fs.write(buffer,  0 , byteread);
            }
         }  catch  (FileNotFoundException e) {
        	 e.printStackTrace();
         }  catch  (IOException e) {
            e.printStackTrace();
         }
	 } 
	 
	 
	 //支持在线打开文件的一种方式
     public  static void  downLoad(String filePath, HttpServletResponse response,  boolean  isOnLine)  throws  Exception {
        File f  =   new  File(filePath);
        if  ( ! f.exists()) {
             response.sendError( 404 ,  " File not found! " );
             return ;
        }
        BufferedInputStream br  =   new  BufferedInputStream( new  FileInputStream(f));
        byte [] buf  =   new   byte [ 1024 ];
        int  len  =   0 ;
        response.reset();  //  非常重要 
        if  (isOnLine) {  //  在线打开方式 
            URL u  =   new  URL( " file:/// "   +  filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader( " Content-Disposition " ,  " inline; filename= "   +  f.getName());
             //  文件名应该编码成UTF-8 
        } else {  //  纯下载方式 
            response.setContentType( " application/x-msdownload " );
            response.setHeader( " Content-Disposition " ,  " attachment; filename= "   +  f.getName());
        }
        OutputStream out  =  response.getOutputStream();
        while  ((len  =  br.read(buf))  >   0 )
            out.write(buf,  0 , len);
        br.close();
        out.close();
    } 
}
