package 이진트리;

public class BinarySearchTree<T> {
    private Node<T> root;
    int size;
//    private Comparator<? super T> compare;

    public BinarySearchTree() {}

    public BinarySearchTree(Node<T> root) {
        this.root = root;
        this.size = 1;
    }

    public Node<T> root() {
        return this.root;
    }

    public boolean add(T data) {
        Node<T> current = root;

        if (current == null) {
            this.root = new Node<>(data);
            size++;

            return true;
        } else {
            Comparable<? super T> compare = (Comparable<? super T>) data;

            while (true) {
                final var compareResult = compare.compareTo(current.data);

                if (compareResult < 0) {

                    if (current.left == null) {
                        current.left = new Node<>(data, current);
                        size++;

                        return true;
                    }

                    current = current.left;
                } else if (compareResult > 0) {

                    if (current.right == null) {
                        current.right = new Node<>(data, current);
                        size++;

                        return true;
                    }

                    current = current.right;
                } else {
                    return false;
                }
            }
        }
    }

    public boolean addRecursive(T data) {

        if (root == null) {
            this.root = new Node<>(data);
            size++;

            return true;
        } else {
            return this.addRecursive(root, data);
        }
    }

    private boolean addRecursive(Node<T> current, T data) {
        Comparable<? super T> compare = (Comparable<? super T>) data;

        final var compareResult = compare.compareTo(current.data);

        if (compareResult < 0) {

            if (current.left != null) {
                this.addRecursive(current.left, data);
            } else {
                current.left = new Node<>(data, current);
                size++;
            }
        } else if (compareResult > 0) {

            if (current.right != null) {
                this.addRecursive(current.right, data);
            } else {
                current.right = new Node<>(data, current);
                size++;
            }
        } else {
            return false;
        }

        return true;
    }

    public Node<T> find(T data) {

        if (size == 0) {
            return null;
        } else {
            Comparable<T> compare = (Comparable<T>) data;
            Node<T> current = root;

            while (true) {
                final var compareResult = compare.compareTo(current.data);

                if (compareResult < 0) {

                    if (current.left == null) {
                        return null;
                    }

                    current = current.left;
                } else if (compareResult > 0) {

                    if (current.right == null) {
                        return null;
                    }

                    current = current.right;
                } else {
                    return current;
                }
            }
        }
    }

    public Node<T> findRecursive(T data) {

        if (size == 0) {
            return null;
        } else {
            return this.findRecursive(data, this.root);
        }
    }

    private Node<T> findRecursive(T data, Node<T> current) {
        Comparable<T> compare = (Comparable<T>) data;
        final var compareResult = compare.compareTo(current.data);

        if (compareResult < 0) {
            return current.left != null ? this.findRecursive(data, current.left) : null;
        } else if (compareResult > 0) {
            return current.right != null ? this.findRecursive(data, current.right) : null;
        } else {
            return current;
        }
    }

    /**
     * 노드 삭제시
     *
     * 1) 자식 노드가 없을경우
     *  - 연결 끊기
     *
     * 2) 왼쪽/오른쪽 자식만 있을경우
     *  - 왼쪽만 있는 경우 : 왼쪽 자식으로 대체
     *  - 오른쪽만 있는 경우 : 오른쪽 자식으로 대체
     *
     * 3) 둘다 있을 경우
     *  - 우측 자손 노드중 가장 작은 노드를 찾고, 찾은 자손 노드의 우측 자식이 없을 경우 삭제 노드에 자손 노드를 연결
     *                                   찾은 자손 노드의 우측 자식이 있을 경우 삭제 노드에 자손 노드를 연결 하고, 찾은자손의 기존 부모 왼쪽 자식에 찾은 자손의 우측 자식 노드를 연결한다
     *
     *
     * @param data
     * @return
     */
    public boolean remove(T data) {
        var target = this.find(data);

        if (target != null) {
            final var parent = target.parent;
            final var left = target.left;
            final var right = target.right;
            boolean isLeftNode = parent.left == target;


            // 자식 노드가 둘다 있는 경우
            if (left != null && right != null) {
                final var lowest = this.findLowestNode(target.right);
                final var lowestRight = lowest.right;

                if (lowestRight != null) {
                    final var lowestParent = lowest.parent;
                    lowestParent.left = lowest.right;
                }

                lowest.left = target.left;
                lowest.right = target.right;

                if (isLeftNode) {
                    parent.left = lowest;
                } else {
                    parent.right = lowest;
                }
            } else if (left != null) {

                if (isLeftNode) {
                    parent.left = left;
                } else {
                    parent.right = left;
                }
            } else if (right!= null) {

                if (isLeftNode) {
                    parent.left = right;
                } else {
                    parent.right = right;
                }
            // 자식 노드가 없는 경우
            } else {

                // root 일 때
                if (parent == null) {
                    this.root = null;
                }
            }

            size--;
            return true;
        }

        return false;
    }

    public Node<T> findLowestNode(Node<T> target) {
        return target.left == null ? target : findLowestNode(target.left);
    }

    public void clear() {

    }

    public void printDepth() {

    }

    public void printBreadth() {

    }

    public void sort() {
        this.sort(true);
    }

    public void sort(boolean isAscending) {

    }
}
