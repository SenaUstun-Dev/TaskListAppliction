package com.staj.tasklist.Repository;

import com.staj.tasklist.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}
