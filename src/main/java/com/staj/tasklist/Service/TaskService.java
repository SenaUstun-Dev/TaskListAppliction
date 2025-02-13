package com.staj.tasklist.Service;

import com.staj.tasklist.Dto.TaskDto;
import com.staj.tasklist.Entity.Enums.TaskPriority;
import com.staj.tasklist.Entity.Enums.TaskState;
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

    //GET REQUESTS AND FILTERS >>>>>>>>>>>>>>>>>>>>>>>>>
    public List<TaskDto> FindAllTasks()  { //pulls all tasks from db, turns them into dto and put in dto list, returns that dto list
        List<Task> tasks = taskRepository.findAll();
        return ConvertTasktoTaskDtoList(tasks);
    }


    public List<TaskDto> FindAllOrderedByState() { // (null -> PENDING -> IN_PROGRESS -> COMPLETED)
    List<Task> tasks = taskRepository.findAllOrderByState();
    return ConvertTasktoTaskDtoList(tasks);
    }

    public List<TaskDto> FindByState(TaskState state) {
        List<Task> tasks = taskRepository.findByState(state);
        return ConvertTasktoTaskDtoList(tasks);
    }

    public List<TaskDto> FindAllOrderedByPriority() { // (URGENT -> IMPORTANT -> CASUAL -> CANWAIT)
        List<Task> tasks = taskRepository.findAllOrderByPriority();
        return ConvertTasktoTaskDtoList(tasks);
    }

    public List<TaskDto> FindByPriority(TaskPriority priority) {
        List<Task> tasks = taskRepository.findByPriority(priority);
        return ConvertTasktoTaskDtoList(tasks);
    }

    //GET REQUESTS AND FILTERS END>>>>>>>>>>>>>>>>>>>>>>>>>


    public TaskDto CreateTask(TaskDto taskDto) {
        Task task = new Task();
        return getTaskDto(taskDto, task);
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
        return getTaskDto(taskDto, task);
    }

    private TaskDto getTaskDto(TaskDto taskDto, Task task) {
        task.setTitle(taskDto.getTitle());
        task.setContent(taskDto.getContent());
        task.setState(taskDto.getState());
        task.setPriority(taskDto.getPriority());
        task.setDeadline(taskDto.getDeadline());
        taskRepository.save(task);
        return taskDto;
    }


    //helper (Task --> TaskDto)
    public List<TaskDto> ConvertTasktoTaskDtoList(List<Task> tasks) {
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskDto taskDto = new TaskDto(task.getTitle(), task.getContent(), task.getState(),task.getPriority(), task.getDeadline());
            taskDtos.add(taskDto);
        }
        return taskDtos;
    }
}
