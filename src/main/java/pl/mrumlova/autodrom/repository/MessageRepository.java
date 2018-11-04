package pl.mrumlova.autodrom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mrumlova.autodrom.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m order by m.surname")
    List<Message> orderBySurname();

    @Query("select m from Message m order by m.beginDate")
    List<Message> orderByBeginDate();

    @Query("select m from Message m order by m.city")
    List<Message> orderByCity();

    @Query("select m from Message m order by m.saveDate")
    List<Message> orderBySaveDate();

}
