
package com.altimetrik.def.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ConcourseURL",
    "ConcourseUsername",
    "ConcoursePassword"
})
public class Concourse {

    @JsonProperty("ConcourseURL")
    private String concourseURL;
    @JsonProperty("ConcourseUsername")
    private String concourseUsername;
    @JsonProperty("ConcoursePassword")
    private String concoursePassword;

    @JsonProperty("ConcourseURL")
    public String getConcourseURL() {
        return concourseURL;
    }

    @JsonProperty("ConcourseURL")
    public void setConcourseURL(String concourseURL) {
        this.concourseURL = concourseURL;
    }

    @JsonProperty("ConcourseUsername")
    public String getConcourseUsername() {
        return concourseUsername;
    }

    @JsonProperty("ConcourseUsername")
    public void setConcourseUsername(String concourseUsername) {
        this.concourseUsername = concourseUsername;
    }

    @JsonProperty("ConcoursePassword")
    public String getConcoursePassword() {
        return concoursePassword;
    }

    @JsonProperty("ConcoursePassword")
    public void setConcoursePassword(String concoursePassword) {
        this.concoursePassword = concoursePassword;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
