package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String notes;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @ManyToMany(mappedBy = "pets", cascade = CascadeType.ALL)
    private Set<Schedule> schedules = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customers owner;
}
