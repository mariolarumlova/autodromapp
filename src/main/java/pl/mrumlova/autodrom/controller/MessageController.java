package pl.mrumlova.autodrom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mrumlova.autodrom.Service.MessageService;
import pl.mrumlova.autodrom.model.Event;
import pl.mrumlova.autodrom.model.Message;
import pl.mrumlova.autodrom.repository.EventRepository;
import pl.mrumlova.autodrom.repository.MessageRepository;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MessageController {

    private MessageRepository messageRepository;
    private EventRepository eventRepository;
    private MessageService messageService;
    List<Message> allMessages;
    SimpleDateFormat dateFormat;

    @Autowired
    public MessageController(MessageRepository messageRepository, MessageService messageService, EventRepository eventRepository) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.eventRepository = eventRepository;
        this.allMessages = messageRepository.findAll();
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
        allMessages = messageRepository.findAll();
        return "redirect:/inbox";
    }

    @GetMapping("/inbox")
    public String allMessages(Model model, @ModelAttribute("category") Event category) {
        model.addAttribute("messages", allMessages);
        model.addAttribute("categories", eventRepository.findAll());
        return "inbox";
    }

    @GetMapping("/refresh")
    public String refreshMessages(Model model, @ModelAttribute("category") Event category) {
        allMessages = messageRepository.findAll();
        model.addAttribute("messages", allMessages);
        model.addAttribute("categories", eventRepository.findAll());
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
        allMessages = messageRepository.findAll();
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
        allMessages = messageRepository.findAll();
        return "redirect:/inbox";
    }

    @PostMapping("/sorting")
    public String sortingMethod(@RequestParam String option,
                                @RequestParam(value = "descending", required = false) String descending){
        switch (option){
            case "0": allMessages = messageRepository.orderBySurname();
                break;
            case "1": allMessages = messageRepository.orderByBeginDate();
                break;
            case "2": allMessages = messageRepository.orderByCity();
                break;
            case "3": allMessages = messageRepository.orderBySaveDate();
                break;
        }
        if (descending != null) {
            Collections.reverse(allMessages);
        }
        return "redirect:/inbox";
    }

    @PostMapping("/filteringCategory")
    public String filteringCategoryMethod(@ModelAttribute("category") Event category){
        Long categoryId = category.getId();
        allMessages = messageRepository.getMessagesWhereCategoryIdIs(categoryId);
        return "redirect:/inbox";
    }

    @PostMapping("/filtering")
    public String filteringMethod(@RequestParam String borderFrom, @RequestParam String borderTo, @RequestParam String radio){

        if(borderFrom.equals("")) borderFrom = "1970-01-01";
        if(borderTo.equals("")) borderTo = "3000-12-31";

        switch (radio){
            case "0": allMessages= messageRepository.getMessagesWhereBeginDateIs(borderFrom, borderTo);
                break;
            case "1": allMessages= messageRepository.getMessagesWhereEndDateIs(borderFrom, borderTo);
                break;
            case "2": allMessages= messageRepository.getMessagesWhereSaveDateIs(borderFrom, borderTo);
                break;
            case "3": allMessages= messageRepository.getMessagesWhereUpdateDateIs(borderFrom, borderTo);
                break;
        }

        return "redirect:/inbox";
    }
}
