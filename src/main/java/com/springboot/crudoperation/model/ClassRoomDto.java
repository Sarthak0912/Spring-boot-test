package com.springboot.crudoperation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClassRoomDto {

    long id;
    String name;
    int grade;
    int floor;
    int strength;
}
