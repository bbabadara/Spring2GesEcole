package com.ecole221.l3dev.gestion.scolarite.controller;

import com.ecole221.l3dev.gestion.scolarite.dto.CreateClasseRequest;
import com.ecole221.l3dev.gestion.scolarite.dto.CreateClasseResponse;
import com.ecole221.l3dev.gestion.scolarite.dto.UpdateClasseRequest;
import com.ecole221.l3dev.gestion.scolarite.helper.ClasseHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {
    private final ClasseHelper classeHelper ;

    public ClasseController(ClasseHelper classeHelper) {
        this.classeHelper = classeHelper;
    }
    @PostMapping
    public @ResponseBody CreateClasseResponse save (@RequestBody @Valid CreateClasseRequest classeRequest){
        return classeHelper.save(classeRequest);
    }
    @GetMapping
    public @ResponseBody List<CreateClasseResponse> allClasses (){
        return classeHelper.findAll();
    }
    @GetMapping("/{id}")
    public @ResponseBody CreateClasseResponse getById(@PathVariable long id){
        return classeHelper.findById(id);
    }
    @PutMapping
    public @ResponseBody CreateClasseResponse update(@RequestBody UpdateClasseRequest updateClasseRequest){
        return classeHelper.update(updateClasseRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable long id){
        return classeHelper.delete(id);
    }


}
