


 class BooleanWrapper {

	private boolean a;
	
	public BooleanWrapper(Boolean a) {
		
		this.a=a;
	}
	
	public void set(boolean a ){
		
		this.a=a;
	}
	
	public boolean get() {
		
		return a;
	}
}











public class BST <K extends Comparable<K>, T> implements Map<K,T> {
BSTNode<K,T> root, current;

public BST() {
root = current = null;
}

@Override
public boolean empty() {//same
	return root == null;
}

@Override
public boolean full() {//same
	return false;
}

@Override
public void clear() {//CHECK
	current = null ;
	root = null ;
	
}

@Override
public T retrieve() {//DONE
	return current.data;
}

@Override
public void update(T e) {//DONE
	current.data=e;
	
}

@Override
public boolean find(K tkey) {//NOT same every int to k compareTo
	BSTNode<K,T> p = root,q = root;
	if(empty())
	return false;
	while(p != null) {
	q = p;
	if(p.key.compareTo(tkey)==0) {
	current = p;
	return true;
	}
	else if(tkey.compareTo(p.key)<0)
	p = p.left;
	else
	p = p.right;
	}
//	current = q;
	 return false;
}


/*private boolean findForInsert(K tkey) {//NOT same every int to k compareTo
	BSTNode<K,T> p = root,q = root;
	if(empty())
	return false;
	while(p != null) {
	q = p;
	if(p.key.compareTo(tkey)==0) {
	current = p;
	return true;
	}
	else if(tkey.compareTo(p.key)<0)
	p = p.left;
	else
	p = p.right;
	}
	current = q;
	 return false;
}
*/

@Override
public int nbKeyComp(K key) {//DONE!!
	
	BSTNode<K,T> t=root;
	
	int counter=1;
	
	while(true) {
		
			
	
	if(key.compareTo(t.key)==0) {
		
		
		break;
	}
	
	if(t==null)
		return 0;
	

	
	else {
	if( key.compareTo(t.key) < 0) {

	
		t=t.left; 
		counter++;
	}
	else {
		if(key.compareTo(t.key) >0) {
			
			
		t=t.right;
		counter++;
		}
	}
	
	
	}
	
	

	}
	return counter;
}


//private int rec_nbKeyComp(BSTNode<K,T> t , K key) {
	
	//if(t==null)
//	return 0;
	
