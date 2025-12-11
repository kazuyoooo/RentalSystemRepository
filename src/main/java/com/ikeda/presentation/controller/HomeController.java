package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikeda.entity.DvdItem;
import com.ikeda.repository.DvdItemRepository;

@Controller
public class HomeController {

    @Autowired
    private DvdItemRepository dvdItemRepository;

    // ログイン後ホーム（商品一覧・ページネーション）
    @GetMapping("/home")
    public String showHomePage(Model model,
                               @RequestParam(name="page", defaultValue="0") int page) {

        int pageSize = 9; // 1ページに表示する件数
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<DvdItem> items = dvdItemRepository.findAll(pageable);

        model.addAttribute("items", items);
        return "home";
    }

    // 商品詳細
    @GetMapping("/detail")
    public String showDetail(@RequestParam Integer id, Model model) {
        DvdItem item = dvdItemRepository.findById(id).orElse(null);
        model.addAttribute("item", item);
        return "detail";
    }
}
