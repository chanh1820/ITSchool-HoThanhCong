package com.nmc.itschool.entity.base;

import java.io.Serializable;

public interface BaseReadonlyEntity<ID extends Serializable> {

    public ID getId();

}
