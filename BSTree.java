//*******************************************************************
// CS4343 - Program 3
// Dawkins, Ryan
//
// Program to understand binary search trees.
//*******************************************************************

/**
 * BSTree class to handle strings of tokens and their frequencies
 *
 * @author Ryan Dawkins
 */
public class BSTree
{

    private BSTNode root;
    private BSTNode deletedNode;

    public BSTree()
    {
        this(null);
    }

    /**
     * Default constructor that creates an empty tree
     *
     */
    public BSTree(BSTNode root)
    {
        // Setting the root node to null means the basis case in the
        // insertion method will happen and will just create a null 
        // BSTNode
        this.root = root;
        this.deletedNode = null;
    }

    public BSTNode getRoot()
    {
        return this.root;
    }

    /**
     * Returns the count of the number of nodes in the tree
     * but is a representation of the number of tokens
     *
     * @return number of nodes in the tree
     */
    public int count()
    {
        return count(this.root);
    }

    /**
     * Recursive method to get the count of the 
     *
     */
    public int count(BSTNode node)
    {
        if(node == null)
            return 0;
        return node.getFrequency()+count(node.getLeft())+count(node.getRight());
    }

    /**
     * Inserts a token into the BST, if the token is found
     * it increments the frequency of that node and does not
     * create a duplicate.
     *
     * @param  token
     * @return void
     */
    public void insert(String token)
    {
        // Removes all junk around the token
        // to the given specifications
        token = toUpperAndTrim(token);

        // Making sure we are not receiving any bad input
        // this uses an or because of short circuiting to optimize code
        if( !(token == "" || token.length() == 0))
        {
            this.root = insert(token, this.root);
        }
    }

    /**
     * Recursive method to insert a token into the BST
     *
     * @param token  string that has a frequency and BSTNode associated with it
     * @param root   the root node above the token 
     */
    public BSTNode insert(String token, BSTNode root)
    {

        // Basis case to see if the root is null
        if(root == null)
        {
            return new BSTNode(token);
        }

        // Checks to see where the token belongs in the tree
        // and where to traverse left or right, if the node is
        // equal to the token then the frequency is incremented
        // rather than creating a duplicate value.
        int result = root.compareTo(token);
        
        // Changes the left node since the node is greater than the 
        // given token
        if(result > 0)
        {
            BSTNode left = insert(token, root.getLeft());
            root.setLeft(left);
        }
        // Changes the right node since the node is less than the
        // given token
        else if(result < 0)
        {
            BSTNode right = insert(token, root.getRight());
            root.setRight(right);
        }
        // Default case if the nodes keys are equal
        else
        {
            root.incrementFrequency();
        }
        return root;
    }

    /**
     * Method to node by a given token
     *
     * @param  token  the token that signifies which node to be deleted
     * @return        the node that was found and deleted
     */
    public BSTNode delete(String token)
    {
        token = toUpperAndTrim(token);
        if( !(token == "" || token.length() == 0))
        {
            BSTNode found = delete(token, this.root);
            return this.deletedNode;
        }
        else
        {
            return null;
        }
    }

    /**
     * Deletion method by using the lecture notes from 03-05
     *
     * @param  token  the string that should be found and deleted
     * @param  root   the root node sent in to search the tree with
     * @return        the node that was found and deleted
     */
    public BSTNode delete(String token, BSTNode root)
    {

        // All cases given were in the textbook/lecture notes...
        // Case 1
        if(root == null)
        {
            return root;
        }
        // Case 2
        if(token.compareTo(root.getToken()) < 0)
        {
            root.setLeft(delete(token, root.getLeft()));
        }
        // Case 3
        else if(token.compareTo(root.getToken()) > 0)
        {
            root.setRight(delete(token, root.getRight()));
        }
        // Case 4.a
        else if(root.getLeft() != null && root.getRight() != null)
        {
            BSTNode min = findMin(root.getRight());
            this.deletedNode = new BSTNode(root.getToken()).setFrequency(root.getFrequency());
            root.setToken(min.getToken());
            root.setRight(delete(root.getToken(), root.getRight()));
        }
        // Case 4.b-d
        else
        {
            this.deletedNode = new BSTNode(root.getToken()).setFrequency(root.getFrequency());
            root = (root.getLeft() != null) ? root.getLeft() : root.getRight();
        }
        return root;
    }

    /**
     * Method to find the left most node in the root of the tree given
     *
     * @param  root  start node given
     * @return       the node that is the smallest node in the tree
     */
    private BSTNode findMin(BSTNode root)
    {
        if(root == null || root.getLeft() == null)
        {
            return root;
        }
        else
        {
            return findMin(root.getLeft());
        }
    }

    /**
     * Calls the copy method 
     *
     * @return the copy of the root node of the tree
     */
    public BSTNode copy()
    {
        return copy(this.root);
    }

    /**
     * Recieves a root node and copies the structure by using
     * a preorder traversal method
     *
     * @param  root  the top node of a tree which will return a copy of this
     *               this node and it's contents below
     * @return       a copy of the root node given
     */
    public BSTNode copy(BSTNode root)
    {
        if(root == null)
        {
            return null;
        }

        BSTNode node = new BSTNode(root.getToken());
        node.setFrequency(root.getFrequency());
        node.setLeft(root.getLeft());
        node.setRight(root.getRight());
        return node;
    }

    /**
     * Method to print the header so that there's no duplicate code
     *
     * @return void
     */
    public void printHeader()
    {
        System.out.println("COUNT  PERCENT  TOKEN");
        System.out.println("-----  -------  --------------------");
    }

    /**
     * Prints a inorder traversal representation of the tree
     * and calls the traverseInOrder(BSTNode) method.
     *
     * @return void
     */
    public void print()
    {
        traverseInOrder(this.root);
    }

    /**
     * Method to print in post order and calls the traverse
     * post order method.
     *
     * @return void
     */
    public void printPostOrder()
    {
        traversePostOrder(this.root);
    }

    /**
     * In order traversal that prints the root node
     *
     * @param  root  the root node to be printed
     * @return       void
     */
    public void traverseInOrder(BSTNode root)
    {
        if(root == null)
        {
            return;
        }
        else
        {
            traverseInOrder(root.getLeft());
            System.out.println(root.toString(this.count()));
            traverseInOrder(root.getRight());
            return;
        }
    }

    /**
     * Method to print and traverse in post order by parameterizing
     * the root node of the BSTree
     *
     * @param  root  the root node of the tree
     * @return       void
     */
    public void traversePostOrder(BSTNode root)
    {
        if(root == null)
        {
            return;
        }
        else
        {
            traversePostOrder(root.getLeft());
            traversePostOrder(root.getRight());
            System.out.println(root.toString(this.count()));
        }
    }

    /**
     * Method to remove trimming punctuation and to turn all
     * letters to uppercase.
     *
     * @param  token  a string that has punctuation
     * @return        trimmed and uppercase version of the token
     */
    public static String toUpperAndTrim(String token)
    {
        // * The first regular expression ^[^a-zA-Z]+ checks to remove
        //   all characters that are not punctuation until it reaches
        //   something that is a character.
        // * The second regular expression [^a-zA-Z]+$ checks and removes
        //   all characters that are not characters until the end of the string.
        // * The final method is a built in method for the string object
        return token.replaceFirst("^[^a-zA-Z]+", "").replaceAll("[^a-zA-Z]+$", "").trim().toUpperCase();
    }

}