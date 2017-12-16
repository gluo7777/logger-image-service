package com.marcuschiu.imageservice.repository;

import com.marcuschiu.imageservice.model.ImageModel;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<ImageModel, String> {
    @Override
    ImageModel findOne(String id);

    @Override
    void delete(ImageModel deleted);
}
