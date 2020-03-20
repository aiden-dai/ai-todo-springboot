package com.demo.springboot.domain;

import java.util.UUID;

public class Task {

    private String id;
    
    private String content;

    private String status;

    public Task() {
        this.id = UUID.randomUUID().toString();
        this.status = "Pending";
    }

    public Task(String id, String content, String status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }
    
    public final String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }

    public final String getContent() {
        return content;
    }

    public final void setContent(String content) {
        this.content = content;
    }

    public final String getStatus() {
        return status;
    }

    public final void setStatus(String status) {
        this.status = status;
    }

}