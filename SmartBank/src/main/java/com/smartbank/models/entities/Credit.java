package com.smartbank.models.entities;

import com.smartbank.models.enums.Civilite;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "credits")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Le projet ne peut pas être vide")
    @Column(length = 50)
    private String projet;

    @NotBlank(message = "La fonction ne peut pas être vide")
    @Column(length = 50)
    private String fonction;

    @PositiveOrZero(message = "Le montant doit être un nombre entier positif ou zéro")
    private Long montant;

    @PositiveOrZero(message = "La durée doit être un nombre entier positif ou zéro")
    private Long duree;

    @DecimalMin(value = "0.0", inclusive = true, message = "La mensualité doit être un nombre positif")
    @Digits(integer = 10, fraction = 2, message = "La mensualité doit être un nombre avec au maximum deux décimales")
    private Double mensualite;

    @Email(message = "L'adresse e-mail doit être valide")
    @NotBlank(message = "L'adresse e-mail ne peut pas être vide")
    private String email;

    @NotBlank(message = "Le numéro de téléphone ne peut pas être vide")
    private String telephone;

    @Enumerated(EnumType.STRING)
    private Civilite civilite;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Column(length = 25)
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide")
    @Column(length = 30)
    private String prenom;

    @Pattern(regexp = "^[A-Za-z]{2}\\d{5,6}$", message = "Le numéro CIN doit contenir deux lettres suivies de 5 ou 6 chiffres")
    private String numeroCin;

    @NotNull(message = "La date de naissance ne peut pas être vide")
    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateNaissance;

    @NotNull(message = "La date d'embauche ne peut pas être vide")
    @PastOrPresent(message = "La date d'embauche doit être aujourd'hui ou dans le passé")
    private LocalDate dateEmbauche;

    @DecimalMin(value = "0.0", inclusive = true, message = "Le revenu doit être un nombre positif")
    @Digits(integer = 10, fraction = 2, message = "Le revenu doit être un nombre avec au maximum deux décimales")
    private Double revenu;

    private boolean creditEncours;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<CreditStatus> creditStatus= new ArrayList<>();;
}
