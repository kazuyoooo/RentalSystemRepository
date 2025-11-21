package com.ikeda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RentalSystemController {
	
	@GetMapping(value = "/home")
	public String toHome() {
		return // ここからホーム画面に遷移する
	}

}
