package pl.mrumlova.autodromapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private List<Message> messages;

    public HomeController() {
        messages = new ArrayList<>();
        messages.add(new Message("Mariola", 1));
        messages.add(new Message("Zdenek", 2));
        messages.add(new Message("Grzesiek",1));
        messages.add(new Message("Zdzisia",2));
        messages.add(new Message("Krzysiu", 2));
        messages.add(new Message("Paweł", 3));
        messages.add(new Message("Michał",4));
    }

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("messages", messages);
        model.addAttribute("newMessage", new Message());

        return "messages";
    }

    @PostMapping("/add")
    public String add(Message message) {
        messages.add(message);
        return "redirect:/"; // przekierowuje na stronę główną
    }
}
