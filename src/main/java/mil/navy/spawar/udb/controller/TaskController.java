package mil.navy.spawar.udb.controller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import mil.navy.spawar.udb.jpa.*;
import mil.navy.spawar.udb.model.RowSet;
import mil.navy.spawar.udb.model.Task;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private MeasureRepository measureRepo;
    @Autowired
    private ReferenceRepository refRepo;
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/import",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> importTaskReport(@RequestParam("inputFile") MultipartFile inputFile) {
        try {
            if (inputFile.isEmpty()) {
                return new ResponseEntity("Please select a file to upload", HttpStatus.BAD_REQUEST);
            }
            else {
                byte[] bytes = inputFile.getBytes();
                XmlMapper xmlMapper = new XmlMapper();
                String xml = new String(bytes, StandardCharsets.UTF_8);
                RowSet value = xmlMapper.readValue(xml, RowSet.class);

                taskRepo.deleteAll();

                List<Task> tasks = value.getData();
                taskRepo.saveAll(tasks.stream().map(task -> TaskEntityMapper.createNewTaskEntity(task)).collect(Collectors.toList()));

                List<TaskEntity> importedTasks = taskRepo.findAll();
                String msg = String.format("Successfully imported %d tasks", importedTasks.size());
                return new ResponseEntity(msg, HttpStatus.OK);
            }
        }
        catch (IOException e) {
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTaskById(@PathVariable("id") Integer id) {
        try {
            if (!taskRepo.existsById(id)) {
                return new ResponseEntity("Cannot find task with that ID", HttpStatus.BAD_REQUEST);
            }
            else {
                Optional<TaskEntity> task = taskRepo.findById(id);
                return new ResponseEntity(TaskEntityMapper.getTask(task.get()), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTasks(
            @RequestParam(name = "summaryOutput", required = false) Boolean summaryOutput,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "nextToken", required = false) String nextToken,
            @RequestParam(name = "limit", required = false) Integer limit) {
        try {
            // validate inputs
            if (summaryOutput == null) summaryOutput = false;
            if (limit == null) limit = 5000;
            if (!StringUtils.isNumeric(nextToken)) nextToken = "0";

            // fetch 1 over the limit to get ID of next token
            Pageable pageable = PageRequest.of(0, limit + 1);
            List<TaskEntity> tasks = taskRepo.findTasksWithPagination(name, Integer.parseInt(nextToken), pageable);
            List<Task> rows = tasks.stream().map(entity -> TaskEntityMapper.getTask(entity)).collect(Collectors.toList());

            RowSet output = new RowSet();
            if (rows.size() > limit) {
                output.setNextToken(rows.get(limit).getId());
                rows = rows.subList(0, limit);
            }
            if (summaryOutput) {
                output.setSummary(rows.stream().map(u -> u.getSummaryOutput()).collect(Collectors.toList()));
            }
            else {
                output.setData(rows);
            }

            return new ResponseEntity(output, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            TaskEntity entity = TaskEntityMapper.createNewTaskEntity(task);
            entity.setDateCreated(new Date());
            TaskEntity newEntity = taskRepo.save(entity);
            return new ResponseEntity(TaskEntityMapper.getTask(newEntity), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        try {
            if (!taskRepo.existsById(id)) {
                return new ResponseEntity("Cannot update task; ID not found", HttpStatus.BAD_REQUEST);
            }
            else {
                task.setId(id.toString());
                TaskEntity entity = TaskEntityMapper.getTaskEntity(task);
                taskRepo.save(entity);
                Optional<TaskEntity> updatedTask = taskRepo.findById(id);
                return new ResponseEntity(TaskEntityMapper.getTask(updatedTask.get()), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTask(@PathVariable("id") Integer id) {
        try {
            if (!taskRepo.existsById(id)) {
                return new ResponseEntity("Cannot delete task; ID not found", HttpStatus.BAD_REQUEST);
            }
            else {
                Optional<TaskEntity> task = taskRepo.findById(id);
                taskRepo.delete(task.get());
                return new ResponseEntity(String.format("Deleted task"), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }
}
