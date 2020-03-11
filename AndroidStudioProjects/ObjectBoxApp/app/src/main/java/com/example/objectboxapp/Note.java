package com.example.objectboxapp;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Note {

    @Id long id;

    String text;

    Date createdAt;
}
