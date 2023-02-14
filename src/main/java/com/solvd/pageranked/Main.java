package com.solvd.pageranked;

import com.solvd.pageranked.dao.INodes;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.NodesDAO;
import com.solvd.pageranked.models.Matrix;
import com.solvd.pageranked.services.HTMLParser;
import com.solvd.pageranked.services.JacksonPojoToJson;
import com.solvd.pageranked.services.MatrixCreator;
import com.solvd.pageranked.services.PageRankService;
import com.solvd.pageranked.services.PageRank;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HTMLParser.HTMLParsing();
        Matrix matrix = MatrixCreator.getMyMatrix();

        INodes iNodes = new NodesDAO();
        PageRank pageRank = new PageRank(matrix);
        PageRankService pageRankService = new PageRankService(pageRank, iNodes);
        pageRankService.updatePageRank();
        pageRankService.showResults();
        
        JacksonPojoToJson.JacksonStart();
    }
}
