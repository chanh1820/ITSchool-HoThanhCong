package com.nmc.itschool.dto.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EqualsAndHashCode(of = { "id" })
@ToString
public class AuditDTO<ID extends Serializable> implements BaseAuditDTO<ID> {

	private static final long serialVersionUID = 1L;

	public ID id;

	public String createdBy;

	public Date createdDate;

	public String updatedBy;

	public Date updatedDate;

}
