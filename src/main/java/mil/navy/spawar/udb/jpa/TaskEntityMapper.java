package mil.navy.spawar.udb.jpa;
import mil.navy.spawar.udb.model.Measure;
import mil.navy.spawar.udb.model.Reference;
import mil.navy.spawar.udb.model.Task;

import java.util.Objects;
import java.util.stream.Collectors;

public class TaskEntityMapper {
    public static MeasureEntity getMeasureEntity(Measure measure, TaskEntity taskEntity) {
        MeasureEntity entity = new MeasureEntity();
        entity.setId(measure.getId());
        entity.setTask(taskEntity);
        entity.setMsrNumber(measure.getMsrNumber());
        entity.setMsrScale(measure.getMsrScale());
        entity.setMsrDescription(measure.getMsrDescription());
        entity.setViewOrder(measure.getViewOrder());
        entity.setDateCreated(measure.getDateCreated());
        return entity;
    }
    public static Measure getMeasure(MeasureEntity measureEntity) {
        Measure dto = new Measure();
        dto.setId(measureEntity.getId());
        dto.setMsrNumber(measureEntity.getMsrNumber());
        dto.setMsrScale(measureEntity.getMsrScale());
        dto.setMsrDescription(measureEntity.getMsrDescription());
        dto.setViewOrder(measureEntity.getViewOrder());
        dto.setDateCreated(measureEntity.getDateCreated());
        return dto;
    }

    public static ReferenceEntity getReferenceEntity(Reference reference, TaskEntity taskEntity) {
        ReferenceEntity entity = new ReferenceEntity();
        entity.setId(reference.getId());
        entity.setTask(taskEntity);
        entity.setPubNumber(reference.getPubNumber());
        entity.setDateCreated(reference.getDateCreated());
        return entity;
    }
    public static Reference getReference(ReferenceEntity referenceEntity) {
        Reference dto = new Reference();
        dto.setId(referenceEntity.getId());
        dto.setPubNumber(referenceEntity.getPubNumber());
        dto.setDateCreated(referenceEntity.getDateCreated());
        return dto;
    }

    public static TaskEntity getTaskEntity(Task task, Boolean newTask) {
        TaskEntity entity = new TaskEntity();
        if (!newTask) {
            entity.setId(Integer.parseInt(task.getId()));
        }
        entity.setTaskType(task.getTaskType());
        entity.setUrgentCrFlag(task.getUrgentCrFlag());
        entity.setTaskDescription(task.getTaskDescription());
        entity.setTaskPrefix(task.getTaskPrefix());
        entity.setTaskNumber(task.getTaskNumber());
        entity.setTaskName(task.getTaskName());
        entity.setTaskId(task.getTaskId());
        entity.setUserId(task.getUserId());
        entity.setDateCreated(task.getDateCreated());
        entity.setApprovalDate(task.getApprovalDate());
        entity.setTaskNote(task.getTaskNote());
        if (!Objects.isNull(task.getMeasures())) {
            entity.setMeasures(task.getMeasures().stream().map(measure -> getMeasureEntity(measure, entity)).collect(Collectors.toList()));
        }
        if (!Objects.isNull(task.getReferences())) {
            entity.setReferences(task.getReferences().stream().map(reference -> getReferenceEntity(reference, entity)).collect(Collectors.toList()));
        }
        return entity;
    }

    public static TaskEntity getTaskEntity(Task task) {
        return (getTaskEntity(task, false));
    }

    public static TaskEntity createNewTaskEntity(Task task) {
        return (getTaskEntity(task, true));
    }

    public static Task getTask(TaskEntity taskEntity) {
        Task dto = new Task();
        dto.setId(taskEntity.getId().toString());
        dto.setTaskType(taskEntity.getTaskType());
        dto.setUrgentCrFlag(taskEntity.getUrgentCrFlag());
        dto.setTaskDescription(taskEntity.getTaskDescription());
        dto.setTaskPrefix(taskEntity.getTaskPrefix());
        dto.setTaskNumber(taskEntity.getTaskNumber());
        dto.setTaskId(taskEntity.getTaskId());
        dto.setTaskName(taskEntity.getTaskName());
        dto.setUserId(taskEntity.getUserId());
        dto.setDateCreated(taskEntity.getDateCreated());
        dto.setApprovalDate(taskEntity.getApprovalDate());
        dto.setTaskNote(taskEntity.getTaskNote());
        if (!Objects.isNull(taskEntity.getMeasures())) {
            dto.setMeasures(taskEntity.getMeasures().stream().map(entity -> getMeasure(entity)).collect(Collectors.toList()));
        }
        if (!Objects.isNull(taskEntity.getReferences())) {
            dto.setReferences(taskEntity.getReferences().stream().map(entity -> getReference(entity)).collect(Collectors.toList()));
        }
        return dto;
    }
}
