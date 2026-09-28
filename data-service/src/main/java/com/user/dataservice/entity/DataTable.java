package com.user.dataservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data_table")
@Builder
public class DataTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "phone_number")
    private Integer phonenumber;
    private String description;
    private String createdBy;
    private String updatedBy;
    private String location;
    }
