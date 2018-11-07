package pl.mrumlova.autodrom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mrumlova.autodrom.Service.EventService;
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
    private EventService eventService;
    private List<Message> allMessages;
    private List<Event> allCategories;
    SimpleDateFormat dateFormat;

    @Autowired
    public MessageController(MessageRepository messageRepository, MessageService messageService, EventRepository eventRepository, EventService eventService) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.eventRepository = eventRepository;
        this.eventService = eventService;
        this.allMessages = messageRepository.findAll();
        this.allCategories = eventRepository.findAll();
    }

    @RequestMapping("/")
    public String mainPage() {
        return "mainpage";
    }

    @GetMapping("/send")
    public String addForm(Model model){
        model.addAttribute("newMessage", new Message());
        model.addAttribute("categories", allCategories);
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

    @GetMapping("/categories")
    public String manageEvents(Model model) {
        model.addAttribute("categories", allCategories);
        return "categories";
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
        model.addAttribute("categories", allCategories);
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

    @GetMapping("/addCategory")
    public String addCategory(Model model){
        model.addAttribute("newEvent", new Event());
        return "addcategory";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(Event event) {
        eventRepository.save(event);
        allCategories = eventRepository.findAll();
        return "redirect:/categories";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Long id) {
        messageRepository.deleteMessagesWhereCategoryIdIs(id);
        eventRepository.deleteById(id);
        allCategories = eventRepository.findAll();
        allMessages = messageRepository.findAll();
        return "redirect:/categories";
    }

    @GetMapping("/updateCategory/{id}")
    public String updateCategoryForm(Model model, @PathVariable Long id){
        Optional<Event> eventById = eventRepository.findById(id);
        eventById.ifPresent(category -> model.addAttribute("category", category));
        model.addAttribute("categories", allCategories);
        return eventById.map(category -> "editcategory").orElse("nocategory");
    }

    @PostMapping("/updateCat")
    public String updateCategory(Event event) {
        eventService.update(event);
        allCategories = eventRepository.findAll();
        return "redirect:/categories";
    }
}
