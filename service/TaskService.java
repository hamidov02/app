package com.eTaskifyAPI.etaskify.service;

import com.eTaskifyAPI.etaskify.dto.TaskDto;
import com.eTaskifyAPI.etaskify.enumclas.Status;
import com.eTaskifyAPI.etaskify.exception.TaskNotFoundException;
import com.eTaskifyAPI.etaskify.model.Task;
import com.eTaskifyAPI.etaskify.model.AppUser;
import com.eTaskifyAPI.etaskify.repository.TaskRepository;
import com.eTaskifyAPI.etaskify.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;
    private final EmailService emailService;

    @Autowired
    public TaskService(TaskRepository taskRepository, AppUserRepository appUserRepository, EmailService emailService) {
        this.taskRepository = taskRepository;
        this.appUserRepository = appUserRepository;
        this.emailService = emailService;
    }

    public Task createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setHeader(taskDto.getHeader());
        task.setDescription(task.getDescription());
        task.setExpairedDate(taskDto.getExpairedDate());
        task.setSituation(taskDto.getSituation());

        if (taskDto.getAssignUserIds() != null) {
            List<AppUser> appUserList = appUserRepository.findAllById(taskDto.getAssignUserIds());
            task.setAddedAppUsers(appUserList);
        }
        List<AppUser> appUserList = appUserRepository.findAllById(taskDto.getAssignUserIds());
        for (AppUser appUser : appUserList) {
            String message = appUser.getName() + "You have new Task" + task.getHeader();
            emailService.taskEmailNotificationSender(appUser.getEmail(), "New Task",message);
        }
        return taskRepository.save(task);
    }
    public Task updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task Not Found!"));

        task.setDescription(taskDto.getDescription());
        task.setHeader(taskDto.getHeader());
        task.setExpairedDate(taskDto.getExpairedDate());
        taskDto.setSituation(taskDto.getSituation());

        if (taskDto.getAssignUserIds() != null) {
            List<AppUser> asssignAppUsers = appUserRepository.findAllById(taskDto.getAssignUserIds());
            task.setAddedAppUsers(asssignAppUsers);
        }
        return taskRepository.save(task);
    }
    public List<Task> getAllTask(Long userId) {
        return taskRepository.findByUserId(userId);
    }
    public Task assignTask(Long taskId, List<Long> userIds) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task Not Found!"));

        List<AppUser> assignedAppUser = appUserRepository.findAllById(userIds);
        task.setAddedAppUsers(assignedAppUser);
        return taskRepository.save(task);
    }

    public Task updateTaskStatus(Long id, TaskDto taskDto) {
        Task existTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task Not Found!"));

        Status oldStatus = existTask.getStatus();
        Status newStatus = taskDto.getNewStatus();

        existTask.setStatus(newStatus);
        taskRepository.save(existTask);

        if (!oldStatus.equals(newStatus)) {
            String message= "Task's status " + taskDto.getOldStatus() + "'from " + taskDto.getNewStatus() + "'to update.";
            for (AppUser appUser : existTask.getAddedAppUsers()) {
                emailService.taskEmailNotificationSender(appUser.getEmail(), "Task's status update", message);
            }
        }

        return existTask;
    }

}
