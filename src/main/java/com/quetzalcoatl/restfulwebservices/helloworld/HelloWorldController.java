package com.quetzalcoatl.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    /* with header injection */
//    @GetMapping(path = "/hello-world/i18n")
//    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
//        return messageSource.getMessage("greeting", null, "default message", locale);
//    }

    /* with LocaleContextHolder */
    @GetMapping(path = "/hello-world/i18n")
    public String helloWorldInternationalized() {
        return messageSource.getMessage(
                "greeting", null, "default message", LocaleContextHolder.getLocale()
        );
    }
}
