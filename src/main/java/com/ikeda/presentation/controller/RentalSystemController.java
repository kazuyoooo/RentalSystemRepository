package com.ikeda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ikeda.presentation.form.MemberForm;

@Controller
public class RentalSystemController {

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
}
