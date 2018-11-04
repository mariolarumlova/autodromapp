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

    @Query("select m from Message m where str_to_date(m.beginDate, %Y-%m-%d)>=str_to_date(borderFrom, %Y-%m-%d) " +
            "and str_to_date(m.endDate, %Y-%m-%d)<=str_to_date(borderTo, %Y-%m-%d)")
    List<Message> getMessagesWhereBeginDateIs(@Param("borderFrom") String borderFrom, @Param("borderTo") String borderTo);

    @Query("select m from Message m where str_to_date(m.endDate, %Y-%m-%d)>=str_to_date(borderFrom, %Y-%m-%d) " +
            "and str_to_date(m.endDate, %Y-%m-%d)<=str_to_date(borderTo, %Y-%m-%d)")
    List<Message> getMessagesWhereEndDateIs(@Param("borderFrom") String borderFrom, @Param("borderTo") String borderTo);

    @Query("select m from Message m where str_to_date(m.saveDate, %Y-%m-%d)>=str_to_date(borderFrom, %Y-%m-%d) " +
            "and str_to_date(m.endDate, %Y-%m-%d)<=str_to_date(borderTo, %Y-%m-%d)")
    List<Message> getMessagesWhereSaveDateIs(@Param("borderFrom") String borderFrom, @Param("borderTo") String borderTo);

    @Query("select m from Message m where str_to_date(m.updateDate, %Y-%m-%d)>=str_to_date(borderFrom, %Y-%m-%d) " +
            "and str_to_date(m.endDate, %Y-%m-%d)<=str_to_date(borderTo, %Y-%m-%d)")
    List<Message> getMessagesWhereUpdateDateIs(@Param("borderFrom") String borderFrom, @Param("borderTo") String borderTo);
}
