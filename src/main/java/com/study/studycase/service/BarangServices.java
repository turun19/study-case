package com.study.studycase.service;

import com.study.studycase.dto.request.BarangRequest;
import com.study.studycase.dto.request.UpdateItemRequest;
import com.study.studycase.entity.AdditionalInfo;
import com.study.studycase.entity.Barang;
import com.study.studycase.exception.ExceptionImage;
import com.study.studycase.exception.InvalidFormatException;
import com.study.studycase.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BarangServices {
    @Autowired
    private BarangRepository barangRepo;

    @Autowired
    private ValidationService validationService;

    private static final String UPLOAD_DIR = "D:/Fathur Files/Semester 8/study-case/upload";
    private static final String BASE_URL = "http://localhost:8080/upload/";

    public void createItem(BarangRequest barangRequest, MultipartFile file) {
        validationService.validate(barangRequest);
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = file.getOriginalFilename();
            if (fileName != null && !(fileName.endsWith(".png") || fileName.endsWith(".jpg"))) {
                throw new InvalidFormatException("Invalid file format");
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Barang barang = new Barang();
            barang.setNamaBarang(barangRequest.getNamaBarang());
            barang.setStockBarang(barangRequest.getStockBarang());
            barang.setAdditionalInfo(barangRequest.getAdditionalInfo());

            // Build the accessible URL for the file
            String fileUrl = BASE_URL + fileName;
            barang.setGambar(fileUrl);
            barang.setCreatedBy(barangRequest.getCreatedBy());

            barangRepo.save(barang);

        } catch (IOException e) {
            throw new ExceptionImage("Gagal Upload: " + e.getMessage());
        }
    }

    public List<Barang> getItems() {
        return barangRepo.findAll();
    }

    public void updateItem(Long id, UpdateItemRequest updateItemRequest) {
        validationService.validate(updateItemRequest);
        Barang barang = barangRepo.findById(id).orElseThrow();

        barang.setNamaBarang(updateItemRequest.getItemName());
        barang.setStockBarang(updateItemRequest.getStockItem());
        barang.setAdditionalInfo(updateItemRequest.getAdditionalInfo());
        barang.setUpdatedBy(updateItemRequest.getUpdateBy());
        barang.setUpdatedAt(LocalDateTime.now());
        barangRepo.save(barang);

    }

    public void deleteItem(Long id) {
        barangRepo.deleteById(id);
    }

    public Barang getItemById(Long id) {
        return barangRepo.findById(id).get();
    }
}
