import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;

/**
 * Hotel Tree constructs a binary search tree to store data base on that, the Hotel Tree object contains a Load Factor
 * and a rearrange function to keep the data evenly distributed as the number of data in the tree increases.
 * The implementation of this feature is that when the size equals to the load factor, the rearrange method will be called
 * and shuffle the tree so that the tree will be evenly distributed, i.e. no branch depth greater than 1.
 * After the shuffle, triple the load factor.
 *
 * @author Xingyu Liu
 * @version 3/4/2019
 */
public class HotelTree<E extends Comparable<E>>
{
    // instance variables - replace the example below with your own
    private TreeNode<E> root;
    private int size;
    private int LOAD_FACTOR;
    private final int DEFAULT_FACTOR = 32;

    /**
     * Default constructor for HotelTree class
     */
    public HotelTree(){
        size = 0;
        root = null;
        LOAD_FACTOR = DEFAULT_FACTOR;
    }

    /**
     * Constructor for hotel tree, takes an int as the custom load factor
     * 
     * @param factor the custom load factor
     */
    public HotelTree(int factor){
        this();
        LOAD_FACTOR = factor;
    }

    /**
     * Constructor for hotel tree, takes a tree node as root
     * 
     * @param node the root for the tree.
     */
    public HotelTree(TreeNode node){
        size = 0;
        root = node;
        LOAD_FACTOR = DEFAULT_FACTOR;
    }

    /**
     * Constructor which takes a collection of data as parameter
     * 
     * @param e the collection of data
     */
    public HotelTree(Collection<? extends E> e){
        this();
        addAll(e);
    }

    /**
     * Constructor which takes a collection of data as parameter
     * and custom load factor
     * 
     * @param e the collection of data
     * @param factor the custom load factor
     */
    public HotelTree(Collection<? extends E> e, int factor){
        this(factor);
        addAll(e);
    }

    /**
     * add a collection of data to the tree
     * 
     * @param e the collection of data
     */
    public void addAll(Collection<? extends E> e){
        for(E value : e){
            this.add(value);
        }
    }

    /**
     * add the value without checking the load factor
     * 
     * @param value the data wanted to add
     */
    public void quickAdd(E value){
        if(root == null){
            root = new TreeNode(value);
            size++;
            return;
        }

        if(root.add(value, root)) size++;
    }

    /**
     * rearrange the tree so that the data will evenly distributed on every branch
     */
    public void rearrange(){
        ArrayList<E> list = this.inorder();
        if(list.size() <= 2) return;
        this.root = null;
        size = 0;
        int temp = list.size()/2;
        quickAdd(list.get(temp));
        rearrange(list.subList(0,temp));
        rearrange(list.subList(temp+1, list.size()));
        LOAD_FACTOR += LOAD_FACTOR * 2;
    }

    /**
     * the recursive method for rearrange. takes list as parameter.
     * 
     * @param list the list of data from the chunck of tree from last call.
     */
    private void rearrange(List<E> list){
        System.out.println(list);
        if(list.size() <= 2){
            for(E value: list){
                quickAdd(value);
            }
            return;
        }
        int temp = list.size()/2;
        quickAdd(list.get(temp));
        rearrange(list.subList(0,temp));
        rearrange(list.subList(temp+1, list.size()));
    }

    /**
     * Add a data to the tree
     * 
     * @param value the value wanted to add to the tree
     */
    public void add(E value){
        if(root == null){
            root = new TreeNode(value);
            size++;
            return;
        }

        if(root.add(value, root)) size++;
        if(size == LOAD_FACTOR){
            rearrange();
            LOAD_FACTOR += LOAD_FACTOR * 2;
        }
    }

    /**
     * return the size of the tree
     * 
     * @return the size of the tree
     */
    public int size(){
        return size;
    }

    /**
     * remove the node of certain value.
     * 
     * @param the value of the node want to be removed
     * @return true if removed false if no match is found.
     */
    public boolean remove(E value){
        if(root == null){
            return false;
        }
        try{
            root.remove(value, root);
            size--;
        }catch(NoSuchElementException e){
            return false;
        }
        return true;
    }

