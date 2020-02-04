package hw3.hash;

import java.util.*;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */

        Set<Oomage>[] buckets = new HashSet[M];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new HashSet<>();
        }
        for (Oomage oomage : oomages) {
            int hashCode = oomage.hashCode();
            int i = (hashCode & 0x7FFFFFFF) % M;
            buckets[i].add(oomage);
        }
        int N = oomages.size();
        for (Set bucket : buckets) {
            int num = bucket.size();
            if (num >= N / 2.5 || num <= N / 50) {
                return false;
            }
        }
        return true;
    }
}
