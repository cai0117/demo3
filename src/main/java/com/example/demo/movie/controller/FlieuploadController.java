package com.example.demo.movie.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.movie.model.Flieupload;
import com.example.demo.movie.service.FlieuploadService;
import com.example.demo.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSW
 * @since 2022-08-08
 */
@RestController
@RequestMapping("/flieupload")
public class FlieuploadController {
    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
    FlieuploadService flieuploadService;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();//获取文件名

        String type = FileUtil.extName(originalFilename);//获取文件类型

        long size = file.getSize();//获取文件大小
        //存储到磁盘中
        //定义一个文件表示uuid
        String UUID = IdUtil.fastSimpleUUID();
        String suffixPath = UUID + StrUtil.DOT + type;
        File uploadFiles = new File(fileUploadPath + suffixPath);
        //判断配置的文件目录是否存在
        if(!uploadFiles.getParentFile().exists()){
            uploadFiles.getParentFile().mkdirs();//在d盘下直接创建
        }

        //上传文件到磁盘
        file.transferTo(uploadFiles);
        //获取md5
        String md5 = SecureUtil.md5(uploadFiles);
        String url;
        //数据库中查询
        QueryWrapper<Flieupload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5",md5);
        List<Flieupload> flieuploadList = flieuploadService.list(queryWrapper);
        Flieupload getFileByMD5 = flieuploadList.size() == 0 ? null : flieuploadList.get(0);

        if (getFileByMD5 != null){
            url = getFileByMD5.getFileurl();
            //由于文件已经存在，使用删除刚才上传的重复文件
            uploadFiles.delete();
        }else{
            url = "http://localhost:9091/flieupload/" + suffixPath;
        }
        //存到数据库中
        Flieupload flieupload = new Flieupload();
        flieupload.setFilename(originalFilename);
        flieupload.setFiletype(type);
        flieupload.setFilesize(size/1024);
        flieupload.setFileurl(url);
        flieupload.setMd5(md5);
        flieuploadService.save(flieupload);
        return url;
    }


    @GetMapping("/{UUID}")
    public void downLoad(@PathVariable String UUID, HttpServletResponse response) throws IOException{
        //根据文件的唯一标识获取文件
        File file = new File(fileUploadPath + UUID);
        //设置输出格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(UUID,"UTF-8"));
        response.setContentType("application/octet-stream");

        //读取文件的字节流
        os.write(FileUtil.readBytes(file));
        os.flush();
        os.close();
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public CommonResult isDeletelogical(@PathVariable Integer id){
        Flieupload byId = flieuploadService.getById(id);
        byId.setIsDelete(true);
        return CommonResult.success(flieuploadService.updateById(byId));
    }
    @PostMapping("/batch")
    public CommonResult isDeleteBatch(@RequestBody List<Integer> ids){
//        QueryWrapper<Flieupload> qe = new QueryWrapper<>();
//        qe.in("id",ids);
        Collection<Flieupload> flieuploads = flieuploadService.listByIds(ids);
        for (Flieupload flieupload : flieuploads) {
            flieupload.setIsDelete(true);
        }
        return CommonResult.success(flieuploadService.updateBatchById(flieuploads));
    }
}

