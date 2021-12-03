/**
 *
 * AVLTree
 *
 * An implementation of aמ AVL Tree with
 * distinct integer keys and info.
 *
 */

public class AVLTree {
	private IAVLNode virtual_node = new AVLNode(-1,null);
	private IAVLNode root;
	private IAVLNode min;
	private IAVLNode max;

  /**
   * public boolean empty()
   *
   * Returns true if and only if the tree is empty.
   *
   */
  public boolean empty() {
    return root == null; // to be replaced by student code
  }

 /**
   * public String search(int k)
   *
   * Returns the info of an item with key k if it exists in the tree.
   * otherwise, returns null.
   */
 public String rec_search(IAVLNode node, int k){
 	if(!node.isRealNode()){
 		return null;
	}
	if(k== node.getKey()){
		return node.getValue();
	}
	if (k < node.getKey()){
		return rec_search(node.getLeft(),k);
	}
	else {
		return rec_search(node.getRight(),k);
	}
 }
  public String search(int k) {
  	 return rec_search(this.getRoot(),k);
  }

  /**
   * public int insert(int k, String i)
   *
   * Inserts an item with key k and info i to the AVL tree.
   * The tree must remain valid, i.e. keep its invariants.
   * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
   * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
   * Returns -1 if an item with key k already exists in the tree.
   */


  public IAVLNode Tree_Position(int k) // given a key k, find where to insert it
  {
	  IAVLNode parent = root;
	  IAVLNode pointer = new AVLNode(parent.getKey(),parent.getValue());
	  while(parent.isRealNode())
	  {
		  pointer = parent;
		  if(parent.getKey() > k)
			  parent = parent.getLeft();
		  if(parent.getKey() < k)
			  parent = parent.getRight();
	  }
	  return pointer;
  }


  public void inserting_update(IAVLNode new_node) // update size, and check min/max after inserting
  {
	  if (new_node.getKey() < min.getKey())
		  min = new_node;
	  else if(new_node.getKey() > max.getKey())
		  max = new_node;
	  size += 1; //* need to change, we no longer have general size!
  }


  public void Insert_First(int k, String i) // insert first
  {
	  this.root = new AVLNode(k,i);
	  this.size += 1;
  }

	public int BF(IAVLNode node) // check BF of a node
	{
		int BF = (node.getLeft().getHeight()-node.getRight().getHeight());
		return BF;
	}

   //public void Rotate

	public int height_diff(IAVLNode node1, IAVLNode node2)//returns the difference between the height of two nodes
	{
		return node1.getHeight()-node2.getHeight();
	}


