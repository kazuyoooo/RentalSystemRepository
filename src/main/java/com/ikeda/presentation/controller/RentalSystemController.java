package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikeda.LoginService;
import com.ikeda.entity.DvdItem;
import com.ikeda.repository.DvdItemRepository;
import com.ikeda.data.ItemData;

import jakarta.servlet.http.HttpSession;

@Controller
public class RentalSystemController {
	@Autowired
	private LoginService loginService;  // インスタンスを注入
	
	@GetMapping(value = "/home")
	public String toHome(HttpSession session, Model model) {
		
		ItemData itemData = (ItemData) session.getAttribute("itemData");
		
		if (itemData == null) {
			itemData = new ItemData();
			itemData.setItemName("データベースから取得するタイトル名");
			session.setAttribute("itemData", itemData);
		}
		model.addAttribute("itemData", itemData);
		return "index";
	}
	
	@GetMapping(value = "/detail")
	public String toDetail() {
		return "detail";
	}

	@GetMapping("/login")
	public String toLogin() {
		return "login"; // templates/login.html を返す
	}

	@PostMapping("/login")
	public String doLogin(
			@RequestParam String email,
			@RequestParam String password,
			Model model) {

		boolean result = loginService.loginCheck(email, password);

		if (result) {
			return "redirect:/home"; // ログイン成功
		} else {
			model.addAttribute("error", "メールアドレスまたはパスワードが違います");
			return "login";
		}

	}
	@Autowired
	private DvdItemRepository dvdItemRepository;
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page) {

        int pageSize = 9; // 1ページ9件

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<DvdItem> items = dvdItemRepository.findAll(pageable);

        model.addAttribute("items", items);        // 一覧（ページ情報つき）
        model.addAttribute("currentPage", page);   // 今のページ番号(0始まり)

        return "index"; // 今の index.html を使う
    }
	

}
