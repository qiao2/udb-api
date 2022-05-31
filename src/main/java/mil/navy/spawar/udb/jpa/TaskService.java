package mil.navy.spawar.udb.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepo;

    public TaskEntity updateTask(Integer id, TaskEntity task) {
        Optional<TaskEntity> originalTask = taskRepo.findById(id);

        taskRepo.save(task);
        Optional<TaskEntity> updatedTask = taskRepo.findById(id);
        return updatedTask.get();
    }
}