   public int insert(int k, String i)
   {
	   if(k!= -1) //invalid key value
		   return -1;

	   if(search(k) != null) // k is already in the tree
		   return -1;

	   if(root.getValue() == null) // tree is empty
		   Insert_First(k,i);

	   int counter; // rotation counter
	   boolean height_changed;
	   IAVLNode new_node = new AVLNode(k,i); // create the new node to insert
	   IAVLNode point = Tree_Position(k); // find where to insert new node
	   new_node.setParent(point);

	   if(k < point.getKey()) // inserting left
	   {
		   point.setLeft(new_node);
		   inserting_update(new_node);
	   }

	   if(k > point.getKey()) // inserting right
		   {
			   point.setRight(new_node);
			   inserting_update(new_node);
		   }

	   if(BF(point) == 1) // we added a node to a "naked" leaf
	   {
		   point.setHeight(point.getHeight() + 1); // promote fix
		   //divide into two reflective cases:
		   if(point.getKey() < point.getParent().getKey()) // the case where we added to a left child
		   while(true)
		   { // *change size
			   IAVLNode point_father = point.getParent();
			   if(BF(point_father) == 1) // promote fix
				   point_father.setHeight(point_father.getHeight()+1);
			   else if(BF(point_father) == 2)// rotations fix
				   if((height_diff(point,point.getRight()) == 2)&&(height_diff(point,point.getLeft())) == 1) // one rotation
				   {
					   //*Rotate_right(p,p_f)
					   //*count++
					   point_father.setHeight(point_father.getHeight()-1);
					   break;
				   }
			  	   else if((height_diff(point,point.getRight()) == 1)&&(height_diff(point,point.getLeft())) == 2) // double rotate
				   {
					   //*Rotate_Left()
					   //*Rotate_Right()
					   //*count +2
					   //*demote p,p_f, promote p.right child
					   break;
				   }
		   }
		   else if(point.getKey() > point.getParent().getKey()) // the case where we added to a right child
		   {
			   while(true)
			   {
				   IAVLNode point_father = point.getParent();
				   if(BF(point_father) == 1)//promote fix
						point_father.setHeight(point_father.getHeight()+1);
				   else if (BF(point_father)==2)
				   {
					   if((height_diff(point,point.getRight()) == 1)&&(height_diff(point,point.getLeft())) == 2) // one rotation
					   {
						   Rotate_Left((AVLNode)point, (AVLNode)point_father);
						   //*count++
						   point_father.setHeight(point_father.getHeight()-1);
						   break;
					   }
					   else if((height_diff(point,point.getRight()) == 2)&&(height_diff(point,point.getLeft())) == 1) // double rotate
					   {
						   //*Rotate left
						   //*rotate right
						   //* count +2
						   //demote p,p_f, promote p.left child
					   }

				   }


			   }
		   }
	   }

		while(true)
		{

		}

	  return 420;
   }
   public void Rotate_Right(AVLNode b, AVLNode a) // a is father of b
   {
	   //check if root
	   if(a.equals(root))

	   b.setParent(a.getParent());
	   if(b.getParent().getKey() > b.getKey())
		   b.getParent().setRight(b);
	   else
		   b.getParent().setLeft(b);
	   a.setParent(b);
	   a.setLeft(b.getRight());
	   b.setRight(a);
	   a.getLeft().setParent(a);

	   a.setSize(b.getSize_right(),a.getSize_right());
	   b.setSize(b.getSize_left(),a.getSize_right()+a.getSize_left());
   }
	public void Rotate_Left(AVLNode b, AVLNode a) // a is father of b
	{
		b.setParent(a.getParent());
		if(b.getKey()<b.getParent().getKey())
			b.getParent().setLeft(b);
		else
			b.getParent().setRight(b);
		a.setRight(b.getLeft());
		a.getRight().setParent(a);
		b.setLeft(a);
		a.setParent(b);

		a.setSize(a.getSize_left(),b.getSize_left());
		b.setSize(a.getSize_left()+a.getSize_right(), b.getSize_right());

	}


  /**
   * public int delete(int k)
   *
   * Deletes an item with key k from the binary tree, if it is there.
   * The tree must remain valid, i.e. keep its invariants.
   * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
   * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
   * Returns -1 if an item with key k was not found in the tree.
   */
   public int delete(int k)
   {
	   return 421;	// to be replaced by student code
   }

   /**
    * public String min()
    *
    * Returns the info of the item with the smallest key in the tree,
    * or null if the tree is empty.
    */

   public String min_help(){
	   IAVLNode parent = root;
	   if (this.empty()){
		   return null;
	   }
	   IAVLNode left = root.getLeft();
	   while(left.isRealNode()){
		   parent = left;
		   left = left.getLeft();
	   }
	   return parent.getValue();

   }
   public String min() {
   	return this.min.getValue();

   }

   /**
    * public String max()
    *
    * Returns the info of the item with the largest key in the tree,
    * or null if the tree is empty.
    */
   public String max()
   {
   	return this.max.getValue();

   }

	public String max_help() {
		IAVLNode parent = this.getRoot();
		if (this.empty()){
			return null;
		}
		IAVLNode right = parent.getRight();
		while(right.isRealNode()){
			parent = right;
			right = right.getRight();
		}
		return parent.getValue(); // to be replaced by student code
	}

  /**
   * public int[] keysToArray()
   *
   * Returns a sorted array which contains all keys in the tree,
   * or an empty array if the tree is empty.
   */

  public IAVLNode[] inorder(){
  	root = this.getRoot();

  }
  public int[] keysToArray()
  {
        return new int[33]; // to be replaced by student code
  }

  /**
   * public String[] infoToArray()
   *
   * Returns an array which contains all info in the tree,
   * sorted by their respective keys,
   * or an empty array if the tree is empty.
   */
  public String[] infoToArray()
  {
        return new String[55]; // to be replaced by student code
  }

