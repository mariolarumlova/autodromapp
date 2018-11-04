package pl.mrumlova.autodrom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mrumlova.autodrom.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
