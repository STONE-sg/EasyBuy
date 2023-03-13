package service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: stone
 * @program: EasyBuy
 * @description:
 * @date: 2021-12-20 18:51:09
 */
@Service
public class ImageService {
    public Result uploadImage(MultipartFile file, HttpServletRequest request) {
        Result result = new Result();
        //重命名图片
        String picTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String picType = file.getContentType();
        String fileName;
        if ("image/png".equals(picType)) {
            fileName = picTime + ".png";
        } else if ("image/gif".equals(picType)) {
            fileName = picTime + ".gif";
        } else {
            fileName = picTime + ".jpg";
        }

        //创建存放图片的目录
        String savePath = request.getServletContext().getRealPath("upload");
        File saveFile = new File(savePath);
        if (!saveFile.exists() && !saveFile.isDirectory()) {
            System.out.println(savePath + " 目录不存在，需要创建 ");
            if (!saveFile.mkdirs()) {
                result.setStatus(-1);
                result.setMsg("创建图片存放目录失败");
                return result;
            }
        }

        //创建图片
        File targetFile = new File(savePath, fileName);
        if (!targetFile.exists()) {
            try {
                if (!targetFile.createNewFile()) {
                    result.setStatus(-2);
                    result.setMsg("创建图片文件失败");
                    return result;
                }
            } catch (IOException e) {
                e.printStackTrace();
                result.setStatus(-3);
                result.setMsg(e.getMessage());
                return result;
            }
        }

        try {
            //复制文件
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            result.setStatus(-4);
            result.setMsg(e.getMessage());
            return result;
        }

        String url = "http://" + request.getServerName() + ":" + request.getLocalPort();
        String fileUrl = url + "/upload/" + fileName;
        result.setStatus(0);
        result.setMsg("上传文件成功");
        result.setData(fileUrl);
        return result;
    }
}
