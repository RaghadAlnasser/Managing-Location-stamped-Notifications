
public class BSTNode <K extends Comparable<K>, T>  {
public K key;
public T data;
public BSTNode<K,T> left, right;
/** Creates a new instance of BSTNode */
public BSTNode(K key, T val) {
this.key = key;
data = val;
left = right = null;
}
public BSTNode(K key , T val, BSTNode<K,T> l, BSTNode<K,T> r) {
this.key = key;
data = val;
left = l;
right = r;
}
}