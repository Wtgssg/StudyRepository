package com.success.oracle.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelExporter<T> {
    //必填参数
    private Workbook workbook;
    private String tableName;
    private List<ColumnConfig<T>> configs;
    private List<T> dataList;
    //可选参数
    private CellStyle headerStyle;
    private CellStyle contentStyle;
    //默认参数格式
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    private Sheet sheet; // 内部状态

    private ExcelExporter() {}

    public static <T> ExcelExporter<T> builder() {
        return new ExcelExporter<>();
    }

    public ExcelExporter<T> workbook(Workbook workbook) {
        this.workbook = workbook;
        return this;
    }

    public ExcelExporter<T> tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public ExcelExporter<T> configs(List<ColumnConfig<T>> configs) {
        this.configs = configs;
        return this;
    }

    public ExcelExporter<T> dataList(List<T> dataList) {
        this.dataList = dataList;
        return this;
    }

    public ExcelExporter<T> headerStyle(CellStyle headerStyle) {
        this.headerStyle = headerStyle;
        return this;
    }

    public ExcelExporter<T> contentStyle(CellStyle contentStyle) {
        this.contentStyle = contentStyle;
        return this;
    }

    public ExcelExporter<T> dateStyle(SimpleDateFormat simpleDateFormat){
        this.simpleDateFormat = simpleDateFormat;
        return this;
    }

    public ExcelExporter<T> bigdecimalStyle(DecimalFormat decimalFormat){
        this.decimalFormat = decimalFormat;
        return this;
    }
    public void export(HttpServletResponse response) throws IOException {
        // 1. 参数校验
        if (workbook == null) this.workbook = new XSSFWorkbook(); // 如果没传，内部默认创建
        if (configs == null || configs.isEmpty()) throw new IllegalArgumentException("列配置不能为空");
        if (dataList == null) dataList = new ArrayList<>();

        // 2. 初始化 Sheet
        this.sheet = workbook.createSheet(tableName);

        // 3. 确定最终使用的样式（如果没传，则生成默认样式）
        CellStyle finalHeaderStyle = (this.headerStyle != null) ? this.headerStyle : null;
        CellStyle finalContentStyle = (this.contentStyle != null) ? this.contentStyle : null;

        // 4. 写入表头
        writeHeader(finalHeaderStyle);

        // 5. 写入数据行
        writeDataRows(finalContentStyle);

        // 6. 自动调整列宽
        // for (int i = 0; i < configs.size(); i++) sheet.autoSizeColumn(i);

        // 7. 输出到 response
        writeToResponse(response);

        // 8. 资源清理
        workbook.close();
    }


    private void writeHeader(CellStyle style) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < configs.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(configs.get(i).getTitle());
            cell.setCellStyle(style);
            // 设置列宽
            sheet.setColumnWidth(i, configs.get(i).getWidth() * 256);
        }
    }

    private void writeDataRows(CellStyle style) {
        int rowIndex = 1;
        for (T data : dataList) {
            Row row = sheet.createRow(rowIndex++);
            for (int i = 0; i < configs.size(); i++) {
                Cell cell = row.createCell(i);
                Object value = configs.get(i).getMethod().apply(data);

                if (value == null) {
                        cell.setCellValue("");
                    } else if (value instanceof BigDecimal){
                        cell.setCellValue(decimalFormat.format(((BigDecimal) value).setScale(2, RoundingMode.HALF_UP)));
                    } else if (value instanceof Date) {
                        cell.setCellValue(simpleDateFormat.format(value));
                    } else {
                        cell.setCellValue(value.toString());
                    }
                    if (contentStyle != null){
                        cell.setCellStyle(contentStyle);
                    }
                cell.setCellStyle(style);
            }
        }
    }

    private void writeToResponse(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(tableName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
            out.flush();
        }
    }

    // --- 默认样式生成方法 ---
    private CellStyle createDefaultHeaderStyle() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private CellStyle createDefaultContentStyle() {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
}
