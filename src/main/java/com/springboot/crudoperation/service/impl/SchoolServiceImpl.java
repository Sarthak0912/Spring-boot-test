package com.springboot.crudoperation.service.impl;


import com.springboot.crudoperation.entity.ClassRoom;
import com.springboot.crudoperation.entity.School;
import com.springboot.crudoperation.exception.DataNotFoundException;
import com.springboot.crudoperation.mapper.SchoolMapper;
import com.springboot.crudoperation.model.PageResponse;
import com.springboot.crudoperation.model.SchoolDto;
import com.springboot.crudoperation.repository.SchoolRepository;
import com.springboot.crudoperation.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

    @Override
    public SchoolDto saveSchool(SchoolDto schoolDto) {
       School school = SchoolMapper.maptoSchool(schoolDto,"Admin");
        schoolRepository.save(school);
        schoolDto.setId(school.getId());
        return schoolDto;
    }

    @Transactional
    @Override
    public SchoolDto updateSchool(SchoolDto schoolDto)  {
        Optional<School> optionalData= schoolRepository.findByIdAndIsDeleted(schoolDto.getId(),0);
        if(optionalData.isPresent()){
            School school=optionalData.get();
            if(!schoolDto.getName().isEmpty()) {
                school.setName(schoolDto.getName());
            }
            school.setUpdatedBy("Admin");
            school.setUpdatedDate(Date.valueOf(LocalDate.now()));
            schoolRepository.save(school);
            return SchoolMapper.mapToSchoolDto(school);
        }
        else {
            throw new DataNotFoundException("Record not found");
        }
    }

    @Override
    public SchoolDto findSchoolById(int schoolId) {
        Optional<School> optionalData = schoolRepository.findByIdAndIsDeleted(schoolId, 0);

        if (optionalData.isPresent()) {
            School school = optionalData.get();
            return SchoolMapper.mapToSchoolDto(school);

        }
        else{
            throw new DataNotFoundException("Record not found");
        }

    }

    @Override
    public void deleteSchoolById(int schoolId) {
        Optional<School> optionalData=schoolRepository.findByIdAndIsDeleted((long)schoolId,0);
        if(optionalData.isPresent()){
            School school = optionalData.get();
            school.setIsDeleted(1);
            schoolRepository.save(school);
        }
        else{
            throw new DataNotFoundException("Record not found!");
        }
    }
    @Override
    public List<SchoolDto> findSchoolBySearchText(String searchText){
        return SchoolMapper.mapToSchoolDtos(schoolRepository.findSchoolBySearchText(searchText));
    }

    @Override
    public PageResponse<?> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable=PageRequest.of(pageNo,pageSize,sort);
        Page<School> page=schoolRepository.findAll(pageable);
        List<School> schools=page.getContent();
        List<SchoolDto> schoolDtos=SchoolMapper.mapToSchoolDtos(schools);
        PageResponse<SchoolDto> pageResponse=new PageResponse<>();
        pageResponse.setPageNo(page.getNumber());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setLast(page.isLast());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setListData(schoolDtos);

        return pageResponse;
    }

    @Override
    public PageResponse<?> searchAll(String searchText, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        pageSize= pageSize==0?1:pageSize;
        Pageable pageable=PageRequest.of(pageNo,pageSize,sort);
        Page<School> page= searchText==null||searchText.isEmpty()?schoolRepository.findAll(pageable):schoolRepository.findSchoolBySearchTextWithPagination(searchText,pageable);
        List<School> schools=page.getContent();
        List<SchoolDto> schoolDtos=SchoolMapper.mapToSchoolDtos(schools);
        PageResponse<SchoolDto> pageResponse=new PageResponse<>();
        pageResponse.setPageNo(page.getNumber());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setLast(page.isLast());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setListData(schoolDtos);

        return pageResponse;
    }


}
