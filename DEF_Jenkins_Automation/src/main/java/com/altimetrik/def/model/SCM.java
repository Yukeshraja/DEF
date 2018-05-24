
package com.altimetrik.def.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"ScmId",
	"Name",
    "Link",
    "repoBranch",
    "CredentialID",
    "authType",
    "status"
})
@Document
public class SCM {
	
    @Id
	@JsonProperty("ScmId")
	private String scmId;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Link")
    private String link;
    @JsonProperty("repoBranch")
    private String repoBranch;
    @JsonProperty("CredentialID")
    private String credentialID;
    @JsonProperty("authType")
    private String authType;
    @JsonProperty("status")
    private String status;
    
    
    

    @JsonProperty("ScmId")
	public String getScmId() {
		return scmId;
	}

	public void setScmId(String scmId) {
		this.scmId = scmId;
	}

	@JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }
    
    

    public String getRepoBranch() {
		return repoBranch;
	}

	public void setRepoBranch(String repoBranch) {
		this.repoBranch = repoBranch;
	}

	@JsonProperty("Link")
    public String getLink() {
        return link;
    }

    @JsonProperty("Link")
    public void setLink(String link) {
        this.link = link;
    }
    
    /**
	 * @return the credentialID
	 */
	public String getCredentialID() {
		return credentialID;
	}

	/**
	 * @param credentialID the credentialID to set
	 */
	public void setCredentialID(String credentialID) {
		this.credentialID = credentialID;
	}

	/**
	 * @return the authType
	 */
	public String getAuthType() {
		return authType;
	}

	/**
	 * @param authType the authType to set
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	
	

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
