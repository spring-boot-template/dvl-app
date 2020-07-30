package com.dvlcube.utils;

import java.io.Serializable;

public interface BaseEntity<ID> {
    ID getId();
    void setId(ID id);
}
