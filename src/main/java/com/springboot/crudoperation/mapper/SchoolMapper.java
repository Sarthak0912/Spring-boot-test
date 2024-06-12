package com.springboot.crudoperation.mapper;

import com.springboot.crudoperation.entity.ClassRoom;
import com.springboot.crudoperation.entity.School;
import com.springboot.crudoperation.model.ClassRoomDto;
import com.springboot.crudoperation.model.SchoolDto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolMapper {

    public static List<SchoolDto> mapToSchoolDtos(List<School> schools){

        return schools.stream().map(school->SchoolDto.builder().name(school.getName()).id(school.getId()).address(school.getAddress()).dressCodeColors(school.getDressCodeColors()).createdBy(school.getCreatedBy()).updatedBy(school.getUpdatedBy()).createdDate(school.getCreatedDate()).updatedDate(school.getUpdatedDate()).classRooms(convertEntityToClassRoomDto(school.getClassRoomList())).build()).collect(Collectors.toList());
    }

    public static SchoolDto mapToSchoolDto(School school){

        return SchoolDto.builder().name(school.getName()).id(school.getId()).address(school.getAddress()).dressCodeColors(school.getDressCodeColors()).createdBy(school.getCreatedBy()).updatedBy(school.getUpdatedBy()).createdDate(school.getCreatedDate()).updatedDate(school.getUpdatedDate()).classRooms(convertEntityToClassRoomDto(school.getClassRoomList())).build();
    }


    public  static School maptoSchool(SchoolDto schoolDto, String user){
        School school=new School();
        school.setName(schoolDto.getName());
        school.setAddress(schoolDto.getAddress());
        school.setDressCodeColors(schoolDto.getDressCodeColors());
        school.setCreatedDate(Date.valueOf(LocalDate.now()));
        school.setCreatedBy(user);
        school.setClassRoomList(convertClassRoomDtoToEntity(schoolDto.getClassRooms()));
        return school;
    }

    public static List<ClassRoom> convertClassRoomDtoToEntity(List<ClassRoomDto> dtos){

        return dtos.stream().map(dto->ClassRoom.builder().floor(dto.getFloor()).grade(dto.getGrade()).name(dto.getName()).strength(dto.getStrength()).build()).collect(Collectors.toList());
    }

    public static List<ClassRoomDto> convertEntityToClassRoomDto(List<ClassRoom> dtos){

        return dtos.stream().map(dto->ClassRoomDto.builder().floor(dto.getFloor()).grade(dto.getGrade()).name(dto.getName()).strength(dto.getStrength()).build()).collect(Collectors.toList());
    }
}
