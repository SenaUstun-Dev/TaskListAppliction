package com.staj.tasklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasklistApplication {
    public static void main(String[] args) {
        SpringApplication.run(TasklistApplication.class, args);
    }

    /*
    to do list web projesi
    temelde yapılacak işleri tutup kullanıcıy gösterecek bir liste
    kullanıcı listeye iş ekleyebilir, çıkarabilir,olan işi düzenleyebilir

    ekstara hedefler:
    işler tamamlamlnınca işaretlenebilecek
    toplam kaç iş olduğu ve kaçı tamamlandığı gözükecek
    başlığına göre işler arabilecek

    veritabanı tabloları:
    task
    -id, not null, unique
    -title, String, not null
    -content, String, default:nullable
    -state, enum, default:pending
    -deadline, date, default:nullable

CREATE TYPE task_state AS ENUM ('pending', 'in_progress', 'completed');

CREATE TABLE task (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT DEFAULT NULL,
    state task_state NOT NULL DEFAULT 'pending',
    deadline DATE DEFAULT NULL
     */

    /*
    @RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }
}

     */

}

