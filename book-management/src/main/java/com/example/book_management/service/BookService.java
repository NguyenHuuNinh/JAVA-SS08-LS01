package com.example.book_management.service;

import com.example.book_management.dto.BookCreateDTO;
import com.example.book_management.entity.Book;
import com.example.book_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Đường dẫn theo đúng yêu cầu kết quả của bài tập
    private final String UPLOAD_DIR = "src/main/resources/uploads/";

    public Book createBook(BookCreateDTO dto) {
        String coverUrl = null;

        if (dto.getCoverImage() != null && !dto.getCoverImage().isEmpty()) {
            MultipartFile file = dto.getCoverImage();
            try {
                File dir = new File(UPLOAD_DIR);
                if (!dir.exists()) dir.mkdirs();

                String fileName = file.getOriginalFilename(); // Đặt tên nguyên bản theo ảnh mẫu
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Trả về đường dẫn giống hệt kết quả Postman
                coverUrl = filePath.toString().replace("/", "\\");
            } catch (IOException e) {
                throw new RuntimeException("Lỗi lưu file");
            }
        }

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setStock(dto.getStock());
        book.setCoverUrl(coverUrl);

        return bookRepository.save(book);
    }
}