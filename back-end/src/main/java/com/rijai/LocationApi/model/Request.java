package com.rijai.LocationApi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reqId;

    private Long dogId;

    private Long userId;

    private String reqName;
    private String reqContact;
    private String reqMessage;
    private String reqStatus;

    public Request() {
    }

    public Request(Long reqId, Long dogId, Long userId, String reqName, String reqContact, String reqMessage, String reqStatus) {
        this.reqId = reqId;
        this.dogId = dogId;
        this.userId = userId;
        this.reqName = reqName;
        this.reqContact = reqContact;
        this.reqMessage = reqMessage;
        this.reqStatus = reqStatus;
    }

    public Long getDogId() {
        return dogId;
    }

    public void setDogId(Long dogId) {
        this.dogId = dogId;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public String getReqContact() {
        return reqContact;
    }

    public void setReqContact(String reqContact) {
        this.reqContact = reqContact;
    }

    public String getReqMessage() {
        return reqMessage;
    }

    public void setReqMessage(String reqMessage) {
        this.reqMessage = reqMessage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.reqId);
        hash = 79 * hash + Objects.hashCode(this.dogId);
        hash = 79 * hash + Objects.hashCode(this.userId);
        hash = 79 * hash + Objects.hashCode(this.reqName);
        hash = 79 * hash + Objects.hashCode(this.reqContact);
        hash = 79 * hash + Objects.hashCode(this.reqMessage);
        hash = 79 * hash + Objects.hashCode(this.reqStatus);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Request other = (Request) obj;
        if (!Objects.equals(this.reqStatus, other.reqStatus)) {
            return false;
        }
        if (!Objects.equals(this.reqMessage, other.reqMessage)) {
            return false;
        }
        if (!Objects.equals(this.reqContact, other.reqContact)) {
            return false;
        }
        if (!Objects.equals(this.reqName, other.reqName)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.dogId, other.dogId)) {
            return false;
        }
        return Objects.equals(this.reqId, other.reqId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Request {");
        sb.append("id=").append(reqId);
        sb.append(", dog=").append(dogId);
        sb.append(", userid=").append(userId);
        sb.append(", name='").append(reqName).append('\'');
        sb.append(", contact='").append(reqContact).append('\'');
        sb.append(", message=").append(reqMessage);
        sb.append(", status=").append(reqStatus);
        sb.append('}');
        return sb.toString();
    }

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }



}