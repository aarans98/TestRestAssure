package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * $table.getTableComment()
 */
@Entity
@Table(schema = "ananda", name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * $column.getColumnComment()
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * $column.getColumnComment()
     */
    @Column(name = "name")
    private String name;

    /**
     * $column.getColumnComment()
     */
    @Column(name = "major")
    private String major;

    /**
     * $column.getColumnComment()
     */
    @Column(name = "gpa")
    private Double gpa;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }
}
