package github.candy.java.learn.ds.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉排序树
 */
public class BinarySortTree {

    private Node root;

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (root != null) {
            root.add(node);
        } else {
            root = node;
        }
    }

    /**
     * 前序遍历
     *
     * @return
     */
    public List<Node> preOrder() {
        if (root == null) {
            throw new RuntimeException("a tree cannot be empty");
        }
        return root.preOrder();
    }

    /**
     * 查找节点
     *
     * @param node
     * @return
     */
    public Node find(Node node) {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return root.find(node);
    }

    /**
     * 查找父节点
     *
     * @param node
     * @return
     */
    public Node findParent(Node node) {
        if (node.data == root.data) {
            return null;
        }
        return root.findParent(node);
    }

    /**
     * 删除节点
     *
     * @param node
     */
    public void del(Node node) {
        Node currentNode = find(node);
        if (currentNode == null) {
            throw new RuntimeException("un found node");
        }
        Node parentNode = findParent(node);
        // 是父节点的左子节点
        if (parentNode.left.data == currentNode.data) {
            // 删除的是叶子节点
            if (currentNode.left == null && currentNode.right == null) {
                parentNode.left = null;
                // 左子节点不为空
            }else if (currentNode.left != null && currentNode.right == null){
                parentNode.left = currentNode.left;
            }else if (currentNode.left == null && currentNode.right != null){
                parentNode.left = currentNode.right;
            }else if (currentNode.left != null && currentNode.right != null){
                Node temp = currentNode.left;
                while (temp.right != null){
                    temp = temp.right;
                }
                parentNode.left = temp;
                temp.right = currentNode.right;
            }
            // 是父节点的右子节点
        } else {
            if (currentNode.left == null && currentNode.right == null){
                parentNode.right = null;
            }else if (currentNode.left != null && currentNode.right == null){
                parentNode.right = currentNode.left;
            }else if (currentNode.left == null && currentNode.right != null){
                parentNode.right = currentNode.right;
            }else if (currentNode.left != null && currentNode.right != null){
                Node temp = currentNode.left;
                while (temp.right != null){
                    temp = temp.right;
                }
                temp = parentNode.right;
            }
        }
    }

    @Data
    static class Node {
        /**
         * 右子节点
         */
        private Node right;

        /**
         * 左子节点
         */
        private Node left;

        /**
         * 数据
         */
        private int data;

        public Node(int data) {
            this.data = data;
        }

        /**
         * 添加节点
         *
         * @param node 节点
         */
        private void add(Node node) {
            if (node.data < this.data) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
        }

        /**
         * 前序遍历
         *
         * @return
         */
        public List<Node> preOrder() {
            ArrayList<Node> result = new ArrayList<>();
            result.add(this);
            if (left != null) {
                List<Node> leftResult = this.left.preOrder();
                result.addAll(leftResult);
            }
            if (right != null) {
                List<Node> rightResult = this.right.preOrder();
                result.addAll(rightResult);
            }
            return result;
        }

        public Node find(Node node) {
            if (data == node.data) {
                return this;
            } else if (node.data < data) {
                return left.find(node);
            }
            return right.find(node);
        }

        public Node findParent(Node node) {
            if (node.data < data && left != null) {
                if (left.data == node.data) {
                    return this;
                }
                return left.findParent(node);
            } else if (node.data >= data && right != null) {
                if (right.data == node.data) {
                    return this;
                }
                return right.findParent(node);
            }
            return null;
        }
    }
}
