package leetcode;

import java.util.*;

public class LC126 {
    public int trapRainWater(int[][] heightMap) {
        int l = heightMap.length;
        int w = heightMap[0].length;
        Map<Integer, int[][]> map = new HashMap();
        Map<Integer, List<List<Integer>>> holeMap = new HashMap();
        generateSnapShot(heightMap, l, w, map, holeMap);
        int count = 0;
        for (int i = 0; i<map.size(); i++) {
            count += getLvlCount(holeMap.get(i), l, w, map.get(i));
        }
        return count;
    }

    private void generateSnapShot(int[][] heightMap, int l, int w, Map<Integer, int[][]> map, Map<Integer, List<List<Integer>>> holeMap) {
        Integer h = 0;
        int count = 0;
        boolean firstTime = true;
        while (firstTime || count != 0) {
            count = 0;
            int[][] snapShot = new int[l][w];
            List<List<Integer>> holeSnapShot = new LinkedList();
            for (int i=0; i<heightMap.length; i++) {
                for (int j=0; j<heightMap[0].length; j++) {
                    int value = heightMap[i][j];
                    if (value > 0) {
                        snapShot[i][j] = 1;
                        heightMap[i][j] = value - 1;
                    } else {
                        snapShot[i][j] = 0;
                        List<Integer> tmp = new LinkedList();
                        tmp.add(i);
                        tmp.add(j);
                        holeSnapShot.add(tmp);
                    }
                    count += heightMap[i][j];
                }
            }
            map.put(h, snapShot);
            holeMap.put(h, holeSnapShot);
            h++;
            firstTime = false;
        }
    }

    private int getLvlCount(List<List<Integer>> list, int l, int w, int[][] level) {
        boolean[][] visited = new boolean[l][w];
        Queue<int[]> q = new LinkedList();
        int count = 0;
        for (int i = 0; i<list.size(); i++) {
            int x = list.get(i).get(0);
            int y = list.get(i).get(1);
            if (visited[x][y]) {
                continue;
            }
            q.add(new int[]{list.get(i).get(0), list.get(i).get(1)});
            count += getCount(q, l, w, level, visited);
        }
        return count;
    }

    private int getCount(Queue<int[]> q, int l, int w, int[][] level, boolean[][] visited) {
        int count = 0;
        boolean inEdge = false;
        while(q.size() > 0) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int value = level[x][y];
            if (value == 0) {
                count++;
                if (inEdge(cur, l, w)) {
                    inEdge = true;
                } else {
                    visited[x][y] = true;
                    if (visited[x+1][y] == false) {
                        q.add(new int[]{x+1, y});
                        visited[x+1][y] = true;
                    }
                    if (visited[x][y+1] == false) {
                        q.add(new int[]{x, y+1});
                        visited[x][y+1] = true;
                    }
                    if (visited[x-1][y] == false) {
                        q.add(new int[]{x-1, y});
                        visited[x-1][y] = true;
                    }
                    if (visited[x][y-1] == false) {
                        q.add(new int[]{x, y-1});
                        visited[x][y-1] = true;
                    }
                }
            } else {
                //Do nothing
            }
        }
        if (inEdge) {
            return 0;
        }
        return count;
    }

    private boolean inEdge(int[] cur, int l, int w) {
        int x = cur[0];
        int y = cur[1];
        if (x == 0 || y == 0 || x == l-1 || y == w-1) {
            return true;
        }
        return false;
    }
}
