package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrelloBoard {
    private String id;
    private String name;
    private List<TrelloList> lists;

}