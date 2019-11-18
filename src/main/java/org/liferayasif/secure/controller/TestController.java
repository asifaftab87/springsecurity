package org.liferayasif.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@RequestMapping("/portfolio")
	public ModelAndView view() {
		ModelAndView mav = new ModelAndView("sample");
		return mav;
	}
	
}
