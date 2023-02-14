package com.solvd.pageranked.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.LinksDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.NodesDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.RelationsDAO;
import com.solvd.pageranked.models.Relations;
import com.solvd.pageranked.models.RelationsJSON;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JacksonPojoToJson {
    public static void JacksonStart() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        NodesDAO nodesDAO = new NodesDAO();

        FileOutputStream fileOutputStreamNodes = new FileOutputStream("src/main/resources/result/nodes.json");
        mapper.writeValue(fileOutputStreamNodes, nodesDAO);
        fileOutputStreamNodes.close();

        FileOutputStream fileOutputStreamRelations = new FileOutputStream("src/main/resources/result/relations.json");
        mapper.writeValue(fileOutputStreamRelations, toName());
        fileOutputStreamRelations.close();
    }

    public static List<RelationsJSON> toName() {
        List<RelationsJSON> relationsJSONList = new ArrayList<>();
        RelationsDAO relationsDAO = new RelationsDAO();
        NodesDAO nodesDAO = new NodesDAO();
        LinksDAO linksDAO = new LinksDAO();
        for (Relations relation : relationsDAO.getAllRelations()) {
            RelationsJSON relationsJSON = new RelationsJSON(relation.getId(),
                    nodesDAO.getById(relation.getNodesId()).getName(), linksDAO.getById(relation.getLinksId()).getLinkHref());
            relationsJSONList.add(relationsJSON);
        }
        return relationsJSONList;
    }
}


