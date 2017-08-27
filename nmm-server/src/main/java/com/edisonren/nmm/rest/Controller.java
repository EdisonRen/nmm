package com.edisonren.nmm.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by edison on 8/26/17.
 */
@RestController
public class Controller {

    @RequestMapping("/")
    public String doshit() {
        return "This GET shit works";
    }
}
