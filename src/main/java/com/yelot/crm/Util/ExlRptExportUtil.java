package com.yelot.crm.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * @author xyzloveabc
 * @2017年9月13日
 */
public class ExlRptExportUtil {
	/**
	 * 分页时每一页的大小
	 */
	public static Integer REPORT_PAGE_SIZE = 300000;

	/**
	 * @Title makeReport 导出execl gird 形式
	 * @Description
	 * @param title
	 *            表格名
	 * @param titleList
	 *            表格模板
	 * @param rptList
	 *            表格数据
	 * @return XSSFWorkbook
	 */
	public static Workbook makeReport(String title,
			List<ExlRptCellType> titleList, List<Object[]> rptList) {

		final double total = rptList.size(); // 数据量
		final int size = REPORT_PAGE_SIZE; // 页面大小
		// 不分页时使用 Excel2007的最大行数-10
		// 分页时使用 size值
		final int pageSize = total > size ? size : Double.valueOf(
				Math.pow(2, 20) - 10).intValue();
		final int pageCount = Double.valueOf(Math.ceil(total / pageSize))
				.intValue(); // 页面数量

		/* 初始化Workbook，每隔1000条刷新一次流 */
		SXSSFWorkbook workbook = new SXSSFWorkbook(1000);

		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setFontName("宋体");

		Font font1 = workbook.createFont();
		font1.setFontHeightInPoints((short) 12);
		font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font1.setFontName("宋体");

		Font font16 = workbook.createFont();
		font16.setFontHeightInPoints((short) 20);
		font16.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font16.setFontName("华文楷体");

		// 设置单元格居中
		CellStyle styleCenter = workbook.createCellStyle();
		styleCenter.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleCenter.setAlignment(CellStyle.ALIGN_CENTER);
		styleCenter.setFont(font16);

		CellStyle styleLeft = workbook.createCellStyle();
		styleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleLeft.setAlignment(CellStyle.ALIGN_LEFT);
		styleLeft.setFont(font);

		CellStyle styleRight = workbook.createCellStyle();
		styleRight.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleRight.setAlignment(CellStyle.ALIGN_RIGHT);
		styleRight.setFont(font);
		DataFormat format = workbook.createDataFormat();
		styleRight.setDataFormat(format.getFormat("#,##0.00"));

		CellStyle styleWeight = workbook.createCellStyle();
		styleWeight.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleWeight.setAlignment(CellStyle.ALIGN_RIGHT);
		styleWeight.setFont(font);
		DataFormat format1 = workbook.createDataFormat();
		styleWeight.setDataFormat(format1.getFormat("0.0000"));

		CellStyle styleCount = workbook.createCellStyle();
		styleCount.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleCount.setAlignment(CellStyle.ALIGN_RIGHT);
		styleCount.setFont(font);

		String sheetName = "";
		// 只有1页时，不显示数字
		if (pageCount == 1)
			sheetName = title;
		for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {

			// 分页的表格名称：报表【1】【2】【3】...【n】
			if (pageCount > 1)
				sheetName = String.format(pageIndex == 0 ? "%2$s【%1$d】"
						: "【%1$d】", pageIndex + 1, title);

			// 创建工作表
			Sheet sheet = workbook.createSheet(sheetName); // 赋值表格名字

			int currentRow = 0; // 当前行
			Row row = null;

			/* ===================生成标题==================== */
			row = sheet.createRow(currentRow++);
			Cell cellTmp = row.createCell(0);
			cellTmp.setCellValue(title);

			// 合并单元格
			CellRangeAddress region = new CellRangeAddress(0, 0, 0,
					titleList.size() - 1);
			sheet.addMergedRegion(region);
			cellTmp.setCellStyle(styleCenter);

			/* =============生成表格列标题 titlelist============= */
			row = sheet.createRow(currentRow++);
			int colIndex = 0;
			for (ExlRptCellType b : titleList) {

				if (colIndex == 0)
					sheet.setColumnWidth(colIndex,
							Short.parseShort(b.getWeight()) * 125);
				else
					sheet.setColumnWidth(colIndex,
							Short.parseShort(b.getWeight()) * 512);

				font1 = workbook.createFont();
				font1.setFontHeightInPoints(Short.parseShort(b.getHeight()));
				font1.setFontName(b.getFont());

				CellStyle styleFirst = workbook.createCellStyle();
				styleFirst.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				styleFirst.setAlignment(CellStyle.ALIGN_LEFT);
				styleFirst.setFont(font1);

				Cell cellx = row.createCell(colIndex);
				cellx.setCellValue(b.getDesc());
				cellx.setCellStyle(styleFirst);
				colIndex++;
			}

			/* ===================生成报表数据================== */
			double endPos = Math.min((pageIndex + 1) * pageSize, total);
			for (int pos = pageIndex * pageSize; pos < endPos; pos++) {
				// 行数据
				Object[] o = rptList.get(pos);
				// 行对象
				row = sheet.createRow(currentRow++);
				// 输出列数据
				for (int k = 0; k < o.length; k++) {
					Cell cellFirstX = row.createCell(k);

					if (null == o[k]) {
						cellFirstX.setCellValue("");
					} else if (o[k] instanceof BigDecimal) {
						cellFirstX
								.setCellValue(Double.valueOf(o[k].toString()));
						if (titleList.get(k).getDesc().contains("重量"))
							cellFirstX.setCellStyle(styleWeight);
						else if (titleList.get(k).getDesc().contains("数量"))
							cellFirstX.setCellStyle(styleCount);
						else if (titleList.get(k).getDesc().contains("份额"))
							cellFirstX.setCellStyle(styleWeight);
						else
							cellFirstX.setCellStyle(styleRight);
					} else {
						cellFirstX.setCellValue(o[k].toString());
						cellFirstX.setCellStyle(styleLeft);
					}

				}
			}
		}

		return workbook;
	}
	
