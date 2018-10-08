package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.ResultVOUtil;
import com.imooc.vo.ProductInfoVO;
import com.imooc.vo.ProductVO;
import com.imooc.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: 买家商品
 *
 * @author LIULE9
 * @create 2018-10-08 7:14 PM
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

  private final ProductService productService;

  private final CategoryService categoryService;

  @Autowired
  public BuyerProductController(ProductService productService, CategoryService categoryService) {
    this.productService = productService;
    this.categoryService = categoryService;
  }

  @GetMapping("/list")
  public ResultVO getAllProducts() {
    //1.查询所有上架的商品
    List<ProductInfo> productInfoList = productService.findUpAll();

    //2.查询类目(一次性查询)
    List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
    List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

    //3.数据拼装
    List<ProductVO> productVOs = new ArrayList<>();
    for (ProductCategory productCategory : productCategoryList) {
      List<ProductInfoVO> productInfoVOList = new ArrayList<>();

      ProductVO productVO = new ProductVO();
      productVO.setCategoryType(productCategory.getCategoryType());
      productVO.setCategoryName(productCategory.getCategoryName());
      productVO.setProductInfoVOList(productInfoVOList);
      productVOs.add(productVO);

      for (ProductInfo productInfo : productInfoList) {
        if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
          ProductInfoVO productInfoVO = new ProductInfoVO();
          BeanUtils.copyProperties(productInfo, productInfoVO);
          productInfoVOList.add(productInfoVO);
        }
      }
    }
    return ResultVOUtil.success(productVOs);
  }
}