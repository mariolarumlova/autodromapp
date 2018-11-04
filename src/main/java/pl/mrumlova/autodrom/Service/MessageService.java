package pl.mrumlova.autodrom.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mrumlova.autodrom.model.Message;
import pl.mrumlova.autodrom.repository.MessageRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void update(Message editedMessage) {
        Optional<Message> messageToUpdate = messageRepository.findById(editedMessage.getId());
        Message message = messageToUpdate.get();

        message.setName(editedMessage.getName());
        message.setSurname(editedMessage.getSurname());
        message.setCelebrity(editedMessage.getCelebrity());
        message.setCity(editedMessage.getCity());
        message.setBeginDate(editedMessage.getBeginDate());
        message.setEndDate(editedMessage.getEndDate());
        message.setCategory(editedMessage.getCategory());
        message.setEmail(editedMessage.getEmail());
        message.setPhoneNumber(editedMessage.getPhoneNumber());
        message.setDescription(editedMessage.getDescription());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        message.setUpdateDate(dateFormat.format(new Date()));
    }

}
