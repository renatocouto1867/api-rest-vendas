package com.example.apirestvendas.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter // Cria os Getter em tempo de execução
@Setter // Cria os Setter em tempo de execução
@AllArgsConstructor // cria um construtor com todos atributos (All)
@NoArgsConstructor // cria um construtor sem atributos (No)
@Entity
@ToString
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)//para indica que no banco não pode ser vazia, mas o objeto aceita
    private Long id;
    @NotBlank
    private String titulo;
    private String descricao;
    private String ean;//codigo de barras
    private double preco;
    private int estoque;
    private String loja;
}
