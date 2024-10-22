package com.study.studycase.controller;

import com.study.studycase.dto.request.BarangRequest;
import com.study.studycase.dto.request.UpdateItemRequest;
import com.study.studycase.dto.response.BaseResponse;
import com.study.studycase.service.BarangServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/item")
public class BarangController {
    @Autowired
    private BarangServices barangServices;

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getItem(){
        return ResponseEntity.ok(BaseResponse.success(barangServices.getItems(), "Success Get List Items"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> getItemById(@PathVariable Long id){
        return ResponseEntity.ok(BaseResponse.success(barangServices.getItemById(id), "Success Get Item ById"));
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<?>> createItem( @Valid @RequestPart(value = "request") BarangRequest request,
                                                      @RequestPart(value = "file", required = false) MultipartFile file) {
        barangServices.createItem(request, file);
        return ResponseEntity.ok().body(BaseResponse.success(null, "Successfully created item"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<?>> updateItem(@PathVariable Long id, @Valid @RequestBody UpdateItemRequest request){
        barangServices.updateItem(id, request);
        return ResponseEntity.ok(BaseResponse.success(null, "Updated Successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> deleteItem(@PathVariable Long id){
        barangServices.deleteItem(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Deleted Successfully"));
    }
}
