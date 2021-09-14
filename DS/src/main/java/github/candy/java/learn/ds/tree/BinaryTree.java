package github.candy.java.learn.ds.tree;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的前序、中序、后序遍历
 *
 * @param <T>
 */
@Data
public class BinaryTree<T> {

    private Node<T> rootNode;

    /**
     * 前序遍历
     *
     * @return
     */
    public List<T> preOrder() {
        if (rootNode == null) {
            throw new RuntimeException("binary tree root cannot be empty...");
        }
        return this.rootNode.preOrder();
    }

    /**
     * 中序遍历
     *
     * @return
     */
    public List<T> infixOrder() {
        if (rootNode == null) {
            throw new RuntimeException("binary tree root cannot be empty...");
        }
        return rootNode.infixOrder();
    }

    /**
     * 后序遍历
     *
     * @return
     */
    public List<T> postOrder() {
        if (rootNode == null) {
            throw new RuntimeException("binary tree root node cannot be empty...");
        }
        return rootNode.postOrder();
    }

    @ToString
    @Data
    static class Node<T> {

        private Node leftNode;

        private Node rightNode;

        private T data;

        public Node(T data) {
            this.data = data;
        }

        /**
         * 前序遍历
         *
         * @return
         */
        public List<T> preOrder() {
            ArrayList<T> result = new ArrayList<>();
            result.add(data);
            if (leftNode != null) {
                List<T> leftResult = this.leftNode.preOrder();
                result.addAll(leftResult);
            }
            if (rightNode != null) {
                List<T> rightResult = this.rightNode.preOrder();
                result.addAll(rightResult);
            }
            return result;
        }

        /**
         * 中序遍历
         *
         * @return
         */
        public List<T> infixOrder() {
            ArrayList<T> result = new ArrayList<>();
            if (leftNode != null) {
                List<T> leftResult = this.leftNode.infixOrder();
                result.addAll(leftResult);
            }
            result.add(data);
            if (rightNode != null) {
                List<T> rightResult = this.rightNode.infixOrder();
                result.addAll(rightResult);
            }
            return result;
        }

        /**
         * 后序遍历
         *
         * @return
         */
        public List<T> postOrder() {
            List<T> result = new ArrayList<>();
            if (leftNode != null) {
                List<T> leftNode = this.leftNode.postOrder();
                result.addAll(leftNode);
            }
            if (rightNode != null) {
                List<T> rightResult = this.rightNode.postOrder();
                result.addAll(rightResult);
            }
            result.add(data);
            return result;
        }

        public Node preOrderSearch(T nodeData){
            if (this.data == nodeData){
                return this;
            }
            Node resultNode = null;
            if (this.leftNode != null){
                resultNode = this.leftNode.preOrderSearch(nodeData);
            }
            if (resultNode != null){
                return resultNode;
            }

            if (this.rightNode != null){
                resultNode = this.rightNode.preOrderSearch(nodeData);
            }
            if (resultNode != null){
                return resultNode;
            }
            return null;
        }
    }
}
