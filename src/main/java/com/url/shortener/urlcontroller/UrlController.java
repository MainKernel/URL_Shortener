package com.url.shortener.urlcontroller;

import com.url.shortener.database.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller("/urls")
@RequiredArgsConstructor
@Slf4j
public class UrlController {
    private final UrlService urlService;


    @GetMapping("/urls/{id}")
    public RedirectView frontController(@PathVariable String id) {
        try {
            String byShortUrl = urlService.getLongUrl(id);

            if (!(byShortUrl == null || byShortUrl.isEmpty())) {
                return new RedirectView(byShortUrl);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new RedirectView("/404");
    }


}
