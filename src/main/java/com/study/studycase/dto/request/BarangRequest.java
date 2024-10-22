package com.study.studycase.dto.request;

import com.study.studycase.entity.AdditionalInfo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BarangRequest {
    @NotBlank(message = "must not be empty")
    private String namaBarang;
    @NotNull(message = "must be not empty")
    private Integer stockBarang;
    private AdditionalInfo additionalInfo;
    @NotBlank(message = "must be not empty")
    private String createdBy;
}
