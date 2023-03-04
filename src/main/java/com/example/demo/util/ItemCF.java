package com.example.demo.util;

import java.util.*;

public class ItemCF {

    // 物品相似度矩阵
    private static Map<Integer, Map<Integer, Double>> itemSimMatrix = new HashMap<>();

    /**
     * 计算物品相似度矩阵
     * @param userItems 用户物品评分矩阵
     */
    public static void calcItemSimMatrix(Map<Integer, Map<Integer, Double>> userItems) {
        // 1. 统计物品被评分的次数
        Map<Integer, Integer> itemCntMap = new HashMap<>();
        for (Map<Integer, Double> itemMap : userItems.values()) {
            for (int itemId : itemMap.keySet()) {
                itemCntMap.put(itemId, itemCntMap.getOrDefault(itemId, 0) + 1);
            }
        }

        // 2. 计算物品相似度矩阵
        for (Map<Integer, Double> itemMap1 : userItems.values()) {
            for (int itemId1 : itemMap1.keySet()) {
                for (int itemId2 : itemMap1.keySet()) {
                    if (itemId1 == itemId2) {
                        continue;
                    }
                    double simVal = itemMap1.get(itemId1) * itemMap1.get(itemId2) / Math.sqrt(itemCntMap.get(itemId1) * itemCntMap.get(itemId2));
                    Map<Integer, Double> simMap = itemSimMatrix.getOrDefault(itemId1, new HashMap<>());
                    simMap.put(itemId2, simMap.getOrDefault(itemId2, 0.0) + simVal);
                    itemSimMatrix.put(itemId1, simMap);
                }
            }
        }
    }

    /**
     * 获取物品相似度
     * @param itemId1 物品1的ID
     * @param itemId2 物品2的ID
     * @return 相似度值
     */
    public static double getItemSim(int itemId1, int itemId2) {
        if (!itemSimMatrix.containsKey(itemId1) || !itemSimMatrix.get(itemId1).containsKey(itemId2)) {
            return 0.0;
        }
        return itemSimMatrix.get(itemId1).get(itemId2);
    }

    /**
     * 计算用户的推荐列表
     * @param userId 用户ID
     * @param itemCnt 推荐物品的数量
     * @param userItems 用户物品评分矩阵
     * @return 推荐物品的列表
     */
    public static List<Integer> recommendItems(int userId, int itemCnt, Map<Integer, Map<Integer, Double>> userItems) {
        Map<Integer, Double> scoreMap = new HashMap<>();
        Map<Integer, Double> weightMap = new HashMap<>();

        // 1. 计算用户对物品的评分加权和以及物品相似度加权和
        for (int itemId : userItems.get(userId).keySet()) {
            for (int simItemId : itemSimMatrix.get(itemId).keySet()) {
                if (userItems.get(userId).containsKey(simItemId)) {
                    continue;
                }
                double simVal = getItemSim(itemId, simItemId);
                double score = userItems.get(userId).get(itemId);
                scoreMap.put(simItemId, scoreMap.getOrDefault(simItemId, 0.0) + score * simVal);
                weightMap.put(simItemId, weightMap.getOrDefault(simItemId, 0.0) + simVal);
            }
        }

        // 2. 计算推荐物品的得分
        List<Integer> itemList = new ArrayList<>(scoreMap.keySet());
        Collections.sort(itemList, (o1, o2) -> Double.compare(scoreMap.get(o2) / weightMap.get(o2), scoreMap.get(o1) / weightMap.get(o1)));
        return itemList.subList(0, Math.min(itemCnt, itemList.size()));
    }
}