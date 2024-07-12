package plus.guiyun.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import plus.guiyun.app.common.code.domain.AjaxResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用接口
 */
@RestController
public class CommonController {

    // 指定上传文件保存的路径
    @Value("${file.upload-dir}")
    private String uploadDir;


    @PostMapping("/upload")
    public AjaxResult<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 获取上传文件的原始文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件后缀名
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            // 获取当天年月日
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String today = sdf.format(new Date());

            // 构建上传文件的保存路径
            String dirPath = uploadDir + today + "/";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 生成新的文件名，使用时间戳和原始文件名
            String newFileName = System.currentTimeMillis()+ "." + suffix;
            // 构建上传文件的保存路径，包括上传文件的新文件名和文件夹路径
            String filePath = dirPath + newFileName;
            // 保存上传文件
            File dest = new File(filePath);
            file.transferTo(dest);
            return AjaxResult.success(today + "/"+newFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("上传失败");
        }
    }
}
