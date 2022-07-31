package edu.challengAlkemy.javaSpringBoot.service;

import edu.challengAlkemy.javaSpringBoot.exception.CustomException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private static final String DIRECTORY = "src/main/resources/static/uploads";

    public String imageToString(MultipartFile image) {
        try {
            String imageName = image.getOriginalFilename();

            if (!validateFormatImage(imageName)) {
                throw new CustomException("Invalid format", HttpStatus.BAD_REQUEST);
            }

            Path imagePath = Paths.get(DIRECTORY, imageName).toAbsolutePath();
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            return imageName;
        } catch (Exception e) {
            throw new CustomException("Error saving image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateFormatImage(String imageName) {
        if (imageName.endsWith(".png") || imageName.endsWith(".jpg") || imageName.endsWith(".jpeg")) {
            return true;
        }
        return false;
    }
}
