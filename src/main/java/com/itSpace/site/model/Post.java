package com.itSpace.site.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title ,fullText;
    private Date createdDate;

    private int views;

    @ManyToOne
    private Employe employe;

}
