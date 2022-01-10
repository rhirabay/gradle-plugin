package rhirabay;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(path = "/**", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String hello() {
        return "hello";
    }
}