	public static SXSSFWorkbook makeRepairOrderInfo(String title,
			List<ExlRptCellType> titleList, List<Object[]> rptList) {
		/* 初始化 */
		SXSSFWorkbook workbook = new SXSSFWorkbook(1000);

		/* 初始化流文件 */
		Sheet sheet = workbook.createSheet(title); // 赋值表格名字
		Row row = null;

		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setFontName("宋体");

		Font font1 = workbook.createFont();
		font1.setFontHeightInPoints((short) 12);
		font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font1.setFontName("宋体");
		
		Font font2 = workbook.createFont();
		font2.setFontHeightInPoints((short) 12);
		font2.setFontName("宋体");

		Font font16 = workbook.createFont();
		font16.setFontHeightInPoints((short) 20);
		font16.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font16.setFontName("华文楷体");

		// 设置单元格居中
		CellStyle styleCenter = workbook.createCellStyle();
		styleCenter.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleCenter.setAlignment(CellStyle.ALIGN_CENTER);
		styleCenter.setFont(font16);

		CellStyle styleLeft = workbook.createCellStyle();
		styleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleLeft.setAlignment(CellStyle.ALIGN_LEFT);
		styleLeft.setFont(font);
		
		CellStyle styleRight = workbook.createCellStyle();
		styleRight.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleRight.setAlignment(CellStyle.ALIGN_RIGHT);
		styleRight.setFont(font);
		DataFormat format = workbook.createDataFormat();
		styleRight.setDataFormat(format.getFormat("0.00"));
		
		CellStyle styleWeight = workbook.createCellStyle();
		styleWeight.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleWeight.setAlignment(CellStyle.ALIGN_RIGHT);
		styleWeight.setFont(font);
		DataFormat format1 = workbook.createDataFormat();
		styleWeight.setDataFormat(format1.getFormat("0.0000"));

		CellStyle styleCount = workbook.createCellStyle();
		styleCount.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleCount.setAlignment(CellStyle.ALIGN_RIGHT);
		styleCount.setFont(font);
		
		/* 生成标题 */
		row = sheet.createRow(0);
		Cell cellTmp = row.createCell(0);
		cellTmp.setCellValue(title);

		// 合并单元格
		CellRangeAddress region = new CellRangeAddress(0, 0, 0,
				titleList.size() - 1);
		sheet.addMergedRegion(region);
		cellTmp.setCellStyle(styleCenter);

		row = sheet.createRow(1);
		
		CellStyle styleFirst0 = workbook.createCellStyle();
		styleFirst0.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleFirst0.setAlignment(CellStyle.ALIGN_CENTER);
		styleFirst0.setFont(font2);
		
		Cell cellx0 = row.createCell(0);
		cellx0.setCellValue(titleList.get(0).getDesc());
		cellx0.setCellStyle(styleFirst0);
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
		
		Cell cellx1 = row.createCell(1);
		cellx1.setCellValue("维修单信息");
		cellx1.setCellStyle(styleFirst0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 12));
		
