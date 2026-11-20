package com.success.oracle.util;

import lombok.Data;
import lombok.Getter;
import org.apache.poi.ss.format.CellNumberStringMod;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;

import java.util.function.Function;


public class ColumnConfig<T> {
    @Getter
    private String title;
    @Getter
    private int width;
    @Getter
    private CellStyle cellStyle;

    private final Function<T, Object> method;

    public ColumnConfig(String title, int width,CellStyle cellStyle, Function<T, Object> method) {
        this.title = title;
        this.width = width;
        this.cellStyle = cellStyle;
        this.method = method;
    }

    public ColumnConfig(String title, int width, Function<T, Object> method) {
        this.title = title;
        this.width = width;
        this.method = method;
    }

    public Object getValue(T entity) {
        if (entity == null || method == null) {
            return null;
        }
        return method.apply(entity);
    }


}
