package pl.mrumlova.autodrom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mrumlova.autodrom.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
