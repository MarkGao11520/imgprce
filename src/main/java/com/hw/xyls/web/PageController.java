package com.hw.xyls.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gaowenfeng on 2017/6/25.
 */
@Controller
@RequestMapping("pageController")
@Scope("prototype")
public class PageController {
}
