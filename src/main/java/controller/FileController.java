package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.ImageService;
import service.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: stone
 * @program: EasyBuy
 * @description:
 * @date: 2021-12-20 18:50:36
 */
@Controller
@RequestMapping("file")
public class FileController {
    private ImageService imageService;

    @Autowired
    public FileController setImageService(ImageService imageService) {
        this.imageService = imageService;
        return this;
    }

    //上传图片
    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        return imageService.uploadImage(file, request);
    }
}
