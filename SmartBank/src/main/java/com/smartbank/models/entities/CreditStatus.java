package com.smartbank.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creditId", nullable = false)
    private Credit credit;

    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    private Status status;

    private LocalDateTime dateStatus;

    @Column(columnDefinition = "TEXT")
    private String explication;

    @Column(name = "actif", nullable = false)
    private boolean actif;
}