   /**
    * public int size()
    *
    * Returns the number of nodes in the tree.
    */
   public int size()
   {
	   return (AVLNode)root.; // to be replaced by student code
   }
   
   /**
    * public int getRoot()
    *
    * Returns the root AVL node, or null if the tree is empty
    */
   public IAVLNode getRoot()
   {
	   return this.root;
   }
   
   /**
    * public AVLTree[] split(int x)
    *
    * splits the tree into 2 trees according to the key x. 
    * Returns an array [t1, t2] with two AVL trees. keys(t1) < x < keys(t2).
    * 
	* precondition: search(x) != null (i.e. you can also assume that the tree is not empty)
    * postcondition: none
    */   
   public AVLTree[] split(int x)
   {
	   return null; 
   }
   
   /**
    * public int join(IAVLNode x, AVLTree t)
    *
    * joins t and x with the tree. 	
    * Returns the complexity of the operation (|tree.rank - t.rank| + 1).
	*
	* precondition: keys(t) < x < keys() or keys(t) > x > keys(). t/tree might be empty (rank = -1).
    * postcondition: none
    */   
   public int join(IAVLNode x, AVLTree t)
   {
	   return -1;
   }

	/** 
	 * public interface IAVLNode
	 * ! Do not delete or modify this - otherwise all tests will fail !
	 */
	public interface IAVLNode{	
		public int getKey(); // Returns node's key (for virtual node return -1).
		public String getValue(); // Returns node's value [info], for virtual node returns null.
		public void setLeft(IAVLNode node); // Sets left child.
		public IAVLNode getLeft(); // Returns left child, if there is no left child returns null.
		public void setRight(IAVLNode node); // Sets right child.
		public IAVLNode getRight(); // Returns right child, if there is no right child return null.
		public void setParent(IAVLNode node); // Sets parent.
		public IAVLNode getParent(); // Returns the parent, if there is no parent return null.
		public boolean isRealNode(); // Returns True if this is a non-virtual AVL node.
    	public void setHeight(int height); // Sets the height of the node.
    	public int getHeight(); // Returns the height of the node (-1 for virtual nodes).
	}

   /** 
    * public class AVLNode
    *
    * If you wish to implement classes other than AVLTree
    * (for example AVLNode), do it in this file, not in another file. 
    * 
    * This class can and MUST be modified (It must implement IAVLNode).
    */




  public class AVLNode implements IAVLNode{
  		private String info;
  		private int key;
  		private IAVLNode parent;
  		private IAVLNode left;
  		private IAVLNode right;
  		private int height = 0;
		private int size_left = 0;
		private int size_right = 0;
		private int size = size_left + size_right + 1;

		public AVLNode(int k, String i)
		{
			this.info = i;
			this.key = k;
			if(k != -1)
			{
				setRight(virtual_node);
				setLeft(virtual_node);
			}
			else
			{
				size = 0;
				setHeight(-1);
			}

		}
		public int getSize_left()
		{
			return this.size_left;
		}
		public int getSize_right()
		{
			return this.size_right;
		}
		public void setSize(int left, int right)
		{
			size_left = left;
			size_right = right;
		}
		public int getKey()
		{
			return this.key; // to be replaced by student code
		}
		public String getValue() {
			if (isRealNode()){
				return this.info; // to be replaced by student code
			} return null;
		}
		public void setLeft(IAVLNode node)
		{
			this.left = node; // to be replaced by student code
		}
		public IAVLNode getLeft()
		{
			return this.left; // to be replaced by student code
		}
		public void setRight(IAVLNode node)
		{
			this.right = node; // to be replaced by student code
		}
		public IAVLNode getRight()
		{
			return this.right; // to be replaced by student code
		}
		public void setParent(IAVLNode node)
		{
			this.parent=node; // to be replaced by student code
		}
		public IAVLNode getParent()
		{
			return this.parent; // to be replaced by student code
		}
		public boolean isRealNode()
		{
			return this.key!=-1; // to be replaced by student code
		}
	    public void setHeight(int height)
	    {
	       this.height = height; // to be replaced by student code
	    }
	    public int getHeight()
	    {
	      return this.getHeight(); // to be replaced by student code
	    }
  }

}
  
