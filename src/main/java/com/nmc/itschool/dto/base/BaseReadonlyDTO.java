package com.nmc.itschool.dto.base;

import java.io.Serializable;

public interface BaseReadonlyDTO<ID extends Serializable> extends Serializable {

    public ID getId();

}
