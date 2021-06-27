/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.BST;

import org.w3c.dom.NodeList;

/**
 *
 * @author paovv
 */
public class BTree implements Tree {

    private BTreeNode root;//representa la unica entrada al árbol

    //Constructor
    public BTree() {
        this.root = null;
    }
    
    public BTreeNode getRoot(){
        return root;
    }

    @Override
    public int size() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("BinaryTree is empty");
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
            throw new TreeException("BinaryTree is empty");
        }
        return binarySearch(root, element);
    }

    private boolean binarySearch(BTreeNode node, Object element) {
        if (node == null) {
            return false;
        } else {
            if (util.Utility.equals(node.data, element)) {
                return true;
            } else {
                return binarySearch(node.left, element) || binarySearch(node.right, element);
            }
        }
    }

    @Override
    public void add(Object element) {
        root = add(root, element);
    }

    private BTreeNode add(BTreeNode node, Object element) {
        if (node == null) {//el árbol está vacío
            node = new BTreeNode(element);
        } else {
            if (node.left == null) {
                node.left = add(node.left, element);
            } else {
                if (node.right == null) {
                    node.right = add(node.right, element);
                } else {//debemos establecer un criterio de inserción
                    int value = util.Utility.random(10);
                    if (value % 2 == 0) {//inserte por la izquierda si value es par
                        node.left = add(node.left, element);
                    } else {//inserte por la der si value es impar
                        node.right = add(node.right, element);
                    }
                }
            }
        }
        return node;
    }

    private BTreeNode add(BTreeNode node, Object element, String label) {
        if (node == null) { //el arbol esta vacio
            node = new BTreeNode(element, label);
        } else if (node.left == null) {
            node.left = add(node.left, element, label + "/left");
        } else if (node.right == null) {
            node.right = add(node.right, element, label + "/right");
        } else { //debemos establecer un criterio de insercion
            int value = util.Utility.random(10);
            if (value % 2 == 0) { //inserte por la izq si value==num par
                node.left = add(node.left, element, label + "/left");
            } else { //inserte por la der si value==num impar
                node.right = add(node.right, element, label + "/right");
            }
        }

        return node;
    }

    @Override
    public void remove(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("BinaryTree is empty");
        }
        root = remove(root, element);
    }

    private BTreeNode remove(BTreeNode node, Object element) {
        if (node != null) {
            if (util.Utility.equals(node.data, element)) {
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
                    Object value = getLeaf(node.right);
                    node.data = value;
                    node.right = removeLeaf(node.right, value);
                }
            }
            node.left = remove(node.left, element);
            node.right = remove(node.right, element);
        }
        return node;
    }

    private Object getLeaf(BTreeNode node) {

        Object aux;
        if (node == null) {
            return null;
        } else {
            //si es una hoja
            if (node.left == null && node.right == null) {
                return node.data;
            } else {
                aux = getLeaf(node.left);
                if (aux == null) {//es porque no encontro ninguna hoja
                    aux = getLeaf(node.right);
                }
            }
        }
        return aux;
    }

    private BTreeNode removeLeaf(BTreeNode node, Object element) {
        if (node == null) {
            return null;
        } else {
            //si es una hoja
            if (node.left == null && node.right == null && util.Utility.equals(node.data, element)) {
                return null;
            } else {
                node.left = removeLeaf(node.left, element);
                node.right = removeLeaf(node.right, element);
            }
        }
        return node;
    }

    @Override
    public int height(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("BinaryTree is empty");
        }
        return height(root, element, 0);
    }

    private int height(BTreeNode node, Object element, int count) {
        if (node == null) {
            return 0;//significa que el elemento no existe
        } else {
            if (util.Utility.equals(node.data, element)) {
                return count;
            } //si no lo encontro debe buscar por la izq y la derecha
            else {
                return Math.max(height(node.left, element, ++count), height(node.right, element, count));
            }
        }
    }

    @Override
    public int height() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("BinaryTree is empty");
        }
        return height(root);
    }

    private int height(BTreeNode node) {
        if (node == null) {
            return 0;//significa que el elemento no existe
        } else {
            return Math.max(height(node.left), height(node.right)+1);
//            if (height(node.left) > height(node.right)) {
//                return (height(node.left) + 1);
//            } else {
//                return (height(node.right) + 1);
//            }
        }
    }

    @Override
    public Object min() throws TreeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object max() throws TreeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String preOrder() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("BinaryTree is empty");
        }
        return "PreOrder Transversal Tour: \n" + preOrder(root);
    }

    //Transversal tour: N-L-R
    private String preOrder(BTreeNode node) {
        String result = "";

        if (node != null) {
            result = node.data + " ";
            result += preOrder(node.left);
            result += preOrder(node.right);
        }

        return result;
    }

    @Override
    public String InOrder() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("BinaryTree is empty");
        }
        return "InOrder Transversal Tour: \n" + InOrder(root);
    }

    //Transversal tour: L-N-R
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
            throw new TreeException("BinaryTree is empty");
        }
        return "PostOrder Transversal Tour: \n" + postOrder(root);
    }

    //Transversal tour: L-R-N
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
        String result = "Binary Tree Tour";

        result += "\nPreOrder:\n " + preOrder(root);
        result += "\nInOrder:\n " + InOrder(root);
        result += "\nPostOrder:\n " + postOrder(root) + "\n";

        return result;
    }
}
