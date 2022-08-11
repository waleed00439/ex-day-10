package com.example.exday10.controller;

import com.example.exday10.modle.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@RequestMapping("/Api/v1/employee")
public class MyUserController {
    ArrayList<Myuser> arr = new ArrayList<>();
    @GetMapping
    public ResponseEntity getEmployee(){
        return ResponseEntity.status(200).body(arr);
    }
    @PostMapping
    public ResponseEntity addEmployee(@RequestBody @Valid Myuser employees,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api (message,400));
        }
        if (employees.getEmploymentYear()<1982 && employees.getEmploymentYear()>2022){
            return ResponseEntity.status(400).body(new Api("Wrong year",400));
        }
        employees.setOnLeave(false);
        arr.add(employees);
        return ResponseEntity.status(201).body(new Api ("New employee add",201));
    }
    @PutMapping("/{index}")
    public ResponseEntity putEmployee(@PathVariable @Valid int index,Myuser employees , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(message, 400));

        }
        if (index >= arr.size()){
            return ResponseEntity.status(400).body(new Api("invalid text", 400));
        }
        arr.remove(index);
        return ResponseEntity.status(201).body(new Api ("update employee",201));
    }
    @DeleteMapping("/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index){
        if (index>=arr.size()){
            return ResponseEntity.status(400).body(new Api("invalid index", 400));
        }
        return ResponseEntity.status(201).body(new Api ("update employee",201));
    }

}
