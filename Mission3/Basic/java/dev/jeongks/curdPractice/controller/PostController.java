package dev.jeongks.curdPractice.controller;

import dev.jeongks.curdPractice.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private PostService postService;
    public PostController(
            @Autowired PostService postService
    ){
        this.postService = postService;
    }

    @RequestMapping(
            value = "/post",
            method = RequestMethod.GET
    )
    public String postHome (){
        return "postList.html";
    }
}