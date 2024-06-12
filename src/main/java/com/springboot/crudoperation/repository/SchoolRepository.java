package com.springboot.crudoperation.repository;

import com.springboot.crudoperation.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School,Long> {

 //custom jpa
    Optional<School> findByIdAndIsDeleted(long Id,int IsDeleted);

 //custom query
    @Query("select s from school s where lower(s.name) like lower(concat('%',:searchType,'%')) Or lower(s.address) like lower(concat('%',:searchType,'%'))")
    List<School> findSchoolBySearchText(@Param("searchType") String searchText);

    @Query("select s from school s where lower(s.name) like lower(concat('%',:searchType,'%')) Or lower(s.address) like lower(concat('%',:searchType,'%'))")
    Page<School> findSchoolBySearchTextWithPagination(@Param("searchType") String searchText, Pageable pageable);

    Page<School> findAll(Pageable pageable);
}
