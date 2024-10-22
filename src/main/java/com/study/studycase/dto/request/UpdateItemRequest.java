package com.study.studycase.dto.request;

import com.study.studycase.entity.AdditionalInfo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateItemRequest {
    @NotBlank(message = "Must not be empty")
    private String itemName;
    @NotNull(message = "Must not be empty")
    private Integer stockItem;
    private AdditionalInfo additionalInfo;
    @NotBlank(message = "Must not be empty")
    private String updateBy;
}
