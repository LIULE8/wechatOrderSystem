package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.enums.ResultEnum;
import com.imooc.form.CategoryFrom;
import com.imooc.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * 描述: 卖家类目
 *
 * @author LIULE9
 * @create 2018-10-13 2:01 PM
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

  private final CategoryService categoryService;

  @Autowired
  public SellerCategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/list")
  public ModelAndView list() {
    ModelAndView modelAndView = new ModelAndView("category/list");
    List<ProductCategory> categoryList = categoryService.findAll();
    modelAndView.addObject("categoryList", categoryList);
    return modelAndView;
  }

  /**
   * 展示商品类目详情
   *
   * @param categoryId
   * @return
   */
  @GetMapping("/index")
  public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
    ModelAndView modelAndView = new ModelAndView("category/detail");
    if (categoryId != null) {
      ProductCategory productCategory = categoryService.findOne(categoryId);
      modelAndView.addObject("category", productCategory);
    }
    return modelAndView;
  }

  /**
   * 保存 / 更新
   *
   * @param categoryFrom
   * @param bindingResult
   * @return
   */
  @PostMapping("/save")
  public ModelAndView save(@Valid CategoryFrom categoryFrom, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      ModelAndView error = new ModelAndView("common/error");
      error.addObject("msg", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
      error.addObject("url", "sell/seller/category/list");
      return error;
    }

    ProductCategory productCategory = new ProductCategory();
    if (categoryFrom.getCategoryId() != null) {
      productCategory = categoryService.findOne(categoryFrom.getCategoryId());
      if (productCategory == null) {
        ModelAndView error = new ModelAndView("common/error");
        error.addObject("msg", ResultEnum.PRODUCT_CATEGORY_ERROR);
        error.addObject("url", "sell/seller/category/list");
        return error;
      }
    }

    BeanUtils.copyProperties(categoryFrom, productCategory);
    ModelAndView modelAndView = new ModelAndView("common/success");
    modelAndView.addObject("url", "sell/seller/category/list");
    return modelAndView;
  }
}