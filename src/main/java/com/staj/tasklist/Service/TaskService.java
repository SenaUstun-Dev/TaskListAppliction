package com.staj.tasklist.Service;

import com.staj.tasklist.Dto.TaskDto;
import com.staj.tasklist.Entity.Task;
import com.staj.tasklist.Repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDto> FindAllTasks()  { //pulls all tasks from db, turns them into dto and put in dto list, returns that dto list
        List<Task> tasks = taskRepository.findAll();

        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskDto taskDto = new TaskDto(task.getTitle(), task.getContent(), task.getState(), task.getDeadline());
            taskDtos.add(taskDto);
        }
        return taskDtos;
    }

    public TaskDto CreateTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setContent(taskDto.getContent());
        task.setState(taskDto.getState());
        task.setDeadline(taskDto.getDeadline());
        taskRepository.save(task);
        return taskDto;
    }

    public void DeleteTask(Long id) {
        //taskRepostory.delete(taskRepostory.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id)));
        try {
            taskRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            System.out.println("Task not found, can't delete");
        }
    }

    public TaskDto UpdateTask(Long id , TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        task.setTitle(taskDto.getTitle());
        task.setContent(taskDto.getContent());
        task.setState(taskDto.getState());
        task.setDeadline(taskDto.getDeadline());
        taskRepository.save(task);
        return taskDto;
    }

}
