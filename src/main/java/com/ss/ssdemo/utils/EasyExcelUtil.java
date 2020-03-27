package com.ss.ssdemo.utils;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.ss.ssdemo.pojo.UserPojo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EasyExcelUtil {

    /**
     * 导出Excel文档
     * @param response
     * @throws IOException
     */
    public static void excelExport(HttpServletResponse response,List<? extends BaseRowModel> list,String filename,String sheetName) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String fileName = new String(filename.getBytes(), "iso8859-1") + dateFormat.format(new Date().getTime()) + ".xls";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("application/x-download");
        response.flushBuffer();
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream out = response.getOutputStream();
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS,true);
        Sheet sheet = new Sheet(1,0,UserPojo.class);
        //设置自适应宽度
        sheet.setAutoWidth(Boolean.TRUE);
        sheet.setSheetName(sheetName);
        writer.write(list,sheet);
        writer.finish();
        out.flush();
        response.getOutputStream().close();
        out.close();
    }
}
