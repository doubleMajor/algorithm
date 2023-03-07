
```java
/*
**
* Definition for a binary tree node.
* public class TreeNode {
*     int val;
*     TreeNode left;
*     TreeNode right;
*     TreeNode(int x) { val = x; }
* }
  */


class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || original == target)
            return cloned;
        TreeNode res = getTargetCopy(original.left, cloned.left, target);
        if (res != null) {
            return res;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }
}
```
![img.png](img.png)

```java

class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || original == target)
			return cloned;
          TreeNode left=getTargetCopy(original.left,cloned.left,target);
         TreeNode right=getTargetCopy(original.right,cloned.right,target);
          if(left!=null){
              return left;
          }else{
              return right;
          }
    }
}
```

![img_1.png](img_1.png)

```java

class Solution {
   TreeNode ans = new TreeNode();
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(original == null){
            return ans;
        }
        if(original == target){
            ans = cloned;
            return ans;
        }
        
        getTargetCopy(original.left, cloned.left, target);
        getTargetCopy(original.right, cloned.right, target);
        
        return ans;
    }
}
```
![img_2.png](img_2.png)
```java


class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || original == target) {
			return cloned;
        }
		TreeNode res =  getTargetCopy(original.right, cloned.right, target);
		if (res == null) {
		    return getTargetCopy(original.left, cloned.left, target);
        }
		return res;
    }
}
```
![img_3.png](img_3.png)