package com.study.studycase.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdditionalInfo {
    @JsonProperty
    private String tempatProduksi;
    @JsonProperty
    private Integer harga;
}
