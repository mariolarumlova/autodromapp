package pl.mrumlova.autodrom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value="select * " +
            "from message m " +
            "where m.begin_date >= :borderFrom", nativeQuery = true)
    List<Message> getMessagesWhereBeginDateIs(@Param("borderFrom") String borderFrom);

    @Query(value="select * " +
            "from message m " +
            "where m.end_date >= :borderFrom", nativeQuery = true)
    List<Message> getMessagesWhereEndDateIs(@Param("borderFrom") String borderFrom);

    @Query(value="select * " +
            "from message m " +
            "where m.save_date >= :borderFrom", nativeQuery = true)
    List<Message> getMessagesWhereSaveDateIs(@Param("borderFrom") String borderFrom);

    @Query(value="select * " +
            "from message m " +
            "where m.update_date >= :borderFrom", nativeQuery = true)
    List<Message> getMessagesWhereUpdateDateIs(@Param("borderFrom") String borderFrom);
}
