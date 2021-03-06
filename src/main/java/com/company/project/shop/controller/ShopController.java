package com.company.project.shop.controller;

import com.company.project.questionprize.dto.QuestionPrize;
import com.company.project.shop.dto.Shop;
import com.company.project.shop.service.ShopServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Shop")
@RestController
public class ShopController {

    @Autowired
    @Qualifier("ShopService")
    private ShopServiceImpl shopService;

    @ApiOperation(tags = "增加商品",value = "增加商品")
    @RequestMapping(value = "/insert" ,method = RequestMethod.POST)
    public Boolean insert(@ApiParam(name = "Problem" ,value = "商品条目" ,required = true) @RequestBody Shop shop){
        if(shopService.insert(shop) == 1)
            return true;
        return false;
    }

    @ApiOperation(value = "删除商品",tags = "删除商品")
    @RequestMapping(value = "/delete" ,method = RequestMethod.GET)
    public Boolean delete(@ApiParam(name = "id" ,value="商品id" ,required = true) @RequestParam Integer id) {
        if(shopService.deleteById(id) == 1)
            return true;
        return false;
    }

    @ApiOperation(value = "更新商品信息",tags = "更新商品信息")
    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    public Boolean update(@ApiParam(name = "id" ,value="商品id" ,required = true) @RequestBody Shop shop) {
        if(shopService.update(shop) == 1)
            return true;
        return false;
    }

    @ApiOperation(value = "根据ID显示商品信息" ,tags = "显示某商品信息" ,notes = "参数是商品id")
    @RequestMapping(value = "/selectById" ,method = RequestMethod.GET)
    public Shop selectById(@ApiParam(name = "id" ,value="商品id" ,required = true) @RequestParam Integer id) {
        Shop shop = shopService.selectById(id);
        return shop;
    }

    @ApiOperation(value = "分页查询" ,tags = "分页查询" ,notes = "分页查询")
    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public List<Shop> list(@ApiParam(name = "page" ,value="页数" ,required = true) @RequestParam(defaultValue = "0") Integer page, @ApiParam(name = "size" ,value="每一页显示的数量" ,required = true)@RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Shop> list = shopService.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        return list;
    }

    @ApiOperation(value = "批量删除商品" ,tags = "批量删除商品" ,notes = "格式：1,2,3（中间用英文逗号分开） ")
    @RequestMapping(value = "/deleteByIds" ,method = RequestMethod.GET)
    public Boolean deleteByIds(@ApiParam(name = "ids" ,value="商品们id" ,required = true) @RequestParam String ids) {
        if(shopService.deleteByIds(ids) != 0)
            return true;
        return false;
    }
}
