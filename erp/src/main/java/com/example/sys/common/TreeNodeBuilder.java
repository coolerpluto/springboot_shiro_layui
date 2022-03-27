package com.example.sys.common;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

    public static List<TreeNode> treeNodesBuild(List<TreeNode> treeNodes, Integer topPid){
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (TreeNode n1 : treeNodes){
            if (n1.getPid() == topPid){
                treeNodeList.add(n1);
            }
            for (TreeNode n2 : treeNodes){
                if (n1.getId() == n2.getPid()){
                    n1.getChildren().add(n2);
                }
            }
        }

        return treeNodeList;
    }
}
