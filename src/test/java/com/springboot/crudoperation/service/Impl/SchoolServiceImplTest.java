package com.springboot.crudoperation.service.Impl;

import com.springboot.crudoperation.entity.School;
import com.springboot.crudoperation.exception.DataNotFoundException;
import com.springboot.crudoperation.model.SchoolDto;
import com.springboot.crudoperation.repository.SchoolRepository;
import com.springboot.crudoperation.service.impl.SchoolServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
 class SchoolServiceImplTest {
    @Mock
    SchoolRepository schoolRepository;
    @InjectMocks
   SchoolServiceImpl schoolService;
    @Test
    void saveSchool(){
       when(schoolRepository.save(ArgumentMatchers.any(School.class))).thenReturn(School.builder().id(1L).name("Test").build());
        SchoolDto schoolDto=schoolService.saveSchool(SchoolDto.builder().name("Test").build());
        assertEquals("Test",schoolDto.getName());
        assertEquals(1L,schoolDto.getId());
    }

    @Test
   void findByIdPassTest(){
        when(schoolRepository.findByIdAndIsDeleted(anyLong(),anyInt())).thenReturn(Optional.of(School.builder().id(1L).name("Test").build()));
        SchoolDto schoolDto=schoolService.findSchoolById(1);
        assertEquals("Test",schoolDto.getName());
        assertEquals(1L,schoolDto.getId());
    }

    @Test
    void findByIdFailTest()  {
        when(schoolRepository.findByIdAndIsDeleted(anyLong(),anyInt())).thenReturn(Optional.empty());
        try {
            schoolService.findSchoolById(1);
            assertTrue(true);
        }
        catch (DataNotFoundException e){
            e.printStackTrace();
        }
    }

}
