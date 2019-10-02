package com.me.account.common.utils;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



/**
 * 导出Excel工具类
 * @author lindr
 */
public class ExportExcelUtil {
	private Connection con = null;

	private WritableSheet sheet = null;

	private WritableWorkbook book;
	
	private String excelName = "";

	/**
	 * 取得一个连接对象
	 * 
	 * @param Connection
	 */
	public void getCon(Connection con) throws Exception {
		this.con = con;
	}
	
	/**
	 * 创建Excel文件
	 * */
	public void creatExcelFile(String path,String excelName) throws Exception{
		this.excelName = excelName;
		//打开文件 
		WritableWorkbook book= Workbook.createWorkbook(new File(path)); 
		//生成名为“第一页”的工作表，参数0表示这是第一页 
		WritableSheet sheet=book.createSheet(excelName,0); 
		//在Label对象的构造子中指名单元格位置是第一列第一行(0,0) //以及单元格内容为test 
		book.write(); 
		book.close();
	}

	/**
	 * 载入Excel指定文件
	 */
	public void openExcel(String path) throws Exception {// Excel获得文件

		Workbook wb = Workbook.getWorkbook(new File(path)); // 打开一个文件的副本，并且指定数据写回到原文件
		book = Workbook.createWorkbook(new File(path), wb); // 添加一个工作表
		sheet = book.getSheet(0);
	}

	/**
	 * 载入数据库的数据添加到Excel中
	 * 返回导入Excel的记录数
	 */
	public int importData(String sql) throws Exception{
		System.out.println("开始查询数据库的数据添加到Excel中....");
		PreparedStatement pstm = null;
		ResultSet rs = null;
		pstm = con.prepareStatement(sql);
		rs = pstm.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCnt = rsmd.getColumnCount();
		sheet.mergeCells(0, 0,colCnt-1, 0);
		Label firstLabel = new Label(0,0,excelName);
		//设置第1行字体大小和字体位置
	    WritableFont font1 = new  WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
        WritableCellFormat cellFormat1=new WritableCellFormat(font1);
        cellFormat1.setAlignment(Alignment.CENTRE);
        cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
        cellFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);
        firstLabel.setCellFormat(cellFormat1);
		sheet.addCell(firstLabel);
		sheet.setRowView(0, 450);
		//设置第2行字体大小和字体位置
	    WritableFont font2 = new  WritableFont(WritableFont.ARIAL,8,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
        WritableCellFormat cellFormat2=new WritableCellFormat(font2);
        cellFormat2.setAlignment(Alignment.CENTRE);
        cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
        cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
		int [] columnWith = new int [colCnt];//列宽数组
		for(int k=1;k<colCnt+1;k++){
			String title = rsmd.getColumnName(k);
			Label labelTitle = new Label(k-1,1,title);
			byte[] bstrLength = title.getBytes();   //中文字符算两个字节
			int colWith = bstrLength.length+2;
			sheet.setColumnView(k-1, colWith); //设置列宽为标题长度
			columnWith[k-1] = colWith;
	        labelTitle.setCellFormat(cellFormat2);
			sheet.addCell(labelTitle);
		}
		sheet.setRowView(1, 350);
		//设置正文字体大小和字体位置
	    //WritableFont font3 = new  WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
        WritableCellFormat cellFormat3=new WritableCellFormat();
        cellFormat3.setAlignment(Alignment.CENTRE);
        cellFormat3.setVerticalAlignment(VerticalAlignment.CENTRE);
        cellFormat3.setBorder(Border.ALL, BorderLineStyle.THIN);
		int i = 2;
		while (rs.next()) {
			for (int j = 1; j < colCnt+1; j++) {
				String colName = rsmd.getColumnName(j);
				String colValue = rs.getString(colName);
				byte[] bstrLength = colValue.getBytes();   //中文字符算两个字节
				int colWith = bstrLength.length+2;
				if(colWith>columnWith[j-1]){//取最大长度设为列宽
					sheet.setColumnView(j-1, colWith);
					columnWith[j-1]=colWith;
				}
				sheet.setRowView(i, 350);
				Label label = new Label(j-1, i, colValue);
				label.setCellFormat(cellFormat3);
				sheet.addCell(label);
			}
			i++;
		}
		book.write();
		book.close();
		rs.close();
		rs=null;
		pstm.close();
		pstm=null;
		System.out.println("数据库的数据成功添加到Excel中....");
		return i;
	}
}
