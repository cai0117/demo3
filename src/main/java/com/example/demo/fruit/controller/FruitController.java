package com.example.demo.fruit.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.fruit.model.Fruit;
import com.example.demo.fruit.service.FruitService;
import com.example.demo.util.CommonPage;
import com.example.demo.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSW
 * @since 2022-08-05
 */
@RestController
@RequestMapping("/fruit")
public class FruitController {
    @Autowired
    FruitService fruitService;

    /**
     * 插入或更新某条记录
     * RequestBody 键前端传过来的json数据转化为java对象
     * @param fruit
     * @return
     */
    @PostMapping("/save")
    public CommonResult saveAndUpdate(@RequestBody Fruit fruit){
        boolean b = fruitService.saveOrUpdate(fruit);
        if (b){
            return CommonResult.success(b);
        }else{
            return CommonResult.failed();
        }
    }

    /**
     * 删除一条记录
     * @param fid
     * @return
     */
    @DeleteMapping("/{fid}")
    public CommonResult deleteFruit(@PathVariable Integer fid){
        return CommonResult.success(fruitService.removeById(fid));
    }

    @PostMapping("/dlbatch")
    public CommonResult deleteBatch(@RequestBody List<Integer> ids){
       return CommonResult.success( fruitService.removeByIds(ids));
    }

    @GetMapping("/page")
    public CommonResult<CommonPage<Fruit>> pageCommonResult(Integer pageNum,Integer pageSize,
                                                            @RequestParam(defaultValue = "") String fname,
                                                            @RequestParam(defaultValue = "") String remark
                                                            ){
       IPage page =  fruitService.getList(fname,remark,pageNum,pageSize);
       return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * 导出
     * @param response
     * @throws Exception
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        List<Fruit> fruit = fruitService.list(null);

        //在内存中操作，写到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
//        writer.addHeaderAlias("fid","编号");
//        writer.addHeaderAlias("fname","名称");
//        writer.addHeaderAlias("fcount","库存");
//        writer.addHeaderAlias("price","价格");
//        writer.addHeaderAlias("remark","描述");
        //一次性取出所有记录，强制输出标题
        writer.write(fruit,true);

        //设置浏览器响应格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String filename = URLEncoder.encode("水果信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename"+filename+".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();


    }

    /**
     * 导入
     * @param file
     * @throws Exception
     */

    @PostMapping("/import")
    public CommonResult imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Fruit> list = reader.readAll(Fruit.class);
        return CommonResult.success(fruitService.saveBatch(list));

    }

}

