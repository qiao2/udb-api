package mil.navy.spawar.udb.jpa;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String taskType;

    Boolean urgentCrFlag;

    @Column(length=4000)
    String taskDescription;

    String taskPrefix;

    String taskNumber;

    @Column(name="taskid")
    String taskId;

    String taskName;

    String userId;

    Date dateCreated;

    Date approvalDate;

    @Column(length=4000)
    String taskNote;

    @OneToMany(targetEntity = MeasureEntity.class, mappedBy = "task", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    List<MeasureEntity> measures;

    @OneToMany(targetEntity=ReferenceEntity.class, mappedBy="task", fetch=FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    List<ReferenceEntity> references;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Boolean getUrgentCrFlag() {
        return urgentCrFlag;
    }

    public void setUrgentCrFlag(Boolean urgentCrFlag) {
        this.urgentCrFlag = urgentCrFlag;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskPrefix() {
        return taskPrefix;
    }

    public void setTaskPrefix(String taskPrefix) {
        this.taskPrefix = taskPrefix;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }

    public List<MeasureEntity> getMeasures() {
        return measures;
    }

    public void setMeasures(List<MeasureEntity> measures) {
        this.measures = measures;
    }

    public List<ReferenceEntity> getReferences() {
        return references;
    }

    public void setReferences(List<ReferenceEntity> references) {
        this.references = references;
    }

}
