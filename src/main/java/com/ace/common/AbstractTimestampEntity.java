package com.ace.common;

/**
 * Created by bamboo on 17-11-25.
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.sql.Timestamp;

abstract class AbstractTimestampEntity implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @CreatedDate
    @Column(name = "gmt_created", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    // DEFAULT CURRENT_TIMESTAMP MUST be defined in MySQL 5.7
            //    @Temporal(TemporalType.TIMESTAMP) // even with temporal TIMESTAMP type mysql still uses DATATIME type, and this the same for java.sql.Timestamp type
            Timestamp gmtCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @LastModifiedDate
    @Column(name = "gmt_updated", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    //    @Temporal(TemporalType.TIMESTAMP)
            Timestamp gmtUpdated;


    @PreUpdate
    @PrePersist
    void updateTimeStamps() {
        this.gmtUpdated = new Timestamp(System.currentTimeMillis());
        if (null == this.gmtCreated) {
            this.gmtCreated = this.gmtUpdated;
        }
    }
}