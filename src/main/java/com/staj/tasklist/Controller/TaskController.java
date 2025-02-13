package com.staj.tasklist.Controller;

import com.staj.tasklist.Dto.TaskDto;
import com.staj.tasklist.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> GetAllTasks() {
        return taskService.FindAllTasks();
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> CreatTask(@RequestBody TaskDto taskDto) {
        taskService.CreateTask(taskDto);
        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskDto> UpdateTask(@PathVariable Long id,@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.UpdateTask(id, taskDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaskDto> DeleteTask(@PathVariable Long id) {
        taskService.DeleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
