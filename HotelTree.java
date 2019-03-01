import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * Write a description of class ListNode here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HotelTree<E extends Comparable<E>>
{
    // instance variables - replace the example below with your own
    private TreeNode<E> root;
    private int size;

    public HotelTree(TreeNode node){
        size = 0;
        root = node;
    }

    public HotelTree(){
        this(null);
    }

    private class TreeNode<E extends Comparable<E>> implements Comparable<TreeNode<E>>
    {
        public E value;
        public TreeNode<E> left;
        public TreeNode<E> right;

        public TreeNode(E value){
            this.value = value;

        }

        public int compareTo(TreeNode<E> other){
            return this.value.compareTo(other.value);
        }

        public void setNode(TreeNode<E> node){
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }

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

        public boolean equals(TreeNode<E> node){
            return this.value.equals(node.value);
        }

    }
    public int size(){
        return size;
    }

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

    public void add(E value){
        if(root == null){
            root = new TreeNode(value);
            size++;
            return;
        }

        if(root.add(value, root)) size++;

    }

    public ArrayList<E> inorder(){
        ArrayList<E> list = new ArrayList();
        if(root != null)root.inorder(list);
        return list;
    }

    public ArrayList<E> preorder(){
        ArrayList<E> list = new ArrayList();
        if(root != null)root.preorder(list);
        return list;
    }

    public ArrayList<E> postorder(){
        ArrayList<E> list = new ArrayList();
        if(root != null)root.postorder(list);
        return list;
    }

    public String toString(){
        String str = "{";
        for(E value : this.inorder()){
            str += value.toString() + ", ";
        }
        if(str.length() != 1)str = str.substring(0, str.length()-2);
        str += "}";
        return str;
    }

    public String toStringPre(){
        String str = "{";
        for(E value : this.preorder()){
            str += value.toString() + ", ";
        }
        if(str.length() != 1)str = str.substring(0, str.length()-2);
        str += "}";
        return str;
    }

    public TreeNode<E> findNode(E value){
        return root.search(value);
    }

    public static void test(){
        HotelTree<Integer> x = new HotelTree();
        x.add(133);
        x.add(123);
        x.add(1312);
        x.add(3221);
        x.add(12);
        x.add(11);
        System.out.println(x.size());
    }
}

