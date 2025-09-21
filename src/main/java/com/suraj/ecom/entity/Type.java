package com.suraj.ecom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "types")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<Product> products; 
}