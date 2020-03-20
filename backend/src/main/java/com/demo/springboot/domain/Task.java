package com.demo.springboot.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @AllArgsConstructor
// @NoArgsConstructor
// @Data
@Table
public class Task {

    @PrimaryKey
    @Id
    private String id;

    @Column(value = "content")
    private String content;

    private String status;

    public Task() {}

    public Task(String content) {
		this.id = UUID.randomUUID().toString();
        this.content = content;
        this.status = "Pending";
    }

    public Task(String id, String content, String status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }

    // public Task(String id, String content) {
	// 	   this.id = id;
    //     this.content = content;
    //     this.status = "Pending";
    // }
    
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