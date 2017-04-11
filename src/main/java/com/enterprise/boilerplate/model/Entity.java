package com.enterprise.boilerplate.model;

import javax.persistence.Id;

@javax.persistence.Entity
public class Entity {

    @Id
    private Long id;
    private String name;

}
