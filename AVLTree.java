
class AVLTree {

    private static class Node {
        int value, height;
        Node left, right;

        Node(int value) {
            this.value = value;
            this.height = 1;
        }
    }

    Node root;

    int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    int balanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    Node rightRotation(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotation(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = balanceFactor(node);

        if (balance > 1 && value < node.left.value) {
            return rightRotation(node);
        }

        if (balance < -1 && value > node.right.value) {
            return leftRotation(node);
        }

        if (balance > 1 && value > node.left.value) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }

        if (balance < -1 && value < node.right.value) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }

        return node;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.println(Strings.VALUE + node.value + Strings.HEIGHT + node.height + Strings.BALANCE_FACTOR + balanceFactor(node));
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(Strings.VALUE + node.value + Strings.HEIGHT + node.height + Strings.BALANCE_FACTOR + balanceFactor(node));
            inOrder(node.right);
        }
    }

    void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(Strings.VALUE + node.value + Strings.HEIGHT + node.height + Strings.BALANCE_FACTOR + balanceFactor(node));
        }
    }

    String generateVisualTree(Node root, String prefix, boolean isLeft) {
        StringBuilder visualTree = new StringBuilder();

        if (root != null) {
            visualTree.append(generateVisualTree(root.right, prefix + " ", false));
            visualTree.append(prefix).append(isLeft ? "└── " : "┌── ").append(root.value).append("\n");
            visualTree.append(generateVisualTree(root.left, prefix + " ", true));
        }

        return visualTree.toString();
    }




    public void finishAVL() {
        System.out.println(Strings.PRE_ORDER_AVL);
        preOrder(root);
        System.out.println(Strings.DIVIDER);
        System.out.println(Strings.IN_ORDER_AVL);
        inOrder(root);
        System.out.println(Strings.DIVIDER);
        System.out.println(Strings.POST_ORDER_AVL);
        postOrder(root);
        System.out.println(Strings.DIVIDER);
    }


}
