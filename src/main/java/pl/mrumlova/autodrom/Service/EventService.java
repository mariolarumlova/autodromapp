package pl.mrumlova.autodrom.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mrumlova.autodrom.model.Event;
import pl.mrumlova.autodrom.model.Message;
import pl.mrumlova.autodrom.repository.EventRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class EventService {
    
    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void update(Event editedEvent) {
        Optional<Event> eventToUpdate = eventRepository.findById(editedEvent.getId());
        Event message = eventToUpdate.get();

        message.setName(editedEvent.getName());
        message.setDescription(editedEvent.getDescription());
    }
}
