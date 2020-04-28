package com.webgame.webgame.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "matrix_row")
public class MatrixRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Game game;

    @ElementCollection(targetClass=Integer.class)
    private List<Integer> list = new ArrayList<>();

    public MatrixRow(Game game) {
        this.game = game;
        Random random = new Random();
        list =  IntStream.range(0, 10)
                .boxed()
                .map(s -> { return new Integer(random.nextInt(100));})
                .collect(Collectors.toList());
        System.out.println();
    }
}