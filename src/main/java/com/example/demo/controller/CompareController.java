package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.ModifyUtil;
import com.example.demo.common.ResultFactory;
import com.example.demo.common.ResultObject;
import com.example.demo.pojo.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/9/16 14:51
 */
@RestController
@RequestMapping("/compare")
public class CompareController {
    private static final Logger logger = LoggerFactory.getLogger(CompareController.class);

    /**
     * @Description: 对比对象修改前后的字段
     * @Param: @param
     * @return: com.example.demo.common.ResultObject<java.lang.Object>
     * @Author: beijing.lv@hand-china.com
     * @Date: 2020/9/16
     */
    @PostMapping("/compareObject")
    public ResultObject<Object> compareObject() {
        //修改前
        ProductDto oldProduct = new ProductDto(1L, "华为", "彩电", "彩电");
        //修改后
        ProductDto newProduct = new ProductDto(1L, "华为", "手机", "华为Met");
        //信息对比
        ModifyUtil.classOfSrcResult(oldProduct, newProduct);
        if(StringUtils.isEmpty(newProduct.getBrand())){
            logger.info("Brand:{}","-----------");
        }
        if(!StringUtils.isEmpty(newProduct.getBrandFlag()) && "uodate".equals(newProduct.getBrandFlag())){
            logger.info("Brand:{}","数据更新");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("oldProduct", oldProduct);
        map.put("newProduct", newProduct);

        logger.info("旧数据：{}", JSON.toJSONString(oldProduct));
        logger.info("新数据：{}", JSON.toJSONString(newProduct));
        return ResultFactory.success(map);
    }


    /**
     * @Description: 集合数据对比，id为数据库主键，为数据对比基础字段
     * @Param: @param
     * @return: com.example.demo.common.ResultObject<java.lang.Object>
     * @Author: beijing.lv@hand-china.com
     * @Date: 2020/9/17
     */
    @PostMapping("/compareList")
    public ResultObject<Object> compareList() {
        List<ProductDto> oldList = new ArrayList<>();
        List<ProductDto> newList = new ArrayList<>();
        List<ProductDto> oldProduct = new ArrayList<>();
        ProductDto product1 = new ProductDto(1L, "富士通", "彩电", "彩电");
        ProductDto product2 = new ProductDto(2L, "华为", "手机", "华为Met");
        ProductDto product3 = new ProductDto(3L, "格力", "空调", "空调");
        oldProduct.add(product1);
        oldProduct.add(product2);
        oldProduct.add(product3);

        List<ProductDto> newProduct = new ArrayList<>();
        ProductDto product4 = new ProductDto(1L, "富士通", "冰箱", "电视");
        ProductDto product5 = new ProductDto(2L, "华为", "手机", "华为Met");
        ProductDto product6 = new ProductDto(null, "三星", "手机", "三星10");
        newProduct.add(product4);
        newProduct.add(product5);
        newProduct.add(product6);
        //信息对比
        Map<Long, ProductDto> mapBank = new HashMap<>();
        oldProduct.stream().forEach((item) -> {
            mapBank.put(item.getId(), item);
        });
        Iterator varBank = newProduct.iterator();
        while (varBank.hasNext()) {
            ProductDto next = (ProductDto) varBank.next();
            ProductDto dto = new ProductDto();
            //获取新增的数据
            if (ObjectUtils.isEmpty(next.getId())) {
                BeanUtils.copyProperties(next, dto);
                dto.setObjectFlag("CREATE");
                newList.add(dto);
            } else {
                //获取修改的数据
                ProductDto productDto1 = mapBank.get(next.getId());
                BeanUtils.copyProperties(next, dto);
                ModifyUtil.classOfSrcResult(productDto1, dto);
                newList.add(dto);
                oldList.add(productDto1);
            }
        }

        //获取删除的数据
        oldProduct.stream().forEach(item -> {
            ProductDto product7 = newProduct.stream().filter(o -> item.getId().equals(o.getId())).findAny().orElse(null);
            if (Objects.isNull(product7)) {
                logger.info("此数据被删除：{}", JSON.toJSONString(item));
                item.setObjectFlag("DELETE");
                oldList.add(item);
            }

        });
        Map<String, Object> map = new HashMap<>();
        map.put("oldList", oldList);
        map.put("newList", newList);
        return ResultFactory.success(map);
    }
}
