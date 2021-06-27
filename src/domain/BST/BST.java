/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.BST;

/**
 *
 * @author paovv BST = Binary Search Tree
 */
public class BST implements Tree {

    private BTreeNode root; //representa la unica entrada al arbol

    //Constructor
    public BST() {
        this.root = null;
    }

    public BTreeNode getRoot() {
        return root;
    }

    @Override
    public int size() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return size(root);
    }

    private int size(BTreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.left) + size(node.right);
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return binarySearch(root, element);
    }

    private boolean binarySearch(BTreeNode node, Object element) {
        if (node == null) {
            return false;
        } else if (util.Utility.equals(node.data, element)) {
            return true; //ya lo encontro
        } else if (util.Utility.lessT(element, node.data)) {
            return binarySearch(node.left, element);
        } else {
            return binarySearch(node.right, element);
        }
    }

    @Override
    public void add(Object element) {
        root = add(root, element);
    }

    private BTreeNode add(BTreeNode node, Object element) {
        if (node == null) { //el arbol esta vacio
            node = new BTreeNode(element);
        } else if (util.Utility.lessT(element, node.data)) {
            node.left = add(node.left, element);
        } else if (util.Utility.greaterT(element, node.data)) {
            node.right = add(node.right, element);
        }
        return node;
    }

    @Override
    public void remove(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        root = remove(root, element);
    }

    private BTreeNode remove(BTreeNode node, Object element) {
        if (node != null) {
            if (util.Utility.lessT(element, node.data)) {
                node.left = remove(node.left, element);
            } else if (util.Utility.greaterT(element, node.data)) {
                node.right = remove(node.right, element);
            } else if (util.Utility.equals(node.data, element)) {
                //Caso 1. El nodo a suprimir no tiene hijos
                if (node.left == null && node.right == null) {
                    return node = null;
                } else //Caso 2. El nodo a suprimir solo tiene un hijo
                if (node.left == null && node.right != null) {
                    node = node.right;
                } else if (node.left != null && node.right == null) {
                    node = node.left;
                } else //Caso 3. El nodo a suprimir tiene dos hijos
                if (node.left != null && node.right != null) {
                    //se debe sustituir la data del nodo, por el la data
                    //alguna hoja (nodo externo) del subarbol derecho
                    Object value = min(node.right);
                    node.data = value;
                    node.right = remove(node.right, value);
                }
            }
        }
        return node;
    }

    @Override
    public int height(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return height(root, element, 0);
    }

    private int height(BTreeNode node, Object element, int count) {
        if (node == null) {
            return 0; //significa q el elemento no existe
        } else if (util.Utility.equals(node.data, element)) {
            return count;
        } //si no lo encontro debe buscar por la izq y la der
        else if (util.Utility.lessT(element, node.data)) {
            return height(node.left, element, ++count);
        } else {
            return height(node.right, element, ++count);
        }
    }

//    @Override
//    public int height() throws TreeException {
//        if (isEmpty()) {
//            throw new TreeException("Binary Search Tree is empty");
//        }
//        return height(root);
//    }
//
//    private int height(BTreeNode node) {
//        if (node == null) {
//            return 0;
//        } else {
//            return Math.max(height(node.left),
//                    height(node.right) + 1);
//        }
//    }
    @Override
    public int height() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return getHeight(root);
    }

    //HeightTree
    public boolean isLeaf(BTreeNode a) {
        if (a.right == null && a.left == null) {
            return true;
        }
        return false;
    }

    public int getMax(int a, int b) {
        return (a > b) ? a : b;
    }

    public int getHeight(BTreeNode a) {
        if (a == null || isLeaf(a)) {
            return 0;
        }
        return (getMax(getHeight(a.left), getHeight(a.right)) + 1);
    }

    @Override
    public Object min() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return min(root);
    }

    private Object min(BTreeNode node) {
        if (node.left != null) {
            return min(node.left);
        }
        return node.data;
    }

    @Override
    public Object max() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return max(root);
    }

    private Object max(BTreeNode node) {
        if (node.right != null) {
            return max(node.right);
        }
        return node.data;
    }

    @Override
    public String preOrder() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return "PreOrder Transversal Tour: \n"
                + preOrder(root);
    }

    //Tranversal tour: N-L-R
    private String preOrder(BTreeNode node) {
        String result = "";
        if (node != null) {
            result += node.data + "\n";
            result += preOrder(node.left);
            result += preOrder(node.right);
        }
        return result;
    }

    public String preOrderAux() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return preOrderAux(root);
    }

    //Tranversal tour: N-L-R
    private String preOrderAux(BTreeNode node) {
        String result = "";
        if (node != null) {
            result = node.data + "\n";
            result += preOrderAux(node.left);
            result += preOrderAux(node.right);
        }
        return result;
    }

    @Override
    public String InOrder() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return "InOrder Transversal Tour: \n"
                + InOrder(root);
    }

    //Tranversal tour: L-N-R
    private String InOrder(BTreeNode node) {
        String result = "";
        if (node != null) {
            result = InOrder(node.left);
            result += node.data + " ";
            result += InOrder(node.right);
        }
        return result;
    }

    @Override
    public String postOrder() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return "PostOrder Transversal Tour: \n"
                + postOrder(root);
    }

    //Tranversal tour: L-R-N
    private String postOrder(BTreeNode node) {
        String result = "";
        if (node != null) {
            result = postOrder(node.left);
            result += postOrder(node.right);
            result += node.data + " ";
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "BINARY SEARCH TREE TOUR...";
        result += "\n"+preOrder(root);
//        result += "\nInOrder: " + InOrder(root);
//        result += "\nPostOrder: " + postOrder(root);
        return result;
    }

    public boolean existe(Object info) {

        BTreeNode node = root;
        while (node != null) {
            if (util.Utility.equals(info, node.data)) {
                return true;
            } else if (util.Utility.lessT(info, node.data)) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return false;
    }

}
