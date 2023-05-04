```java
class Solution {
    public int findCenter(int[][] edges) {
        Set<Integer> set = new HashSet<>();
        for(int[] edge : edges){
            for(int node : edge){
                if(set.contains(node)) {
                    return node;
                } else {
                    set.add(node);
                }
            }
        }
        return -1;
    }
}
```
![img.png](img.png)
