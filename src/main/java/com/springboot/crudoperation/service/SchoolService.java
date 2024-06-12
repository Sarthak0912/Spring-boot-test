package com.springboot.crudoperation.service;


import com.springboot.crudoperation.entity.School;
import com.springboot.crudoperation.model.PageResponse;
import com.springboot.crudoperation.model.SchoolDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SchoolService {

    SchoolDto saveSchool(SchoolDto schoolDto);

    SchoolDto updateSchool(SchoolDto schoolDto) throws Exception;

    SchoolDto findSchoolById(int schoolId);

    void deleteSchoolById(int schoolId);
    public List<SchoolDto> findSchoolBySearchText(String searchText);

    public PageResponse<?> findAll(int pageNo, int pageSize, String sortBy, String sortDir);
    public PageResponse<?> searchAll(String searchText,int pageNo, int pageSize, String sortBy, String sortDir);

}
