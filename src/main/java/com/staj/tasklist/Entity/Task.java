package com.staj.tasklist.Entity;

import com.staj.tasklist.Entity.Enums.TaskState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="Tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id'nin otomatik artmasını sağlıyor
    private Long id;

    @NonNull
    private String title;

    private String content;
    private String deadline;

    @Enumerated(EnumType.STRING)
    private TaskState state;

    // Varsayılan değeri ayarlamak için
    @PrePersist
    public void prePersist() {
        if (state == null) {
            state = TaskState.PENDING;
        }
    }
    //private Priority priority;  I might add this if everything goes well

}
