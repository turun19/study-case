package com.study.studycase.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "barang")
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Barang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_barang")
    private String namaBarang;

    @Column(name = "stock_barang")
    private Integer stockBarang;

    @Column(name = "nomor_seri")
    @GeneratedValue
    private UUID nomorSeri = UUID.randomUUID();

    @Type(type = "jsonb")
    @Column(name = "additional_info", columnDefinition = "jsonb")
    private AdditionalInfo additionalInfo;

    @Column(name = "gambar")
    private String gambar;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

}
