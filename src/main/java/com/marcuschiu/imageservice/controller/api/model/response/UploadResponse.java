package com.marcuschiu.imageservice.controller.api.model.response;

import lombok.Data;

@Data
public class UploadResponse {

    Boolean success;
    String imageURL;
    String imageID;
    String errorMessage;
}
