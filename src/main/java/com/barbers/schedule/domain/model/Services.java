package com.barbers.schedule.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_services")
@Table(name = "tb_users")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer coast;
}