    /**
     * get the elements of the tree through inorder traversal
     * 
     * @return the arraylist of elements through inorder traversal
     */
    public ArrayList<E> inorder(){
        ArrayList<E> list = new ArrayList();
        if(root != null)root.inorder(list);
        return list;
    }

    /**
     * get the elements of the tree through preorder traversal
     * 
     * @return the arraylist of elements through preorder traversal
     */
    public ArrayList<E> preorder(){
        ArrayList<E> list = new ArrayList();
        if(root != null)root.preorder(list);
        return list;
    }

    /**
     * get the elements of the tree through postorder traversal
     * 
     * @return the arraylist of elements through postorder traversal
     */
    public ArrayList<E> postorder(){
        ArrayList<E> list = new ArrayList();
        if(root != null)root.postorder(list);
        return list;
    }

    /**
     * return the String representation of the tree object in inorder
     * 
     * @return the String representation of the tree object in inorder
     */
    public String toString(){
        String str = "{";
        for(E value : this.inorder()){
            str += value.toString() + ", ";
        }
        if(str.length() != 1)str = str.substring(0, str.length()-2);
        str += "}";
        return str;
    }

    /**
     * return the String representation of the tree object in preorder
     * 
     * @return the String representation of the tree object in preorder
     */
    public String toStringPre(){
        String str = "{";
        for(E value : this.preorder()){
            str += value.toString() + ", ";
        }
        if(str.length() != 1)str = str.substring(0, str.length()-2);
        str += "}";
        return str;
    }

