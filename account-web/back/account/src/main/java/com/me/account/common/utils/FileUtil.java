package com.me.account.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {
	
    /** 
     * 把文件从原始路径拷贝到新路径下并删除原来路径下的文件 
     *@param oldPath 旧的文件目录 
     *@param newPath 新的文件目录 
     *@throws Exception 
     */  
	public static void transferFile(String oldPath,String newPath) throws Exception {  
	          
	    int byteread = 0;  
	    File oldFile = new File(oldPath);  
	    FileInputStream fin = null;  
	    FileOutputStream fout = null;  
	    try{  
	        if(oldFile.exists()){  
	            fin = new FileInputStream(oldFile);  
	            fout = new FileOutputStream(newPath);  
	            byte[] buffer = new byte[fin.available()];  
	            while( (byteread = fin.read(buffer)) != -1){  
	                fout.write(buffer,0,byteread);  
	            }  
	            if(fin != null){  
	                fin.close();  
	                delFile(oldFile);  
	            }  
	        }else{  
	            throw new Exception("需要转移的文件不存在!");  
	        }  
	    }catch(Exception e){  
	        e.printStackTrace();  
	        throw e;  
	    }finally{  
	        if(fin != null){  
	            fin.close();  
	        }  
	    }  
	}  


    /** 
     * 删除文件,只支持删除文件,不支持删除目录 
     * @param file 要删除的文件 
     * @throws Exception 
     */  
    public static void delFile(File file) throws Exception {  
        if(!file.exists()) {  
            throw new Exception("文件"+file.getName()+"不存在,请确认!");  
        }  
        if(file.isFile()){  
            if(file.canWrite()){  
                file.delete();  
            }else{  
                throw new Exception("文件"+file.getName()+"只读,无法删除,请手动删除!");  
            }  
        }else{  
            throw new Exception("文件"+file.getName()+"不是一个标准的文件,有可能为目录,请确认!");  
        }  
    }  
    
    public static void main(String argv[]) {  
        try {  
        	FileUtil tz = new FileUtil();  
            tz.transferFile("d:/2.doc","e:/2.doc");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

}
