import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.StringBuffer;

public class WordCounter
{
	
	public static void main(String[] args)
	{

		// Checking to see there are at least two file paths given.
		if(args.length < 2)
		{
			System.out.println("Not enough arguments given, exiting program.");
			System.exit(1);
		}

		// Create two empty binary search trees
		BSTree tree1, tree2;
		tree1 = new BSTree();
		tree2 = new BSTree();

		// Read the name of two input files that are
		// command line arguments of the program.
		String file1 = readFile(args[0]);
		String file2 = readFile(args[1]);

		// Checks to see if the file could've been read
		if(file1 == null)
		{
			System.out.println("ERROR: Cannot read file: "+args[0]+" exiting the program.");
			System.exit(1);
		}
		else if(file2 == null)
		{
			System.out.println("ERROR: Cannot read file: "+args[1]+" exiting the program.");
			System.exit(1);
		}

		// Adds the files into the trees
		tokenize(file1, tree1);
		tree2 = new BSTree(tree1.copy());

		// Unimportant words..
		String[] unimportantWords = file2.split("\\s+");
		for(int i = 0; i < unimportantWords.length; i++)
		{
			tree1.delete(unimportantWords[i]);
		}

		System.out.println("BST 1\n");
		tree1.printHeader();
		tree1.print();
		System.out.println("\n");

		System.out.println("BST 2\n");
		tree2.printHeader();
		tree2.print();
		System.out.println("\n");

		System.out.println("BST 1 (POSTORDER)\n");
		tree1.printHeader();
		tree1.printPostOrder();
		System.out.println("\n");

	}

	/**
	 * Method to read in a text file
	 *
	 * @param  fileName  the path to the file to be read
	 * @return           the contents of the file or null if a bad filePath is sent in
	 */
	public static String readFile(String fileName)
	{

		BufferedReader bf;
		StringBuffer fileContents = new StringBuffer();
		try
		{
			bf = new BufferedReader(new FileReader(fileName));
			String line = null;
			while((line = bf.readLine()) != null)
			{
				fileContents.append(" "+line);
			}
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
		catch(IOException e)
		{
			return null;
		}
		return fileContents.toString();
	}

	public static void tokenize(String file, BSTree tree)
	{
		String[] tokens = file.split("\\s+");
		for(int i = 0; i < tokens.length; i++)
		{
			tree.insert(tokens[i]);
		}
	}



}