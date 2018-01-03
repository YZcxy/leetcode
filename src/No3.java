/**
 * max-points-on-a-line
 *
 * Given n points on a 2D plane,
 * find the maximum number of points
 * that lie on the same straight line.
 *
 * 二维坐标中最多多少个点连成直线
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 逻辑概述：
 * 通过穷举所有组合，判断共线的最多有几个点。
 * 通过斜率比较，相同的则为共线
 * 斜率采用GCD算法
 */
public class No3 {
    public int maxPoints(Point[] points) {
        int result = 0;
        if (points == null) return result;
        if (points.length <= 2) return points.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            Point a = points[i];
            HashMap<String,Integer> map = new HashMap<>();
            int addIndex = 0;
            //求出所有斜率，相同即为同一条线
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                Point b = points[j];
                if (isSame(a,b)) {
                    addIndex++;
                } else {
                    String tempSlope = slope(a,b);
                    if(map.get(tempSlope) != null) {
                        map.put(tempSlope,map.get(tempSlope) + 1);
                    } else {
                        map.put(tempSlope,1);
                    }
                }
            }
            //同一条线最多点的存入max
            if (map.size() != 0) {
                for (Map.Entry<String,Integer> entry : map.entrySet()) {
                    if ((entry.getValue() + 1 + addIndex) > max) {
                        max = entry.getValue() + 1 + addIndex;
                    }
                }
            } else {
                max = addIndex + 1;
            }
        }
        return max;
    }

    private boolean isSame(Point a,Point b) {
        if ((a.x == b.x) && (a.y == b.y)) {
            return true;
        }
        return false;
    }

    /**
     * 求两个点的斜率
     */
    private String slope(Point a, Point b){
        int ax = a.x;
        int ay = a.y;
        int bx = b.x;
        int by = b.y;
        //flag用于标记正负，true为正，false为负
        boolean flag = true;
        int num1 = ax - bx;
        int num2 = ay - by;
        if (num1 == 0) {
            return "|";
        }
        if (num2 == 0) {
            return "—";
        }
        //同正同负时为正
        if ((num1>0 && num2>0) || (num1<0 && num2<0)) {
            flag = true;
        }else {
            flag = false;
        }
        //将负转换为正
        if (num1 < 0){
            num1 = 0 - num1;
        }
        if (num2 < 0){
            num2 = 0 - num2;
        }

        int gcdNum = gcd(num1,num2);
        String result = num1/gcdNum + "/" + num2/gcdNum;
        if (!flag) {
            result = result + "-";
        }
        return result;
    }

    /**
     * 欧几里德算法，计算两个整数a,b的最大公约数
     */
    private int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return gcd(num2,num1%num2);
    }
}
