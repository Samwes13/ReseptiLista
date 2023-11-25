package com.example.ReseptiLista.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;





@Controller public class UploadController {
/*
    //public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    
    @Autowired
    private ImageRepository imageRepository;


    @PostMapping("/upload")
    public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            // Handle empty file case
            return "redirect:uploadimage";
        }

        Image image = new Image();
        image.setTiedostonimi(file.getOriginalFilename());
        image.setMimeType(file.getContentType());
        image.setData(file.getBytes());

        // Assuming you have an ImageRepository to save the Image entity
        imageRepository.save(image);

        model.addAttribute("msg", "Uploaded image: " + file.getOriginalFilename());
        return "imageupload/index";
    }*/

}