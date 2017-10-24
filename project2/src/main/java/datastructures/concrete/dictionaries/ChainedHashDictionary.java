package datastructures.concrete.dictionaries;

import datastructures.concrete.KVPair;
import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;
import misc.exceptions.NotYetImplementedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * See the spec and IDictionary for more details on what each method should do
 */
public class ChainedHashDictionary<K, V> implements IDictionary<K, V> {
    // You may not change or rename this field: we will be inspecting
    // it using our private tests.
    private IDictionary<K, V>[] chains;

    // You're encouraged to add extra fields (and helper methods) though!
    private int size; 		//number of elements in the hash table
    private int numBuckets; //number of buckets for storage in hash table
    
    //prime numbers to use in resizing each are roughly double than the previous index
    private int[] primeNumbers = {7, 13, 29, 59, 113, 227, 557, 1117}; 
    private int primeNumberIndex = 0; //current index in primeNumber array being used
    
    public ChainedHashDictionary() {
    	this.chains = makeArrayOfChains(7);
        this.size = 0;
        this.numBuckets = 7;
    }

    /**
     * This method will return a new, empty array of the given size
     * that can contain IDictionary<K, V> objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private IDictionary<K, V>[] makeArrayOfChains(int size) {
        // Note: You do not need to modify this method.
        // See ArrayDictionary's makeArrayOfPairs(...) method for
        // more background on why we need this method.
        return (IDictionary<K, V>[]) new IDictionary[size];
    }

    @Override
    public V get(K key) {
    	//get the hashcode for the key
        int hashCode = key.hashCode();
        //Variable to store value
        V value = null;
        //iterate through the array until you find the key
        for(int i = 0; i < chains.length; i++) {
        	if(hashCode == i) {
        		value = chains[i].get(key);
        	}
        }
		if(value == null) {
        	throw new NoSuchElementException();
        }
        return value;
    }

    @Override
    public void put(K key, V value) {
    	//make a KVPair using parameters
    	//create a hashcode for that KVPair
    	//Attempt to insert it into the array using that hashcode
    	
    	//Worst Case: Resize
			//check if load factor exceeds 0.75
			//if it does, make new Array table with the next prime number in primeNumber Array
			//update primeNumberIndex
			//iterate through all array indexes and rehash elements using new array size
    	
		//Create ArrayDictionary Case:
			//Create ArrayDictionary at that array index
			//store KVPair using put method
			//update size
    	
    	//best case: no need to resize or create ArrayDictionary
			//use that ArrayDictionary's put method to store KVPair
			//update size
    	
        throw new NotYetImplementedException();
    }

    @Override
    public V remove(K key) {
    	//Get hashcode from key
    	//Iterate through the array
    	//If it finds the key
    		//use the ArrayDictionary remove method to remove the key
    	//if not,
    		//throw NoSuchElementException
        throw new NotYetImplementedException();
    }

    @Override
    public boolean containsKey(K key) {
    	//Get hashcode from key
    	//Iterate through array using hashcode
    	//If it finds the key
    		//use the ArrayDictionary containsKey() method to check
    	//If it doesn't
    		//Throw NoSuchElementException()
        throw new NotYetImplementedException();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        // Note: you do not need to change this method
        return new ChainedIterator<>(this.chains);
    }

    /**
     * Hints:
     *
     * 1. You should add extra fields to keep track of your iteration
     *    state. You can add as many fields as you want. If it helps,
     *    our reference implementation uses three (including the one we
     *    gave you).
     *
     * 2. Before you try and write code, try designing an algorithm
     *    using pencil and paper and run through a few examples by hand.
     *
     * 3. Think about what exactly your *invariants* are. An *invariant*
     *    is something that must *always* be true once the constructor is
     *    done setting up the class AND must *always* be true both before and
     *    after you call any method in your class.
     *
     *    Once you've decided, write them down in a comment somewhere to
     *    help you remember.
     *
     *    You may also find it useful to write a helper method that checks
     *    your invariants and throws an exception if they're violated.
     *    You can then call this helper method at the start and end of each
     *    method if you're running into issues while debugging.
     *
     *    (Be sure to delete this method once your iterator is fully working.)
     *
     * Implementation restrictions:
     *
     * 1. You **MAY NOT** create any new data structures. Iterators
     *    are meant to be lightweight and so should not be copying
     *    the data contained in your dictionary to some other data
     *    structure.
     *
     * 2. You **MAY** call the `.iterator()` method on each IDictionary
     *    instance inside your 'chains' array, however.
     */
    private static class ChainedIterator<K, V> implements Iterator<KVPair<K, V>> {
        private IDictionary<K, V>[] chains;

        public ChainedIterator(IDictionary<K, V>[] chains) {
            this.chains = chains;
        }

        @Override
        public boolean hasNext() {
            throw new NotYetImplementedException();
        }

        @Override
        public KVPair<K, V> next() {
            throw new NotYetImplementedException();
        }
    }
}
