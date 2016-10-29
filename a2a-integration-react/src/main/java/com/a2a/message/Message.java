package com.a2a.message;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

//import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import com.a2a.common.BooleanToStringConverter;
import com.a2a.common.HibernateJodaDateTimeConverter;


@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "PARENT_ID")
    private Long parentId;
    
    @Column(name = "DATA")
    @Lob
    private String data;
    
    @Column(name = "STATUS")
    private String status;
    
    @Column(name = "ATTEMPT_NO")
    private Long attemptNo;
    
    @Column(name = "LAST_ATTEMPT")
    @Convert(converter = HibernateJodaDateTimeConverter.class)
    private DateTime lastAttempt;
    
    @Column(name = "HEADER")
    private String header;
    
    @Column(name = "CREATED")
    @Convert(converter = HibernateJodaDateTimeConverter.class)
    private DateTime created;
    
    @Column(name = "CREATED_BY")
    private String createdBy;
    
    @Column(name = "CREATOR_TYPE")
    private String creatorType;
    
    @Column(name = "CREATOR_INSTANCE")
    private String creatorInstance;
    
    @Column(name = "UPDATED")
    @Convert(converter = HibernateJodaDateTimeConverter.class)
    private DateTime updated;
    

    @Column(name = "UPDATED_BY")
    private String updatedBy;
    
    @Column(name = "UPDATOR_TYPE")
    private String updatorType;
    
    @Column(name = "UPDATOR_INSTANCE")
    private String updatorInstance;
    
    @Column(name = "QUEUE_ID")
    private Long queueId;
    
    @Column(name = "LOCKED")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean locked;
    
    @Column(name = "TYPE")
    private String type;
    
    @Column(name = "IS_SEQ")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isSeq;
    
    @Column(name = "LOG")
    private String log;
    
    @Column(name = "REASON_CODE")
    private String reasonCode;
    
    @Column(name = "DURATION")
    private Long duration;
    
    public Message() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getParentId() {
        return parentId;
    }


    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public Long getAttemptNo() {
        return attemptNo;
    }


    public void setAttemptNo(Long attemptNo) {
        this.attemptNo = attemptNo;
    }


    public DateTime getLastAttempt() {
        return lastAttempt;
    }


    public void setLastAttempt(DateTime lastAttempt) {
        this.lastAttempt = lastAttempt;
    }


    public String getHeader() {
        return header;
    }


    public void setHeader(String header) {
        this.header = header;
    }


    public DateTime getCreated() {
        return created;
    }


    public void setCreated(DateTime created) {
        this.created = created;
    }


    public String getCreatedBy() {
        return createdBy;
    }


    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    public String getCreatorType() {
        return creatorType;
    }


    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }


    public String getCreatorInstance() {
        return creatorInstance;
    }


    public void setCreatorInstance(String creatorInstance) {
        this.creatorInstance = creatorInstance;
    }


    public DateTime getUpdated() {
        return updated;
    }


    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }


    public String getUpdatedBy() {
        return updatedBy;
    }


    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }


    public String getUpdatorType() {
        return updatorType;
    }


    public void setUpdatorType(String updatorType) {
        this.updatorType = updatorType;
    }


    public String getUpdatorInstance() {
        return updatorInstance;
    }


    public void setUpdatorInstance(String updatorInstance) {
        this.updatorInstance = updatorInstance;
    }


    public Long getQueueId() {
        return queueId;
    }


    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }


    public Boolean getLocked() {
        return locked;
    }


    public void setLocked(Boolean locked) {
        this.locked = locked;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Boolean getIsSeq() {
        return isSeq;
    }


    public void setIsSeq(Boolean isSeq) {
        this.isSeq = isSeq;
    }


    public String getLog() {
        return log;
    }


    public void setLog(String log) {
        this.log = log;
    }


    public String getReasonCode() {
        return reasonCode;
    }


    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }


    public Long getDuration() {
        return duration;
    }


    public void setDuration(Long duration) {
        this.duration = duration;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
