package com.altimetrik.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nmuthusamy
 *
 */
@Entity
@Table(name = "aws_user_credentials")
public class AWSCredentialDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sno")
	private Integer sno;
	@Column(name = "aws_access_key_id")
	private String aws_access_key_id;
	@Column(name = "aws_secret_access_key")
	private String aws_secret_access_key;
	
	@Column(name = "user_name")
	private String user_name;
	
	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public String getAws_access_key_id() {
		return aws_access_key_id;
	}
	public void setAws_access_key_id(String aws_access_key_id) {
		this.aws_access_key_id = aws_access_key_id;
	}
	public String getAws_secret_access_key() {
		return aws_secret_access_key;
	}
	public void setAws_secret_access_key(String aws_secret_access_key) {
		this.aws_secret_access_key = aws_secret_access_key;
	}
	
	
	
}
