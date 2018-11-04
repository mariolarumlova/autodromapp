package pl.mrumlova.autodrom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.mrumlova.autodrom.Service.MessageService;
import pl.mrumlova.autodrom.model.Message;
import pl.mrumlova.autodrom.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class MessageController {

    private MessageRepository messageRepository;
    private MessageService messageService;

    @Autowired
    public MessageController(MessageRepository messageRepository, MessageService messageService) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
    }

    @GetMapping("/send")
    public String addForm(Model model){
        model.addAttribute("newMessage", new Message());
        return "sendmessage";
    }

    @PostMapping("/save")
    public String sendMessage(Message message) {
        messageRepository.save(message);
        return "success";
    }

    @GetMapping("/inbox")
    public String allMessages(Model model) {
        List<Message> allMessages = messageRepository.findAll();
        model.addAttribute("messages", allMessages);
        return "inbox";
    }

    @GetMapping("/offer/{id}")
    public String openMessage(Model model, @PathVariable Long id) {
        Optional<Message> messageById = messageRepository.findById(id);
        messageById.ifPresent(message -> model.addAttribute("message", message));
        return messageById.map(message -> "openmessage").orElse("nomessage");
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String deleteMessage(@PathVariable Long id) {
        messageRepository.deleteById(id);
        return "Usunięto ofertę o id " + id;
    }

    @GetMapping("/update")
    @ResponseBody
    public String updateMessage(){
        messageService.update();
        return "Zaktualizowana oferta";
    }
}
