package com.solvd.pageranked.services;

import com.solvd.pageranked.dao.INodes;
import com.solvd.pageranked.models.Nodes;

import java.util.List;

public class PageRankService {
    private final PageRank pageRank;
    private final INodes iNodes;

    public PageRankService(PageRank pageRank, INodes iNodes) {
        this.pageRank = pageRank;
        this.iNodes = iNodes;

    }

    public void updatePageRank() {
        double[] pageRankResults = pageRank.calculate();
        for (int i = 0; i < pageRankResults.length; i++) {
            Nodes byId = iNodes.getById(i + 1);
            byId.setPageRank(pageRankResults[i]);
            iNodes.updateNode(byId);

        }
    }

    public void showResults() {
        List<Nodes> allNodes = iNodes.getAllNodes();
        System.out.print("\nPage Ranks of the Websites: \n");
        for (Nodes node : allNodes) {
            System.out.printf("Page Rank of " + node.getName() + " is: " + node.getPageRank() + "\n");
        }
    }

}
