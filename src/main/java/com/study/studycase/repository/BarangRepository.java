package com.study.studycase.repository;

import com.study.studycase.entity.Barang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarangRepository extends JpaRepository<Barang, Long> {
}
