package com.itSpace.site.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sms;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private Employe fromEmployee;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private Employe toEmployee;
}

