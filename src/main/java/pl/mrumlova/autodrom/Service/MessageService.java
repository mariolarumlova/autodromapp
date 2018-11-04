package pl.mrumlova.autodrom.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mrumlova.autodrom.model.Message;
import pl.mrumlova.autodrom.repository.MessageRepository;
import java.util.Optional;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void update() {
        Optional<Message> messageToUpdate = messageRepository.findById(2L);
        Message message = messageToUpdate.get();
        message.setCity("Wałbrzych");
    }

}
