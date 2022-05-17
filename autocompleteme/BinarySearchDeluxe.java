/*
    modified binary search implementation to allow searching for first or last index of a key
    @author Jonathon Zempel
    @version 02-17-2022
 */


import java.util.Comparator;

public class BinarySearchDeluxe {
    // Returns the index of the first key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null)
            throw new NullPointerException("one or more of the arguments is null; this cannot be");

        int hi = a.length - 1;
        int lo = 0;
        while (hi >= lo) {
            int mid = lo + (hi - lo) / 2;
            if (comparator.compare(a[mid], key) > 0)
                hi = mid - 1;
            else if (comparator.compare(a[mid], key) < 0)
                lo = mid + 1;
            else {
                while (mid >= 0 && comparator.compare(a[mid], key) == 0) {
                    mid--;
                }
                return mid + 1;
            }
        }
        return -1;
    }

    // Returns the index of the last key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null)
            throw new NullPointerException("one or more of the arguments is null; this cannot be");

        int hi = a.length - 1;
        int lo = 0;
        while (hi >= lo) {
            int mid = lo + (hi - lo) / 2;
            if (comparator.compare(a[mid], key) > 0)
                hi = mid - 1;
            else if (comparator.compare(a[mid], key) < 0)
                lo = mid + 1;
            else {
                while (mid < a.length && comparator.compare(a[mid], key) == 0) {
                    mid++;
                }
                return mid - 1;
            }
        }
        return -1;
    }

}
