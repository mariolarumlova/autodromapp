package pl.mrumlova.autodrom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mrumlova.autodrom.Service.MessageService;
import pl.mrumlova.autodrom.model.Message;
import pl.mrumlova.autodrom.repository.EventRepository;
import pl.mrumlova.autodrom.repository.MessageRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MessageController {

    private MessageRepository messageRepository;
    private EventRepository eventRepository;
    private MessageService messageService;
    SimpleDateFormat dateFormat;

    @Autowired
    public MessageController(MessageRepository messageRepository, MessageService messageService, EventRepository eventRepository) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.eventRepository = eventRepository;
    }

    @RequestMapping("/")
    public String mainPage() {
        return "mainpage";
    }

    @GetMapping("/send")
    public String addForm(Model model){
        model.addAttribute("newMessage", new Message());
        model.addAttribute("categories", eventRepository.findAll());
        return "sendmessage";
    }

    @PostMapping("/save")
    public String addMessage(Message message) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        message.setSaveDate(dateFormat.format(new Date()));
        message.setUpdateDate(dateFormat.format(new Date()));
        messageRepository.save(message);
        return "redirect:/inbox";
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
    public String deleteMessage(@PathVariable Long id) {
        messageRepository.deleteById(id);
        return "redirect:/inbox";
    }

    @GetMapping("/update/{id}")
    public String updateForm(Model model, @PathVariable Long id){
        Optional<Message> messageById = messageRepository.findById(id);
        messageById.ifPresent(message -> model.addAttribute("message", message));
        model.addAttribute("categories", eventRepository.findAll());
        return messageById.map(message -> "updatemessage").orElse("nomessage");
    }

    @PostMapping("/update")
    public String updateMessage(Message message) {
        messageService.update(message);
        return "redirect:/inbox";
    }
}
