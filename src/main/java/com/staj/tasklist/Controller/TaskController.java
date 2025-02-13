package com.staj.tasklist.Controller;

import com.staj.tasklist.Dto.TaskDto;
import com.staj.tasklist.Entity.Enums.TaskPriority;
import com.staj.tasklist.Entity.Enums.TaskState;
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

    //GET REQUESTS AND FILTERS >>>>>>>>>>>>>>>>>>>>>>>>>
    @GetMapping
    public List<TaskDto> GetAllTasks() {
        return taskService.FindAllTasks();
    }

    @GetMapping
    public List<TaskDto> GetTasksOrderedByState() {
        return taskService.FindAllOrderedByState();
    }

    @GetMapping
    public List<TaskDto> GetTasksByState(TaskState state) {
        return taskService.FindByState(state);
    }

    @GetMapping
    public List<TaskDto> GetTasksOrderedByPriority() {
        return taskService.FindAllOrderedByPriority();
    }

    @GetMapping
    public List<TaskDto> GetTasksByPriority(TaskPriority priority) {
        return taskService.FindByPriority(priority);
    }
    //GET REQUESTS AND FILTERS END>>>>>>>>>>>>>>>>>>>>>>>>>
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
