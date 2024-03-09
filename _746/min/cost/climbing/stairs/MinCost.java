package leetcode.special.number._746.min.cost.climbing.stairs;

//746. Min Cost Climbing Stairs
//        https://leetcode.com/problems/min-cost-climbing-stairs/description/?envType=daily-question&envId=2024-03-06

public class MinCost {

    public int[] cost = {0,1,2,2};

//    public int minCostClimbingStairs(int[] cost) {
//        int minCost = 0;
//        if (cost.length ==3) {
//                int i = 0;
//
//                if (cost[i + 1] < cost[i] + cost[i + 2] || cost[i + 1] <= cost[i + 2]) {
//                    minCost += cost[i + 1];
//
//                } else {
//                    minCost += cost[i + 2];
//
//                }
//                return minCost;
//            }
//
//        if (cost[0] < cost[1]) {
//            int i = 0;
//
//            for (i = 0; i < cost.length; i++) {
//
//                if (i + 2 == cost.length - 1) {
//
//                    if (cost[i + 1] < cost[i] + cost[i + 2] || cost[i + 1] <= cost[i + 2]) {
//                        minCost += cost[i + 1];
//                        minCost += cost[i];
//                    } else {
//                        minCost += cost[i + 2];
//                        minCost += cost[i];
//                    }
//                    return minCost;
//                }
//
//                if (i + 1 == cost.length - 1 || i == cost.length - 1) {
//                    minCost += cost[i];
//                    break;
//                } else if (cost[i + 1] < cost[i + 2]) {
//                    minCost += cost[i];
//                } else if (cost[i + 1] == cost[i + 2]) {
//                    minCost += cost[i];
//                    i++;
//                } else {
//                    minCost += cost[i];
//                    i++;
//                }
//            }
//        } else {
//
//            for (int i = 1; i < cost.length; i++) {
//
//
//
//                if (i + 2 == cost.length - 1) {
//
//                    if (cost[i + 1] < cost[i] + cost[i + 2] || cost[i + 1] <= cost[i + 2]) {
//                        minCost += cost[i + 1];
//                        minCost += cost[i];
//                    } else {
//
//                        minCost += cost[i + 2];
//                        minCost += cost[i];
//                    }
//                    return minCost;
//                }
//
//
//                if (i + 1 == cost.length - 1 || i == cost.length - 1) {
//                    minCost += cost[i];
//                    break;
//                } else if (cost[i + 1] < cost[i + 2]) {
//                    minCost += cost[i];
//                } else if (cost[i + 1] == cost[i + 2]) {
//                    minCost += cost[i];
//                    i++;
//                } else {
//                    minCost += cost[i];
//                    i++;
//                }
//            }
//        }
//        return minCost;
//    }


        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            for(int i=n-3;i>=0;i--){
                cost[i] += Math.min(cost[i+1],cost[i+2]);
            }
            return Math.min(cost[0],cost[1]);
        }


    public static void main(String[] args) {
        MinCost minCost = new MinCost();
        System.out.println(minCost.minCostClimbingStairs(minCost.cost));
    }
}
