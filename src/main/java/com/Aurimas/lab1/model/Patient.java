package com.Aurimas.lab1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Patients")

public class Patient {
    private long id;
    private String name;
    private String surname;
    private String personalCode;
	private String condition;

    public Patient(){

    }

    public Patient(String name, String surname, String personalCode, String condition){
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
		this.condition = condition;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }

    @Column(name = "surname", nullable = false)
    public String getSurname() {
        return surname;
    }
    public void setsurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "personalcode", nullable = false)
    public String getPersonalCode() {
        return personalCode;
    }
	    @Column(name = "condition", nullable = false)
    public String getCondition() {
        return condition;
    }
    public void setpersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }
	public void setCondition(String condition)
	{
		this.condition = condition;
	}

}
