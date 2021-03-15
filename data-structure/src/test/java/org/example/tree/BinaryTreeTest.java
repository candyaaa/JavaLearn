package org.example.tree;

import org.junit.Test;

import java.util.List;

public class BinaryTreeTest {
    private static final BinaryTree binaryTree = new BinaryTree();

    static {
        BinaryTree.Node node1 = new BinaryTree.Node(1);
        BinaryTree.Node node2 = new BinaryTree.Node(2);
        BinaryTree.Node node3 = new BinaryTree.Node(3);
        BinaryTree.Node node4 = new BinaryTree.Node(4);
        BinaryTree.Node node5 = new BinaryTree.Node(5);

        binaryTree.setRootNode(node1);
        node1.setLeftNode(node2);
        node1.setRightNode(node3);
        node2.setLeftNode(node4);
        node2.setRightNode(node5);
    }

    /**
     * 前序遍历测试
     */
    @Test
    public void preOrderTest() {

        List list = binaryTree.preOrder();
        System.out.println(list);
    }

    @Test
    public void infixOrderTest(){
        List list = binaryTree.infixOrder();
        System.out.println(list);
    }

    @Test
    public void postOrderTest(){
        List list = binaryTree.postOrder();
        System.out.println(list);
    }
}
