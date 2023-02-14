package com.solvd.pageranked.dao;

import com.solvd.pageranked.models.Relations;

import java.util.List;

public interface IRelations {
    Relations getByNodeAndLink(int nodesId, int linksId);

    List<Relations> getAllRelations();

    void deleteAllRelations();

    void addRelations(Relations relations);

    void updateRelations(Relations relations);

    void deleteRelations(int id);
}
