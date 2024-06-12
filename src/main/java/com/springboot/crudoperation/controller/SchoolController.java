package com.springboot.crudoperation.controller;

import com.springboot.crudoperation.model.ResponseDto;
import com.springboot.crudoperation.model.SchoolDto;
import com.springboot.crudoperation.service.SchoolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Tag(name="School Controller", description = "This is used to perform crud operations on school data")
@RequestMapping(value = "/school")
@RestController
public class SchoolController {

    private final SchoolService schoolService;
    @Autowired
   SchoolController (SchoolService schoolService){
        this.schoolService=schoolService;
    }


    @GetMapping(value = "{schoolId}")
    public ResponseEntity<ResponseDto<Object>> getSchoolUsingRespDto(@PathVariable int schoolId) {

        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.OK.value()).message("successfully fetched!").data(schoolService.findSchoolById(schoolId)).build(),HttpStatus.OK);
    }

    @GetMapping

    public ResponseEntity<Object> getValueUsingRP(@RequestParam("SID") int schoolId){

        return new ResponseEntity<>(schoolService.findSchoolById(schoolId), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<ResponseDto<Object>> postValue(@RequestBody SchoolDto schoolDto){

        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.OK.value()).message("Created Successfully").data(schoolService.saveSchool(schoolDto)).build(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> putValue(@RequestBody SchoolDto schoolDto) throws Exception {

        return new ResponseEntity<>(schoolService.updateSchool(schoolDto), HttpStatus.ACCEPTED);
    }
    @DeleteMapping (value = "{schoolId}")
    public ResponseEntity<ResponseDto<Object>> deleteValueUsingPV(@PathVariable int schoolId){

        schoolService.deleteSchoolById(schoolId);
        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.OK.value()).message("Record Deleted!").build(),HttpStatus.OK);
    }

    @GetMapping(value = "/data")
    public ResponseEntity<ResponseDto<Object>> searchSchoolsBySearch(@RequestParam("SearchText") String searchText){

       return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.FOUND.value()).message("Records fetched!").listData(Collections.singletonList(schoolService.findSchoolBySearchText(searchText))).build(),HttpStatus.FOUND);
   }

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseDto<Object>> getSchoolsBySort(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize")int pageSize,@RequestParam("sortBy")String sortBy,@RequestParam("sortDir")String sortDir){

        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.FOUND.value()).message("Records fetched").data(schoolService.findAll(pageNo,pageSize,sortBy,sortDir)).build(),HttpStatus.FOUND);
    }

    @GetMapping(value = "/byAll")
    public ResponseEntity<ResponseDto<Object>> getSchoolsBySearchAndSort(@RequestParam("SearchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("pageSize")int pageSize,@RequestParam("sortBy")String sortBy,@RequestParam("sortDir")String sortDir){

        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.FOUND.value()).message("Records fetched").data(schoolService.searchAll(searchText,pageNo,pageSize,sortBy,sortDir)).build(),HttpStatus.FOUND);
    }
}

