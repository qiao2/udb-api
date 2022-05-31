package mil.navy.spawar.udb.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "reference")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reference implements Serializable {
    @JsonProperty("id")
    @JacksonXmlProperty(localName = "task_reference_id")
    String id;

    @JsonProperty("pubNumber")
    @JacksonXmlProperty(localName = "pub_number")
    String pubNumber;

    @JsonProperty("dateCreated")
    @JacksonXmlProperty(localName = "date_created")
    Date dateCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPubNumber() {
        return pubNumber;
    }

    public void setPubNumber(String pubNumber) {
        this.pubNumber = pubNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
