package com.imooc.controller;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

  @Autowired
  public SellerProductController(ProductService productService) {
    this.productService = productService;
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
}