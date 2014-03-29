//*******************************************************************
// CS4343 - Program 3
// Dawkins, Ryan
//
// Program to understand binary search trees.
//*******************************************************************

import java.lang.StringBuilder;
import java.text.DecimalFormat;

/**
 * BSTNode class is the class that will represent the token string that the parser reads.
 * It will not be very usable outside of the BSTree class
 *
 * @author     Ryan Dawkins
 * @implements Compareable<String>
 */
public class BSTNode implements Comparable<BSTNode>
{
	
	private String token;
	private int frequency;
	private BSTNode left;
	private BSTNode right;

	private static String SPACING = "  ";

	/**
	 * Creates the default BSTNode class when it is the tokens first occurence
	 *
	 * @param token the string that was found for the first time
	 */
	public BSTNode(String token)
	{
		// Creating a new BSTNode gives frequency 1
		this(token, 1);
	}

	/**
	 * Constructor that requires a token and frequency, 
	 * recommended to compare before.
	 *
	 * @param token      a string that has letters, punctuation, and numbers
	 * @param frequency  the frequency of the BSTNode if recreating it
	 */
	public BSTNode(String token, int frequency)
	{
		this.token = token;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	}

	/**
	 * Accessor for the String token
	 *
	 * @return the member variable token
	 */
	public String getToken()
	{
		return this.token;
	}

	public BSTNode setToken(String token)
	{
		this.token = token;
		return this;
	}

	public BSTNode setFrequency(int frequency)
	{
		this.frequency = frequency;
		return this;
	}

	/**
	 * compareTo method to compare the string tokens
	 * if the tokens are the same.
	 *
	 * @param  token  a string that is checking to see if in the tree.
	 * @return        the correct compare to number 0, -1, or 1
	 */
	public int compareTo(String token)
	{
		// Stores the integer so the function does not need
		// to be called multiple times
		int result = this.token.compareTo(token);

		// They are equal
		if(result == 0)
		{
			return 0;
		}
		// this.token is less than the parameter
		else if(result < 0)
		{
			return -1;
		}
		// this.token is greater than the parameter
		else
		{
			return 1;
		}
	}

	/**
	 * compareTo method to compare with a BSTNode object
	 *
	 * @param  node  node to compare the other node to
	 * @return       returns a 0, -1, or 1 to signify if it is equal less than or equal to
	 */
	public int compareTo(BSTNode node)
	{
		// Stores the integer so the function does not need
		// to be called multiple times
		int result = this.token.compareTo(node.getToken());

		// They are equal
		if(result == 0)
		{
			return 0;
		}
		// this.token is less than the parameter
		else if(result < 0)
		{
			return -1;
		}
		// this.token is greater than the parameter
		else
		{
			return 1;
		}
	}

	/**
	 * Increments the frequency since there is no setter for it.
	 *
	 * @return this for chainability
	 */
	public BSTNode incrementFrequency()
	{
		this.frequency++;
		return this;
	}

	/**
	 * Method to get the frequency of the amount of tokens that
	 * were found for this given node
	 *
	 * @return number of times the token this node represents was encountered
	 */
	public int getFrequency()
	{
		return this.frequency;
	}

	/**
	 * Mutator for left child node
	 *
	 * @param  left  child node
	 * @return       this for chainability
	 */
	public BSTNode setLeft(BSTNode left)
	{
		this.left = left;
		return this;
	}

	/**
	 * Accessor for the left child node
	 *
	 * @return left child node
	 */
	public BSTNode getLeft()
	{
		return this.left;
	}

	/**
	 * Mutator for the right child node
	 *
	 * @param  right  child node
	 * @return        this for chainability
	 */
	public BSTNode setRight(BSTNode right)
	{
		this.right = right;
		return this;
	}

	/**
	 * Accessor for the right child node
	 *
	 * @return right child node
	 */
	public BSTNode getRight()
	{
		return this.right;
	}

	/**
	 * Has children method simply checks the left and right node
	 *
	 * @return boolean whether or not there exists children
	 */
	public boolean hasChildren()
	{
		if(this.left != null || this.right != null)
		{
			return true;
		}
		return false;
	}

	/**
	 * toString method so you can print the 
	 *
	 * @param  totalCount  the count of all nodes sent in
	 * @return             String serialized version of this object
	 */
	public String toString(int totalCount)
	{
		double percent = ((0.0+this.frequency)/(totalCount)*100);

		StringBuilder s = new StringBuilder();
		s.append(String.format("%5s", ""+this.frequency));
		s.append(SPACING);
		s.append(String.format("%6s", (new DecimalFormat("0.00")).format(percent))).append("%");
		s.append(SPACING);
		s.append(this.token);

		return s.toString();
	}

}