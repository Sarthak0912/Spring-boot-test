package com.springboot.crudoperation.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {
    Integer statusCode;
    String message;

    List<T> listData;

    int pageNo;
    int pageSize;
    long totalPages;
    boolean isLast;
}
