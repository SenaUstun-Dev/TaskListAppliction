package com.staj.tasklist.Dto;

import com.staj.tasklist.Entity.Enums.TaskPriority;
import com.staj.tasklist.Entity.Enums.TaskState;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String title;
    private String content;

    @Nullable
    @Enumerated(EnumType.STRING)
    private TaskState state;

    @Nullable
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Nullable
    private String deadline;

//    // Varsayılan değeri ayarlamak için
//    @PrePersist
//    public void prePersist() {
//        if (state == null) {
//            state = TaskState.PENDING;
//        }
//        if (priority == null) {
//            priority = TaskPriority.CASUAL;
//        }
//        if (deadline == null) {
//            deadline = "no pressure";
//        }
//    }
}
