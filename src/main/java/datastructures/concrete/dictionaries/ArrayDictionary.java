// Yasmine Hejazi and Jason Peacher-Ton
// 10/4/17
// CSE 373 Autumn 17

package datastructures.concrete.dictionaries;

import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;

/**
* See IDictionary for more details on what this class should do
*/
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    // You may not change or rename this field: we will be inspecting
    // it using our private tests.
    private Pair<K, V>[] pairs;
    
    // You're encouraged to add extra fields (and helper methods) though!
    private int size;
    private int sizeOfArray;
    
    public ArrayDictionary() {
        this.pairs = makeArrayOfPairs(4);
        this.size = 0;
        this.sizeOfArray = 4;
    }
    
    /**
    * This method will return a new, empty array of the given size
    * that can contain Pair<K, V> objects.
    *
    * Note that each element in the array will initially be null.
    */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        // It turns out that creating arrays of generic objects in Java
        // is complicated due to something known as 'type erasure'.
        //
        // We've given you this helper method to help simplify this part of
        // your assignment. Use this helper method as appropriate when
        // implementing the rest of this class.
        //
        // You are not required to understand how this method works, what
        // type erasure is, or how arrays and generics interact. Do not
        // modify this method in any way.
        return (Pair<K, V>[]) (new Pair[arraySize]);
        
    }
    
    @Override
    public V get(K key) {
        if (size == 0) {
            throw new NoSuchKeyException();
        }
        for (int i = 0; i < size; i++) {
            if (key == null) {
                if (pairs[i].key == null) {
                    return pairs[i].value;
                }
            } else if (key.equals(pairs[i].key)) {
                return pairs[i].value;
            }
        }
        // no key is found
        throw new NoSuchKeyException();
        
    }
    
    @Override
    public void put(K key, V value) {
        Pair<K, V> newPair = new Pair<K, V>(key, value);
        // check if key exists
        // if exists, update
        boolean exists = false;
        for (int i = 0; i < size; i++) {
            if (key == pairs[i].key) {
                pairs[i].value = value;
                exists = true;
            }
        }
        // check if array is full and needs to be resized
        if (size == sizeOfArray && !exists) {
            // make an temp array to store pairs
            Pair<K, V>[] newArray = makeArrayOfPairs(sizeOfArray);
            for (int i = 0; i < size; i++) {
                newArray[i] = pairs[i];
            }
            // double array size
            sizeOfArray = sizeOfArray * 2;
            // update original array and refill
            pairs = makeArrayOfPairs(sizeOfArray);
            for (int i = 0; i < size; i++) {
                pairs[i] = newArray[i];
            }
        }
        // appends
        if (size < sizeOfArray && !exists) {
            pairs[size] = newPair;
            size++;
        }
    }
    
    @Override
    public V remove(K key) {
        //check for key
        int index = -1;
        V removed;
        for (int i = 0; i < size; i++) {
            if (key == null) {
                if (pairs[i].key == null) {
                    index = i;
                }
            } else if (key.equals(pairs[i].key)) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchKeyException();
        }
        removed = pairs[index].value;
        // shift pairs to replace removed pair
        for (int i = index; i < size - 1; i++){
            pairs[i] = pairs[i + 1];
        }
        pairs[size - 1] = null;
        size--;
        return removed;
    }
    
    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (key == null) {
                if (pairs[i].key == null) {
                    return true;
                }
            } else if (key.equals(pairs[i].key)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    private static class Pair<K, V> {
        public K key;
        public V value;
        
        // You may add constructors and methods to this class as necessary.
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}