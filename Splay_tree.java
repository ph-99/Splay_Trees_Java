import java.io.*; 
import java.util.*;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;
import java.io.*; 
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue; 
import java.util.Stack;

public class Splay_tree {
	static class node {
		int key;
		node left , right;
		
	};
	//allocates a new node with the given key
	static node newNode(int key){
		node Node = new node();
		Node.key = key;
		Node.left = Node.right = null;
		return (Node);
		
	}
	
	
	/* y                                     x
	  / \     Zig (Right Rotation)          /  \
	 x   T3   – - – - – - – - - ->         T1   y 
	/ \       < - - - - - - - - -              / \
   T1  T2     Zag (Left Rotation)            T2   T3 */
	
	// to rotate right 
	static node rightRotate(node x){
		node y = x.left;
		node t2 = y.right;
		
		// rotation
		
		y.right = x;
		x.left=t2;
		return y;
		
	}
	// to left rotate
	static node leftRotate(node x)  
	{  
		node y = x.right;
		node t2 = y.left;
		
		// rotation
		
		
		y.left = x;
		x.right=t2;
		return y;
	}  
	/* This function brings the key at root if key is present in tree.  
	If key is not present, then it brings the last accessed item at  root. 
	This function modifies the tree and returns the new root  */

	static node splay(node root , int key){
		if(root==null || root.key == key)
			return root;
		
		if(key<root.key){
			if(root.left == null)
				return root;
			//Zig-Zig (L-L)
			if(key< root.left.key){
				//first rotation for root
				root.left.left = splay(root.left.left , key);
				root = rightRotate(root);
				
			}
			//Zig-Zag (L-R)
			else if(key>root.left.key){
				root.left.right = splay(root.left.right , key);
				if(root.left.right != null)
					//first rotation for root.left 
					root.left = leftRotate(root.left);
			}
			// second rotation for root
			return(root.left == null) ? root : rightRotate(root);
				
		}
		
		else{
			
			if (root.right == null) 
				return root;  
  
			// Zag-Zig (Right Left)  
			if ( key<root.right.key ){
				root.right.left = splay(root.right.left , key);
				// Do first rotation for root.right  
				if (root.right.left != null)  
					root.right = rightRotate(root.right);
				
			}
			// Zag-Zag (Right Right) 
			else if(root.right.key <key){
				
				root.right.right = splay(root.right.right , key);
				root =leftRotate(root);
			}
			// Do second rotation for root
			return (root.right == null) ? root : leftRotate(root);
        
		
		}


	}
	
	/*  The search function for Splay tree. 
	Note that this function returns the new root of Splay Tree.
	If key is  present in tree then, it is moved to root. */
	
	static node search(node root , int key){
		return splay(root,key);
	}
	// preorder traversal of the tree. 
	static void preOrder(node root){
		if(root!= null){
			System.out.print(root.key + " " );
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	public static void main(String[] args) {  
    node root = newNode(100);  
    root.left = newNode(50);  
    root.right = newNode(200);  
    root.left.left = newNode(40);  
    root.left.left.left = newNode(30);  
    root.left.left.left.left = newNode(20);  
  
    root = search(root, 20);  
    System.out.print("Preorder traversal of the" + " modified Splay tree is \n");  
    preOrder(root);  
	}  
	
	

}