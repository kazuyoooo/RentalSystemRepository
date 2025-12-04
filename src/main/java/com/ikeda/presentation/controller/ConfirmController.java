package com.ikeda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ikeda.presentation.form.MemberForm;

@Controller
public class ConfirmController {

    // GET /confirm → 確認画面表示
    @GetMapping("/confirm")
    public String showConfirm(MemberForm memberForm, Model model) {
        model.addAttribute("memberForm", memberForm);
        return "confirm"; // templates/confirm.html
    }

    // POST /confirm → 確定処理（登録完了 or DB保存）
    @PostMapping("/confirm")
    public String submitConfirm(MemberForm memberForm, Model model) {
        // TODO: Serviceを呼んでDB登録など
        // memberService.register(memberForm);

        return "redirect:/"; // TOP画面へリダイレクト
    }
}