	/*int counter2=0;
	
	if( key.compareTo(t.key) ==0)  {
	
		return counter2;
		
	}
	
	if(key.compareTo(t.key) >0 || key.compareTo(t.key) <0)
		counter2++;
	
	
	
	counter2+=rec_nbKeyComp( t.left,key);
	
	counter2+=rec_nbKeyComp(t.right,key);
	
return counter2;
*/
	
//}

@Override
public boolean insert(K key, T data) {////NOT same every int to k compareTo
	BSTNode<K,T> p, q=root;
	p=root;
	if(find(key)) {
	//current = q; // find() modified current
	return false; }
	
	//i should make the current the parent of the new node
	
	while(p != null) {
		q = p;
		if(p.key.compareTo(key)==0) {
		current = p;
		return true;
		}
		else if(key.compareTo(p.key)<0)
		p = p.left;
		else
		p = p.right;
		}
		current = q;
		
	
		p = new BSTNode<K,T>(key,data);
		
	
	
	if (empty()) {
	root = current = p;
	return true;
	}
	else {
	// current is pointing to parent of the new key
	if (key.compareTo(current.key)<0 )
	current.left = p;
	else
	current.right = p;
	current = p;
	return true;
	}
}


/*
public boolean remove(K key) {//NOT same every int to k compareTo
	// Search for k
	 K k1 = key;
	 BSTNode<K,T> p = root;
	 BSTNode<K,T> q = null; // Parent of p
	 while (p != null) {
	 if (k1.compareTo(p.key)<0) {
	 q =p;
	 p = p.left;
	 } else if (k1.compareTo(p.key) > 0) {
	 q = p;
	 p = p.right;
	 }
	 else { // Found the key
		 // Check the three cases
		 if ((p.left != null) && (p.right != null)) {
		// Case 3: two children
		 // Search for the min in the right subtree
		 BSTNode<K,T> min = p.right;
		 q = p;
		 while (min.left != null) {
		 q = min;
		min = min.left;
		 }
		 p.key = min.key;
		 p.data = min.data;
		 k1 = min.key;
		 p = min;
		 // Now fall back to either case 1 or 2
		 }
		// The subtree rooted at p will change here
		 if (p.left != null) { // One child
		 p = p.left;
		 } else { // One or no children
		 p = p.right;
		 }
		 if (q == null) { // No parent for p, root must change
		 root = p;
		 } else {
		 if (k1.compareTo(q.key) < 0) {
		 q.left = p;
		 } else {
		 q.right = p;
		 }
		 }
		 current = root;
		 return true;
		 } 
		 }
		 return false; // Not found
		
}
*/


public boolean remove(K tkey){//DONE
BooleanWrapper removed = new BooleanWrapper(false);
BSTNode<K,T> p;
p = remove_aux(tkey, root, removed);
current = root = p;
return removed.get();
}



private BSTNode<K,T> remove_aux(K key, BSTNode<K,T> p, BooleanWrapper
flag) {
BSTNode<K,T> q, child = null;
if(p == null)
return null;
if(key.compareTo(p.key)<0)
p.left = remove_aux(key, p.left, flag); //go left
else if(key.compareTo(p.key) > 0)
p.right = remove_aux(key, p.right, flag); //go right
else {
flag.set( true);
if (p.left != null && p.right != null){ //two children
q = find_min(p.right);
p.key = q.key;
p.data = q.data;
p.right = remove_aux(q.key, p.right, flag);
}
else {
if (p.right == null) //one child
child = p.left;
else if (p.left == null) //one child
child = p.right;
return child;










}
}
return p;
}

private BSTNode<K,T> find_min(BSTNode<K,T> p){
if(p == null)
return null;
while(p.left != null){
p = p.left;
}
return p;
}


@Override
public List<Pair<K, T>> getAll() {//DONE
	
	List<Pair<K,T>> l=new LinkedList <Pair<K,T>>();
	
	
	
	re_getAll( root,l);
	
	
	// TODO Auto-generated method stub
	return l;
}


private void re_getAll(BSTNode<K,T> t, List<Pair<K,T>> l) {//DONE
	
	if(t==null)
		return;
	
	
	
	re_getAll(t.left,l);
	
	l.insert(new Pair(t.key,t.data));
	
	re_getAll(t.right,l);
	
	

	
	
}

@Override
public List<Pair<K, T>> getRange(K k1, K k2) {//DONE
	
	List<Pair<K,T>> l =new LinkedList<Pair<K,T>>();
	
	
	
	rec_getRange(root, l , k1,  k2); 
	
	// TODO Auto-generated method stub
	return l;
}


private void rec_getRange(BSTNode<K,T> t,List<Pair<K,T>> l, K k1, K k2) {//DONE
	
	if(t==null)
		return;
	
	rec_getRange( t.left, l,  k1,  k2);
	
	if((k1.compareTo(t.key) <0 || k1.compareTo(t.key) ==0) &&( k2.compareTo(t.key)==0 ||k2.compareTo(t.key)>0 ))
			
	l.insert(new Pair(t.key,t.data));
	
	
	
	
	
	
	rec_getRange( t.right, l, k1,  k2);
	
	
	
	
	/*
	if ( p==null)
		return ; 
	
	rec_getRange(l,p.left,k1,k2);
	
	if (p.key.compareTo(k1)>=0 &&p.key.compareTo(k2)<=0)
	l.insert(new Pair(p.key,p.data));
	
	rec_getRange(l,p.right,k1,k2);
	*/
}






private int rec_nbKeyComp( K k1, K k2,BSTNode<K,T> t) {//DONE
	
	  int  counter =0 , the_counter =0;
	
	  	if(t==null)
		return counter+the_counter;
	


 	
	  	 
		 if (t.key.compareTo(k2)<0)
			 the_counter =+rec_nbKeyComp( k1,  k2, t.right);
	 
	 
	 counter++;
	 
	
	 

	 if (t.key.compareTo(k1)>0)
			counter =+ rec_nbKeyComp( k1,  k2, t.left);
	
	
	
	
	return counter+the_counter;
	
}
@Override
public int nbKeyComp(K k1, K k2) {//DONE
	int counter=0;
	
	if(k1.compareTo(k2)>0) {
		return 1;
	}
	
	counter =rec_nbKeyComp(  k1,  k2, root) ;
	
	// TODO Auto-generated method stub
	return counter;
}
}


