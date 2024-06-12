package com.springboot.crudoperation.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import java.util.List;

@Entity(name = "school")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class School extends BaseEntity {

    String name;
    String address;
    String dressCodeColors;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
            @JoinColumn(name = "school_Id")
    List<ClassRoom> classRoomList;

}
