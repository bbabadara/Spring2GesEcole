package com.ecole221.l3dev.gestion.scolarite.controller;

import com.ecole221.l3dev.gestion.scolarite.dto.CreateClasseRequest;
import com.ecole221.l3dev.gestion.scolarite.dto.CreateClasseResponse;
import com.ecole221.l3dev.gestion.scolarite.helper.ClasseHelper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {
    private final ClasseHelper classeHelper ;

    public ClasseController(ClasseHelper classeHelper) {
        this.classeHelper = classeHelper;
    }
    @PostMapping
    public @ResponseBody CreateClasseRequest save (@RequestBody @Valid CreateClasseRequest classeRequest){
//        return classeHelper.save(classeRequest);
       return classeRequest;
    }

}
