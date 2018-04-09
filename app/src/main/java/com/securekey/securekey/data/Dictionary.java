package com.securekey.securekey.data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenbi on 3/21/18.
 */

//Dictionary implemented using a Trie Tree. Each node has Char-Node pairs as children
public class Dictionary {
    public Node root;
    private static Dictionary instance;
    public Set<String> searchResult;

    private Dictionary() {
        root= new Node();
        searchResult = new HashSet<String> () ;
    }

    public static Dictionary getInstance()
    {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        if (instance == null)
        {
            instance = new Dictionary();
        }
        return instance;
    }


    /**
     * Search through the dictionary for words.
     * @param string The string to be parsed.
     * @return void.
     */
    public void parse(String string) {
        searchResult.clear ();
        for (int i = 0; i<string.length ();i++){
             _parse (string.substring(i), root, 0);
        }
    }


    //Recursive method that searches through the Trie Tree to find all words.
    private void _parse (String string, Node node, int i) {

        if (node.endOfWord) {
                searchResult.add(string.substring (0,i));
        }

        if (i<string.length ()) {
            if (node.children.containsKey (string.charAt (i)) ) {
                _parse (string, node.children.get (string.charAt (i)), i + 1);
            }
        }
    }




    /**
     * Insert a word into the dictionary.
     * @param string The word to insert.
     */
    public void insert(String string) {
        if (string == null || string.length ()==0) return;

        insertWord(string, root);
    }

    //Recursive method that inserts a new word into the trie tree.
    private void insertWord(String string, Node node) {
        final Node child;
        if (node.children.containsKey(string.charAt(0))) {
            child = node.children.get(string.charAt(0));
        } else {
            child = new Node();
            node.children.put(string.charAt(0), child);
        }

        if (string.length() == 1) {
            child.endOfWord = true;
        } else {
            insertWord(string.substring(1),child);
        }
    }



}