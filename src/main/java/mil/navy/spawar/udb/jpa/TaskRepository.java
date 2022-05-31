package mil.navy.spawar.udb.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    @Query("FROM TaskEntity WHERE id = ?1")
    List<TaskEntity> findTaskById(Integer id);

    @Query("FROM TaskEntity WHERE "
            + "(:taskName is null or taskName LIKE %:taskName%)"
            + "AND (:nextToken is null or id >= :nextToken)"
            + "ORDER BY id ASC")
    List<TaskEntity> findTasksWithPagination(
            @Param("taskName") String taskName,
            @Param("nextToken") Integer nextToken,
            Pageable pageable);

    Boolean existsTaskById(Integer id);
}
