package com.nmc.itschool.dto.base;

import java.io.Serializable;
import java.util.Date;

public interface BaseAuditDTO<ID extends Serializable> extends BaseReadonlyDTO<ID> {

	public String getCreatedBy();

	public void setCreatedBy(String user);

	public Date getCreatedDate();

	public void setCreatedDate(Date createdDate);

	public String getUpdatedBy();

	public void setUpdatedBy(String user);

	public Date getUpdatedDate();

	public void setUpdatedDate(Date updatedDate);

	public void setId(ID id);

}
