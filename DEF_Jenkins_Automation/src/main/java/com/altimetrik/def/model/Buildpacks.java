
package com.altimetrik.def.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Name",
    "Buildtool",
    "PomXml"
})
public class Buildpacks {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Buildtool")
    private String buildtool;
    @JsonProperty("PomXml")
    private String pomXml;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Buildtool")
    public String getBuildtool() {
        return buildtool;
    }

    @JsonProperty("Buildtool")
    public void setBuildtool(String buildtool) {
        this.buildtool = buildtool;
    }

    @JsonProperty("pomXml")
    public String getPomXml() {
        return pomXml;
    }

    @JsonProperty("PomXml")
    public void setPomXml(String pomXml) {
        this.pomXml = pomXml;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}