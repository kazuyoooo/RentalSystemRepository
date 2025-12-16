package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikeda.entity.DvdItem;
import com.ikeda.service.ItemService;



@Controller
public class HomeController {
	
	@Autowired
    private ItemService itemService;

    @GetMapping({"/home"})
    public String showHomePage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model) {

        Page<DvdItem> items = itemService.findAll(page);
        model.addAttribute("items", items);

        return "index";
    }

    // 商品詳細
    @GetMapping("/detail")
    public String showDetail(@RequestParam Integer id, Model model) {
        DvdItem item = itemService.findById(id);
        model.addAttribute("item", item);
        return "detail";
    }
}
