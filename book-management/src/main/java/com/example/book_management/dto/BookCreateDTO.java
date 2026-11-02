package com.example.book_management.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class BookCreateDTO {
    private String title;
    private String author;
    private Integer stock;
    private MultipartFile coverImage;
}