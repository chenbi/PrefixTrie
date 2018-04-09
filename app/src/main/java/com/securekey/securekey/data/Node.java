package com.securekey.securekey.data;

import java.util.HashMap;

/**
 * Created by chenbi on 3/21/18.
 */

public class Node {
    public Boolean endOfWord = false; //mark the end of a word, last char of word is this node's key pair in its parent's children
    public HashMap<Character,Node> children = new HashMap<Character,Node> ();
}