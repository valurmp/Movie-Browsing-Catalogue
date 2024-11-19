package com.team18.MBC.Services;

import com.team18.MBC.Repositories.ImageRepository;
import com.team18.MBC.core.Image;
import com.team18.MBC.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(MultipartFile file, User user) throws IOException {
        Optional<Image> existingImageOpt = imageRepository.findByUserId(user.getID());
        Image image;

        if (existingImageOpt.isPresent()) {
            image = existingImageOpt.get();
        } else {
            image = new Image();
            image.setUserId(user.getID());
        }
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setData(file.getBytes());
        image.setUserId(user.getID());

        return imageRepository.save(image);
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

   
}
