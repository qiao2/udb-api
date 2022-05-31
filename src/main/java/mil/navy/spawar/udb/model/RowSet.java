package mil.navy.spawar.udb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JacksonXmlRootElement(localName="rowset")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RowSet implements Serializable {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName="row")
    List<Task> data;
    List<String> summary;
    Map<String, Object> paging;

    public RowSet() {
        paging = new HashMap<>();
        paging.put("nextToken", null);
    }

    public List<Task> getData() {
        return data;
    }

    public void setData(List<Task> rowSet) {
        this.data = rowSet;
    }

    public List<String> getSummary() {
        return summary;
    }

    public void setSummary(List<String> summary) {
        this.summary = summary;
    }

    public Map<String, Object> getPaging() {
        return paging;
    }

    public void setPaging(Map<String, Object> paging) {
        this.paging = paging;
    }

    public void setNextToken(String token) {
        paging.put("nextToken", token);
    }
}
