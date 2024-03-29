package pl.mrumlova.autodrom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.mrumlova.autodrom.model.Message;

import javax.validation.executable.ValidateOnExecution;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Transactional
    @Modifying
    @Query(value="delete from message where category_id = :categoryId", nativeQuery = true)
    void deleteMessagesWhereCategoryIdIs(@Param("categoryId") Long categoryId);

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
            "where m.category_id = :categoryId", nativeQuery = true)
    List<Message> getMessagesWhereCategoryIdIs(@Param("categoryId") Long categoryId);

    @Query(value="select * " +
            "from message m " +
            "where m.begin_date between :borderFrom and :borderTo", nativeQuery = true)
    List<Message> getMessagesWhereBeginDateIs(@Param("borderFrom") String borderFrom, @Param("borderTo") String borderTo);

    @Query(value="select * " +
            "from message m " +
            "where m.end_date between :borderFrom and :borderTo", nativeQuery = true)
    List<Message> getMessagesWhereEndDateIs(@Param("borderFrom") String borderFrom, @Param("borderTo") String borderTo);

    @Query(value="select * " +
            "from message m " +
            "where m.save_date between :borderFrom and :borderTo", nativeQuery = true)
    List<Message> getMessagesWhereSaveDateIs(@Param("borderFrom") String borderFrom, @Param("borderTo") String borderTo);

    @Query(value="select * " +
            "from message m " +
            "where m.update_date between :borderFrom and :borderTo", nativeQuery = true)
    List<Message> getMessagesWhereUpdateDateIs(@Param("borderFrom") String borderFrom, @Param("borderTo") String borderTo);
}
