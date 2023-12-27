import java.util.List;

public class RedBlackTree {
    private static final int RED = 0;
    private static final int BLACK = 1;

    private Node root;

    private static class Node {
        int data;
        Node parent;
        Node left;
        Node right;
        int color;
        int height;
        int balanceFactor;

        private Node(int data, int color) {
            this.data = data;
            this.color = color;
            this.height = 1;
            this.balanceFactor = 0;
        }
    }

    public void insertList(List<Integer> values) {
        for (Integer value : values) {
            insert(value);
        }
        printTreeWithBalanceFactors();
        finishRB();
    }

    private void insert(int data) {
        Node newNode = new Node(data, RED);
        if (root == null) {
            root = newNode;
            root.color = BLACK;
        } else {
            insertNode(root, newNode);
            fixInsert(newNode);
        }
    }

    private void insertNode(Node root, Node newNode) {
        if (newNode.data < root.data) {
            if (root.left == null) {
                root.left = newNode;
                newNode.parent = root;
            } else {
                insertNode(root.left, newNode);
            }
        } else if (newNode.data > root.data) {
            if (root.right == null) {
                root.right = newNode;
                newNode.parent = root;
            } else {
                insertNode(root.right, newNode);
            }
        }

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        root.balanceFactor = getBalanceFactor(root);
    }

    private void fixInsert(Node newNode) {
        Node uncle;
        while (newNode != root && newNode.parent.color == RED) {
            if (newNode.parent == newNode.parent.parent.left) {
                uncle = newNode.parent.parent.right;
                if (uncle != null && uncle.color == RED) {
                    newNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode.parent.parent.color = RED;
                    newNode = newNode.parent.parent;
                } else {
                    if (newNode == newNode.parent.right) {
                        newNode = newNode.parent;
                        rotateLeft(newNode);
                    }
                    newNode.parent.color = BLACK;
                    newNode.parent.parent.color = RED;
                    rotateRight(newNode.parent.parent);
                }
            } else {
                uncle = newNode.parent.parent.left;
                if (uncle != null && uncle.color == RED) {
                    newNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode.parent.parent.color = RED;
                    newNode = newNode.parent.parent;
                } else {
                    if (newNode == newNode.parent.left) {
                        newNode = newNode.parent;
                        rotateRight(newNode);
                    }
                    newNode.parent.color = BLACK;
                    newNode.parent.parent.color = RED;
                    rotateLeft(newNode.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }

    private void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        rightChild.left = node;
        node.parent = rightChild;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        rightChild.height = Math.max(getHeight(rightChild.left), getHeight(rightChild.right)) + 1;
    }

    private void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        leftChild.right = node;
        node.parent = leftChild;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        leftChild.height = Math.max(getHeight(leftChild.left), getHeight(leftChild.right)) + 1;
    }

    private void preOrder() {
        System.out.println(Strings.PRE_ORDER_RB);
        preOrderTraversal(root);
        System.out.println(Strings.DIVIDER);
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.println(Strings.VALUE + node.data + Strings.HEIGHT + getHeight(node) + Strings.BALANCE_FACTOR + getBalanceFactor(node));
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    private void inOrder() {
        System.out.println(Strings.IN_ORDER_RB);
        inOrderTraversal(root);
        System.out.println(Strings.DIVIDER);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(Strings.VALUE + node.data + Strings.HEIGHT + getHeight(node) + Strings.BALANCE_FACTOR + getBalanceFactor(node));
            inOrderTraversal(node.right);
        }
    }

    private void postOrder() {
        System.out.println(Strings.POST_ORDER_RB);
        postOrderTraversal(root);
        System.out.println(Strings.DIVIDER);
    }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(Strings.VALUE + node.data + Strings.HEIGHT + getHeight(node) + Strings.BALANCE_FACTOR + getBalanceFactor(node));
        }
    }

    private void finishRB() {
        preOrder();
        System.out.println(Strings.DIVIDER);
        inOrder();
        System.out.println(Strings.DIVIDER);
        postOrder();
        System.out.println(Strings.DIVIDER);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private void printTreeWithBalanceFactors() {
        printTreeWithBalanceFactors(root, 0);
    }

    private void printTreeWithBalanceFactors(Node node, int level) {
        if (node != null) {
            printTreeWithBalanceFactors(node.right, level + 1);

            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }

            String color = (node.color == BLACK) ? "B" : "R";
            System.out.println(node.data + "(" + color + ")");

            printTreeWithBalanceFactors(node.left, level + 1);
        }
    }
}