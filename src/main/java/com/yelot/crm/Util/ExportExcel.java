package com.yelot.crm.Util;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class ExportExcel {
    // 声明一个工作薄
	private HSSFWorkbook workbook;
	// 生成一个表格
	private HSSFSheet sheet;
	private HSSFRow row;
	
	public ExportExcel(){
		workbook = new HSSFWorkbook();
	}
	/**
	 * @param title表格标题名
	 * @param headers表格属性列名数组
	 * @param strs需要显示的数据集合
	 * @param out与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param rows表格行数
	 */
	public void exportExcel(String title, String[] headers,
			List<String> strs, OutputStream out, int rows) {
		sheet = workbook.createSheet(title);
        // 产生表格标题行
        row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        // 遍历集合数据，产生数据行
        int index = 0;
        int cellLengths = 0;
        for(int i = 0; i < rows; i++){//行循环
        	index++;
        	row = sheet.createRow(index);
			for (int j = 0; j < headers.length; j++) {//列循环
				HSSFCell cell = row.createCell(j);
				HSSFRichTextString richString = new HSSFRichTextString(strs.get(cellLengths));
				cell.setCellValue(richString);
				cellLengths++;
			}
        }
        try {
        	workbook.write(out);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

	public void writeExcel(HttpServletResponse response, ByteArrayOutputStream os, String fileName) throws IOException {
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
//	public void exportExcel(OutputStream out, int rows, String title, String rowInfo0, String rowInfo1){
//		sheet = workbook.createSheet(title);
//		//标题栏
//		row = sheet.createRow(0);
//		row.createCell(0).setCellValue("溯源二维码信息");
//		row.createCell(1).setCellValue("基地名称");
//		//二维码循环
//		for(int i = 1; i <= rows; i++){//行循环
//	        	row = sheet.createRow(i);
//	        	row.createCell(0).setCellValue(rowInfo0+i);
//	        	row.createCell(1).setCellValue(rowInfo1);
//
//	        }
//		sheet.autoSizeColumn(0);
//		try {
//			workbook.write(out);
//			out.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