    /**
     * see if the trees are equal
     * 
     * @param the other tree to be compared
     * @return true if equals, false if not
     */
    public boolean equals(HotelTree tree){
        if(this.root == null && tree.root == null) return true;
        if(this.size != tree.size) return false;
        try{
            TreeNode.equalsRecursive(this.root, tree.root);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * return the max depth of the tree
     * 
     * @return the max depth of the tree
     */
    public int maxLevel() {
        if (root == null)
            return 0;
        return Math.max(root.left.maxLevel(), root.right.maxLevel()) + 1;
    }

    /**
     * find the node with certain value
     * 
     * @param value the value of the node wanted to be found
     * @return the node object of the certain value, null if no match is found
     */
    public TreeNode<E> findNode(E value){
        return root.search(value);
    }

    /**
     * test method of the Hotel Tree Class
     */
    public static void test(){
        HotelTree<Integer> x = new HotelTree();
        x.add(133);
        x.add(123);
        x.add(221);
        x.add(322);
        x.add(144);
        x.add(525);
        x.add(114);
        x.add(121);
        x.add(113);
        x.add(13);
        x.add(12);
        x.add(0);
        x.add(-3);
        x.add(-32);
        x.add(-332);
        x.add(322);
        x.add(25);
        x.add(154);
        x.add(421);
        x.add(502);
        BTreePrinter.printNode(x.root);
        System.out.println(x);
        System.out.println(x.size());
        x.rearrange();
        System.out.println(x);
        System.out.println(x.size());
        BTreePrinter.printNode(x.root);
    }

    /**
     * The Tree Node Class. Since no where else will use this class, so this become the nested class.
     * the tree node class builds the node of the tree, and contains various of recursive methods.
     */
    private static class TreeNode<E extends Comparable<E>> implements Comparable<TreeNode<E>>
    {
        public E value;
        public TreeNode<E> left;
        public TreeNode<E> right;

        /**
         * default constructor for TreeNode.
         * 
         * @param value the value which the tree node object will contain.
         */
        public TreeNode(E value){
            this.value = value;
        }

        /**
         * Compare two tree node objects
         * 
         * @param other the other tree node to be compared
         * @return negative integer if this is smaller than other, 0 if equals, positive integer if greater
         */
        public int compareTo(TreeNode<E> other){
            return this.value.compareTo(other.value);
        }

        /**
         * set the node to the exxact value of the given node
         * 
         * @param node the other node to set the value with
         */
        public void setNode(TreeNode<E> node){
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }

        /**
         * the recursive method of maxLevel, find the greatest depth of the tree
         * 
         * @return int the greatest depth so far
         */
        public int maxLevel() {
            if (this == null)
                return 0;

            return Math.max(this.left.maxLevel(), this.right.maxLevel()) + 1;
        }

        /**
         * recursive method call for add
         */
        public boolean add(E value, TreeNode<E> node){
            if(node.value.compareTo(value) > 0){
                if(node.left != null){
                    return add(value, node.left);
                }else{
                    node.left = new TreeNode(value);
                    return true;
                }
            }else if (node.value.compareTo(value) < 0){
                if(node.right != null){
                    return add(value, node.right);
                }else{
                    node.right = new TreeNode(value);
                    return true;
                }
            }
            return false;
        }

        public static void equalsRecursive(TreeNode node1, TreeNode node2){
            if(!node1.value.equals(node2.value)) throw new IllegalArgumentException();
            if(node1.left != null || node2.left != null) equalsRecursive(node1.left, node2.left);
            if(node1.right != null || node2.right != null) equalsRecursive(node1.right, node2.right);
        }

        /**
         * get the elements of the tree through inorder traversal
         * 
         * @param the arraylist of elements through inorder traversal
         */
        public void inorder(ArrayList<E> list){
            if(this.left != null){
                this.left.inorder(list);
            }
            if(this != null){
                list.add(value);
            }
            if (this.right != null){
                this.right.inorder(list);
            }
        }

        /**
         * get the elements of the tree through postorder traversal
         * 
         * @param the arraylist of elements through postorder traversal
         */
        public void postorder(ArrayList<E> list){
            if(this.left != null){
                this.left.inorder(list);
            }
            if (this.right != null){
                this.right.inorder(list);
            }
            if(this != null){
                list.add(value);
            }
        }

        /**
         * get the elements of the tree through preorder traversal
         * 
         * @param the arraylist of elements through preorder traversal
         */
        public void preorder(ArrayList<E> list){
            if(this != null){
                list.add(value);
            }
            if(this.left != null){
                this.left.inorder(list);
            }
            if (this.right != null){
                this.right.inorder(list);
            }
        }

        /**
         * the recursive call for remove
         */
        public TreeNode remove(E value, TreeNode node){
            if (node == null){
                throw new NoSuchElementException();
            }

            if(node.value.compareTo(value) == 0){
                if(node.left == null && node.right == null){
                    node = null;
                }else if(node.left == null){
                    node = node.right;
                }else if(node.right == null){
                    node = node.left;
                }else{
                    TreeNode<E> left = node.left;
                    while(left.right != null){
                        left = left.right;
                    }
                    node.value = left.value;
                    node.left = remove(left.value, node.left);
                }   
            }else if(node.value.compareTo(value) > 0){
                node.left = node.remove(value, node.left);
            }else if(node.value.compareTo(value) < 0){
                node.right = node.remove(value, node.right);
            }
            return node;
        }

        /**
         * Get the tree node contains specific value. return null if no matches
         * 
         * @param value the desired value 
         * @return the TreeNode match the value, null if no matches
         */
        public TreeNode<E> search(E value){
            if (this == null){
                return this;
            }
            if(this.value.compareTo(value) > 0 && this.left != null){
                return this.left.search(value);
            }
            else if(this.value.compareTo(value) < 0 && this.right != null){
                return this.right.search(value);
            }else{
                return this;
            }
        }

        /**
         * see if the treeNodes are equal
         * 
         * @param the other node to be compared
         * @return true if equals, false if not
         */
        public boolean equals(TreeNode<E> node){
            return this.value.equals(node.value);
        }
    }

    private static class BTreePrinter{
        public static <E extends Comparable<E>> void printNode(TreeNode root) {
            int maxLevel = BTreePrinter.maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static <E extends Comparable<E>> void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 2;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<TreeNode> newNodes = new ArrayList<TreeNode>();
            for (TreeNode node : nodes) {
                if (node != null) {
                    System.out.print(fillSpace(2, node.value.toString()));
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private static <E extends Comparable<?>> int maxLevel(TreeNode node) {
            if (node == null)
                return 0;

            return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
        }

        private static <E> boolean isAllElementsNull(List<E> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }
            return true;
        }

        private static String fillSpace(int x, String name){
            int temp = x - name.length();
            String str = "";
            if(temp > 0){
                for(int i = 0; i < temp; i++){
                    str += " ";
                }
            }
            str += name;
            return str;
        }
    }
}

