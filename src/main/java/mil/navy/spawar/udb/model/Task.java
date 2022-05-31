package mil.navy.spawar.udb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name="task")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task implements Serializable {
    @JsonProperty("id")
    String id;

    @JsonProperty("taskType")
    @JacksonXmlProperty(localName = "task_type")
    String taskType;

    @JsonProperty("urgentCrFlag")
    @JacksonXmlProperty(localName = "urgent_cr_flag")
    Boolean urgentCrFlag;

    @JsonProperty("taskDescription")
    @JacksonXmlProperty(localName = "task_description")
    String taskDescription;

    @JsonProperty("taskPrefix")
    @JacksonXmlProperty(localName = "task_prefix")
    String taskPrefix;

    @JsonProperty("taskNumber")
    @JacksonXmlProperty(localName = "task_number")
    String taskNumber;

    @JsonProperty("taskId")
    @JacksonXmlProperty(localName = "taskid")
    String taskId;

    @JsonProperty("taskName")
    @JacksonXmlProperty(localName = "task_name")
    String taskName;

    @JsonProperty("userId")
    @JacksonXmlProperty(localName = "user_id")
    String userId;

    @JsonProperty("dateCreated")
    @JacksonXmlProperty(localName = "date_created")
    Date dateCreated;

    @JsonProperty("approvalDate")
    @JacksonXmlProperty(localName = "approval_date")
    Date approvalDate;

    @JsonProperty("taskNote")
    @JacksonXmlProperty(localName = "task_note")
    String taskNote;

    @JsonProperty("measures")
    List<Measure> measures;

    @JsonProperty("references")
    List<Reference> references;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    @JsonIgnore
    public String getSummaryOutput() {
        String output = String.format("ID: %-7s | ", this.getId()) +
            String.format("Task ID: %-7s | ", this.getTaskId()) +
            String.format("%s %s %s | ", this.getTaskPrefix(), this.getTaskNumber(), this.getTaskName()) +
            String.format("Measures: %s | ", measures.stream().map(n -> n.getId()).collect(Collectors.joining(", ", "[", "]"))) +
            String.format("References: %s", references.stream().map(n -> n.getId()).collect(Collectors.joining(", ", "[", "]")));
        return output;
    }

}
