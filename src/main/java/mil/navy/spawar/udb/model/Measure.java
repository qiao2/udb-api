package mil.navy.spawar.udb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name="measure")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measure implements Serializable {
    @JsonProperty("id")
    @JacksonXmlProperty(localName = "task_measure_id")
    private String id;

    @JsonProperty("msrNumber")
    @JacksonXmlProperty(localName = "msr_number")
    private String msrNumber;

    @JsonProperty("msrScale")
    @JacksonXmlProperty(localName = "msr_scale")
    private String msrScale;

    @JsonProperty("msrDescription")
    @JacksonXmlProperty(localName = "msr_description")
    private String msrDescription;

    @JsonProperty("viewOrder")
    @JacksonXmlProperty(localName = "view_order")
    private Integer viewOrder;

    @JsonProperty("dateCreated")
    @JacksonXmlProperty(localName = "date_created")
    private Date dateCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
