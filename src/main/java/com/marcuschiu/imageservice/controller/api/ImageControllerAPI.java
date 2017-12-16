package com.marcuschiu.imageservice.controller.api;

import com.marcuschiu.imageservice.controller.api.model.request.UploadRequest;
import com.marcuschiu.imageservice.controller.api.model.response.UploadResponse;
import com.marcuschiu.imageservice.model.ImageModel;
import com.marcuschiu.imageservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("api")
public class ImageControllerAPI {

    @Value("${image.repository.public.directory}")
    private String imageRepositoryPublicDirectory;

    @Value("${image.repository.domain.name}")
    private String imageRepositoryDomainName;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping(value = "/get", produces = "application/json")
    public ImageModel getEmployeeInJSON() {
        ImageModel imageModel = new ImageModel();
        imageModel.setId("id");
        imageModel.setExtension(".jpg");
        imageModel.setTag("hello");
        return imageModel;
    }

    @GetMapping(value = "/get.xml", produces = "application/xml")
    public ImageModel getEmployeeInXML() {
        ImageModel imageModel = new ImageModel();
        imageModel.setId("id-xml");
        imageModel.setExtension(".xml");
        imageModel.setTag("hello.xml");
        return imageModel;
    }

    @PostMapping(value = "/upload", produces = "application/json")
    public UploadResponse uploadImage(@RequestBody UploadRequest uploadRequest) {
        BufferedImage image = null;
        ImageModel imageModel = null;

        try {
            String uri = uploadRequest.getImageURL();
            String extension = uri.substring(uri.lastIndexOf(".") + 1);

            URL url = new URL(uri);
            image = ImageIO.read(url);

            imageModel = new ImageModel();
            imageModel.setExtension(extension);
            imageRepository.save(imageModel);

            String destinationFilePath = imageRepositoryPublicDirectory + imageModel.getId() + "." + extension;
            File outputFile = new File(destinationFilePath);
            ImageIO.write(image, extension, outputFile);

            imageModel.setImagePath(destinationFilePath);
            imageRepository.save(imageModel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UploadResponse uploadResponse = new UploadResponse();
        uploadResponse.setSuccess(true);
        uploadResponse.setImageID(imageModel.getId());
        uploadResponse.setImageURL("http://" + imageRepositoryDomainName + imageModel.getImagePath());
        uploadResponse.setErrorMessage("error message");

        return uploadResponse;
    }
}
