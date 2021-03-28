package com.mkgn.weatherservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

@RestController
public class CounterController {

    @GetMapping("/counter")
        // @CookieValue
    Map<String, Object> getCounter(HttpServletRequest request, HttpServletResponse response) {
//        request.getHeaderNames().asIterator().forEachRemaining(System.out::println);
//        response.setHeader("my-custom-header-response", "value");

        int counter = 0;

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            counter = 1;
            Cookie cookie = new Cookie("counter", String.valueOf(counter));
            response.addCookie(cookie);
            return Map.of("counter", counter);
        }

        Cookie newCookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("counter"))
                .findFirst()
                .orElseGet(() -> new Cookie("counter", "0"));

        String counterString = newCookie.getValue();
        counter = Integer.parseInt(counterString);
        counter++;
        newCookie.setValue(String.valueOf(counter));
        newCookie.setMaxAge(5);

        response.addCookie(newCookie);

        return Map.of("counter", counter);
    }

    @GetMapping("/counter/session")
        // @SessionAttribute
    Map<String, Object> getSessionCounter(HttpSession httpSession) {
        Object counter = httpSession.getAttribute("counter");

        if (counter == null) {
            httpSession.setAttribute("counter", 1);
            return Map.of("counter", 1);
        }

        int counterValue = (int) counter;
        counterValue++;
        httpSession.setAttribute("counter", counterValue);

        return Map.of("counter", counterValue);
    }
}