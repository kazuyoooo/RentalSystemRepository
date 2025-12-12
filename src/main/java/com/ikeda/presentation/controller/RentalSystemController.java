package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.ikeda.entity.DvdItem;
import com.ikeda.presentation.form.MemberForm;
import com.ikeda.repository.DvdItemRepository;
import com.ikeda.service.ItemService;

@Controller
public class RentalSystemController {
//	@Autowired
//	private LoginService loginService;  // インスタンスを注入
	
	@GetMapping(value = "/gohome")//二つあるので仮のgoに変更してます
	public String toHome( /* HttpSession session, Model model */ ) {
		
//		ItemData itemData = (ItemData) session.getAttribute("itemData");
//		
//		if (itemData == null) {
//			itemData = new ItemData();
//			itemData.setItemName("データベースから取得するタイトル名");
//			session.setAttribute("itemData", itemData);
//		}
//		model.addAttribute("itemData", itemData);
		return "home";
	}
	
//	@GetMapping(value = "/detail")
//	public String toDetail() {
//		return "detail";
//	}
	@Autowired
	private DvdItemRepository dvdItemRepository;
	
	@GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") Integer id, Model model) {
		
		DvdItem item = dvdItemRepository.findById(id).orElse(null);

		if (item == null) {
		    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
		}

        model.addAttribute("item", item);

        return "detail"; // detail.html を表示
    }


    // 商品詳細は HomeController に統一 → このメソッドは削除

    // 会員登録フォーム表示
    @GetMapping("/rentalForm")
    public String showForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "form";
    }

    // 会員情報編集フォーム表示
    @GetMapping("/editform")
    public String showEditForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "editForm";
    }

    // カート確認ページ
    @GetMapping("/cartconfirm")
    public String showCartConfirm() {
        return "cartconfirm";
    }
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "index";


}
}
