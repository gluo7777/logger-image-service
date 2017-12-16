package com.marcuschiu.imageservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@Document(collection = "image")
@XmlRootElement(name = "ImageModel")
public class ImageModel {

    @Id
    String id;
    String extension;
    String imagePath;
    String tag;
}
