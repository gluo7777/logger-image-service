package com.marcuschiu.imageservice.controller.ui;

import com.marcuschiu.imageservice.model.ImageModel;
import com.marcuschiu.imageservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @RequestMapping("/")
    public String home(Model model) {
        return "default";
    }


    @RequestMapping("/image")
    public String product(Model model) {
        Iterable<ImageModel> images = imageRepository.findAll();
        model.addAttribute("images", images);
        return "image";
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("image", imageRepository.findOne(id));
        return "show";
    }

//    @RequestMapping("/create")
//    public String create(Model model) {
//        return "create";
//    }
//
//    @RequestMapping("/save")
//    public String save(@RequestParam String extension, @RequestParam String tag) {
//        ImageModel image = new ImageModel();
//        image.setExtension(extension);
//        image.setTag(tag);
//        imageRepository.save(image);
//
//        return "redirect:/show/" + image.getId();
//    }
//
//    @RequestMapping("/delete")
//    public String delete(@RequestParam String id) {
//        ImageModel image = imageRepository.findOne(id);
//        imageRepository.delete(image);
//
//        return "redirect:/image";
//    }
//
//    @RequestMapping("/edit/{id}")
//    public String edit(@PathVariable String id, Model model) {
//        model.addAttribute("image", imageRepository.findOne(id));
//        return "edit";
//    }
//
//    @RequestMapping("/update")
//    public String update(@RequestParam String id, @RequestParam String extension, @RequestParam String tag) {
//        ImageModel image = imageRepository.findOne(id);
//        image.setExtension(extension);
//        image.setTag(tag);
//        imageRepository.save(image);
//
//        return "redirect:/show/" + image.getId();
//    }
}
