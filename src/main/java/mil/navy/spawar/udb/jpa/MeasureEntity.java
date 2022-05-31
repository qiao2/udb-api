package mil.navy.spawar.udb.jpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "measure")
public class MeasureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer dbId;

    private String id;

    @ManyToOne(targetEntity = TaskEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    private String msrNumber;

    private String msrScale;

    @Column(length=4000)
    private String msrDescription;

    private Integer viewOrder;

    private Date dateCreated;

    public Integer getDbId() {
        return dbId;
    }

    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public String getMsrNumber() {
        return msrNumber;
    }

    public void setMsrNumber(String msrNumber) {
        this.msrNumber = msrNumber;
    }

    public String getMsrScale() {
        return msrScale;
    }

    public void setMsrScale(String msrScale) {
        this.msrScale = msrScale;
    }

    public String getMsrDescription() {
        return msrDescription;
    }

    public void setMsrDescription(String msrDescription) {
        this.msrDescription = msrDescription;
    }

    public Integer getViewOrder() {
        return viewOrder;
    }

    public void setViewOrder(Integer viewOrder) {
        this.viewOrder = viewOrder;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
