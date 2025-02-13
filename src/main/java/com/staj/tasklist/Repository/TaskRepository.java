package com.staj.tasklist.Repository;

import com.staj.tasklist.Entity.Enums.TaskPriority;
import com.staj.tasklist.Entity.Enums.TaskState;
import com.staj.tasklist.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    //TaskState durumuna göre sıralama (null -> PENDING -> IN_PROGRESS -> COMPLETED)
    @Query("SELECT t FROM Task t ORDER BY " +
            "CASE WHEN t.state IS NULL THEN 0 " +
            "WHEN t.state = 'PENDING' THEN 1 " +
            "WHEN t.state = 'IN_PROGRESS' THEN 2 " +
            "WHEN t.state = 'COMPLETED' THEN 3 END")
    List<Task> findAllOrderByState();

    // Kullanıcının seçtiği TaskState duruma göre filtreleme
    List<Task> findByState(TaskState state);

    //TaskPriority göre sıralama (URGENT -> IMPORTANT -> CASUAL -> CANWAIT)
    @Query("SELECT t FROM Task t ORDER BY " +
            "CASE WHEN t.priority = 'URGENT' THEN 0 " +
            "WHEN t.priority = 'IMPORTANT' THEN 1 " +
            "WHEN t.priority = 'CASUAL' THEN 2 " +
            "WHEN t.priority = 'CANWAIT' THEN 3 END")
    List<Task> findAllOrderByPriority();

    //Kullanıcının seçtiği TaskPriority durumuna göre filtreleme
    List<Task> findByPriority(TaskPriority priority);
}
