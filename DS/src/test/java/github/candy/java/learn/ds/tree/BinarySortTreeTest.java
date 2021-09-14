package github.candy.java.learn.ds.tree;

import org.junit.Test;

import java.util.List;

public class BinarySortTreeTest {
    private static BinarySortTree binarySortTree = new BinarySortTree();

    static {
        BinarySortTree.Node node1 = new BinarySortTree.Node(7);
        BinarySortTree.Node node2 = new BinarySortTree.Node(3);
        BinarySortTree.Node node3 = new BinarySortTree.Node(10);
        BinarySortTree.Node node4 = new BinarySortTree.Node(12);
        BinarySortTree.Node node5 = new BinarySortTree.Node(5);
        BinarySortTree.Node node6 = new BinarySortTree.Node(1);
        BinarySortTree.Node node7 = new BinarySortTree.Node(9);
        binarySortTree.add(node1);
        binarySortTree.add(node2);
        binarySortTree.add(node3);
        binarySortTree.add(node4);
        binarySortTree.add(node5);
        binarySortTree.add(node6);
        binarySortTree.add(node7);
    }

    /**
     * 二叉排序树遍历
     */
    @Test
    public void prePost() {
        List<BinarySortTree.Node> nodes = binarySortTree.preOrder();
        for (BinarySortTree.Node node : nodes) {
            System.out.println(node.getData());
        }
    }

    @Test
    public void findTest(){
        BinarySortTree.Node node = binarySortTree.find(new BinarySortTree.Node(10));
        System.out.println(node.getData());
    }

    @Test
    public void findParentTest(){
        BinarySortTree.Node parent = binarySortTree.findParent(new BinarySortTree.Node(10));
        System.out.println(parent.getData());
    }

    @Test
    public void delTest01(){
        binarySortTree.del(new BinarySortTree.Node(3));
        List<BinarySortTree.Node> nodes = binarySortTree.preOrder();
        for (BinarySortTree.Node node : nodes) {
            System.out.println(node.getData());
        }
    }

    @Test
    public void delTest02(){
        binarySortTree.del(new BinarySortTree.Node(1));
        List<BinarySortTree.Node> nodes = binarySortTree.preOrder();
        for (BinarySortTree.Node node : nodes) {
            System.out.println(node.getData());
        }
    }

    @Test
    public void delTest03(){
        binarySortTree.del(new BinarySortTree.Node(5));
        List<BinarySortTree.Node> nodes = binarySortTree.preOrder();
        for (BinarySortTree.Node node : nodes) {
            System.out.println(node.getData());
        }
    }
}
