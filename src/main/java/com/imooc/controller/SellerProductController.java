package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.ProductForm;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * 描述: 卖家端商品
 *
 * @author LIULE9
 * @create 2018-10-13 12:27 PM
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

  private final ProductService productService;

  private final CategoryService categoryService;

  @Autowired
  public SellerProductController(ProductService productService, CategoryService categoryService) {
    this.productService = productService;
    this.categoryService = categoryService;
  }

  @GetMapping("/list")
  public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size) {

    PageRequest pageRequest = PageRequest.of(page - 1, size);
    Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
    ModelAndView modelAndView = new ModelAndView("product/list");
    modelAndView.addObject("productInfoPage", productInfoPage);
    modelAndView.addObject("currentPage", page);
    modelAndView.addObject("size", size);
    return modelAndView;
  }

  /**
   * 商品下架
   *
   * @param productId
   * @return
   */
  @GetMapping("/off_sale")
  public ModelAndView offSale(@RequestParam("productId") String productId) {
    try {
      productService.offSale(productId);
    } catch (SellException e) {
      log.error("【卖家端商品下架】 异常={}", e);
      ModelAndView error = new ModelAndView("common/error");
      error.addObject("msg", e.getMessage());
      error.addObject("url", "/sell/seller/product/list");
      return error;
    }
    ModelAndView success = new ModelAndView("common/success");
    success.addObject("msg", ResultEnum.SUCCESS);
    success.addObject("url", "/sell/seller/product/list");
    return success;
  }

  /**
   * 商品上架
   *
   * @param productId
   * @return
   */
  @GetMapping("/on_sale")
  public ModelAndView onSale(@RequestParam("productId") String productId) {
    try {
      productService.onSale(productId);
    } catch (SellException e) {
      log.error("【卖家端商品上架】 异常={}", e);
      ModelAndView error = new ModelAndView("common/error");
      error.addObject("msg", e.getMessage());
      error.addObject("url", "/sell/seller/product/list");
      return error;
    }
    ModelAndView success = new ModelAndView("common/success");
    success.addObject("msg", ResultEnum.SUCCESS);
    success.addObject("url", "/sell/seller/product/list");
    return success;
  }

  @GetMapping("/index")
  public ModelAndView index(@RequestParam(value = "productId", required = false) String productId) {
    ModelAndView modelAndView = new ModelAndView("product/detail");
    if (!StringUtils.isEmpty(productId)) {
      ProductInfo productInfo = productService.findOne(productId);
      if (productInfo == null) {
        log.error("【卖家端商品详情】 商品不存在, productId={}", productId);
        throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
      }
      modelAndView.addObject("productInfo", productInfo);
    }
    List<ProductCategory> categoryList = categoryService.findAll();
    modelAndView.addObject("categoryList", categoryList);
    return modelAndView;
  }

  /**
   * 保存和更新
   *
   * @param productForm
   * @param bindingResult
   * @return
   */
  @PostMapping("/save")
//  @CachePut(cacheNames = "product", key = "123")
//  @CacheEvict 先清除缓存，然后再存放
  @CacheEvict(cacheNames = "product", key = "123")
  public ModelAndView save(@Valid ProductForm productForm,
                           BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      ModelAndView error = new ModelAndView("common/error");
      error.addObject("msg", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
      error.addObject("url", "/sell/seller/product/index");
      return error;
    }
    ProductInfo productInfo = new ProductInfo();
    //如果productId为空，说明是新增
    if (!StringUtils.isEmpty(productForm.getProductId())) {
      productInfo = productService.findOne(productForm.getProductId());
    } else {
      productForm.setProductId(KeyUtil.genUniqueKey());
    }
    if (productInfo == null) {
      ModelAndView error = new ModelAndView("common/error");
      error.addObject("msg", ResultEnum.PRODUCT_NOT_EXIST);
      error.addObject("url", "/sell/seller/product/index");
      return error;
    }
    BeanUtils.copyProperties(productForm, productInfo);

    try {
      productService.save(productInfo);
    } catch (SellException e) {
      ModelAndView error = new ModelAndView("common/error");
      error.addObject("msg", e.getMessage());
      error.addObject("url", "/sell/seller/product/index");
      return error;
    }

    ModelAndView modelAndView = new ModelAndView("/common/success");
    modelAndView.addObject("url", "sell/seller/product/list");
    return modelAndView;
  }

}