		Cell cellx2 = row.createCell(13);
		cellx2.setCellValue("货品信息");
		cellx2.setCellStyle(styleFirst0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 13, 15));
		
		Cell cellx3 = row.createCell(16);
		cellx3.setCellValue("维修信息");
		cellx3.setCellStyle(styleFirst0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 16, 18));
		
		Cell cellx4 = row.createCell(19);
		cellx4.setCellValue("付款信息");
		cellx4.setCellStyle(styleFirst0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 19, 26));
		
		Cell cellx5 = row.createCell(27);
		cellx5.setCellValue("支出信息");
		cellx5.setCellStyle(styleFirst0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 27, 33));
		
		Cell cellx6 = row.createCell(34);
		cellx6.setCellValue("客户信息");
		cellx6.setCellStyle(styleFirst0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 34, 44));
		
		Cell cellx7 = row.createCell(45);
		cellx7.setCellValue("来源信息");
		cellx7.setCellStyle(styleFirst0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 45, 47));
		
		Cell cellx8 = row.createCell(48);
		cellx8.setCellValue("其他");
		cellx8.setCellStyle(styleFirst0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 48, 50));
		
		row = sheet.createRow(2);
		
		int i = 0;
		for (ExlRptCellType b : titleList) {

			if(i==0)
				sheet.setColumnWidth(i, Short.parseShort(b.getWeight()) * 212);
			else
				sheet.setColumnWidth(i, Short.parseShort(b.getWeight()) * 212);

			font1 = workbook.createFont();
			font1.setFontHeightInPoints(Short.parseShort(b.getHeight()));
			font1.setFontName(b.getFont());

			CellStyle styleFirst = workbook.createCellStyle();
			styleFirst.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			styleFirst.setAlignment(CellStyle.ALIGN_LEFT);
			styleFirst.setFont(font1);

			Cell cellx = row.createCell(i);
			cellx.setCellValue(b.getDesc());
			cellx.setCellStyle(styleFirst);
			
			i++;
		}
		
		int j = 3;
		for (Object[] o : rptList) {
			row = sheet.createRow(j);
			for (int k = 0; k < o.length; k++) {
				Cell cellFirstX = row.createCell(k);
				
				if(null == o[k]){
					cellFirstX.setCellValue("");
				}else if(o[k] instanceof BigDecimal){
					cellFirstX.setCellValue(Double.valueOf(o[k].toString()));
					if(titleList.get(k).getDesc().contains("重量"))
						cellFirstX.setCellStyle(styleWeight);
					else if(titleList.get(k).getDesc().contains("数量"))
						cellFirstX.setCellStyle(styleCount);
					else
						cellFirstX.setCellStyle(styleRight);
					
				}else {
					cellFirstX.setCellValue(o[k].toString());
					cellFirstX.setCellStyle(styleLeft);
				}
				
			}
			j++;
		}
		return workbook;
	}
	
	/**
	 * @param titleStringList
	 * @return
	 */
	public static List<ExlRptCellType> getTitleList(List<String> titleStringList) {
		
		List<ExlRptCellType> titleList = new ArrayList<ExlRptCellType>();
		
		for (String string : titleStringList) {
			ExlRptCellType ct = new ExlRptCellType();
			ct.setDesc(string);
			ct.setFont("宋体");
			ct.setHeight("12");
			ct.setWeight("10");
			titleList.add(ct);
		}
		
		return titleList;
	}

}
