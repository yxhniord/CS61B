public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    /**
     * A palindrome is defined as a word that is the same whether it is read forwards or backwards.
     * Any word of length 1 or 0 is a palindrome.
     * Use recursion.
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        return isPalindromeHelper(d);
    }

    private boolean isPalindromeHelper(Deque d) {
        if (d.size() <= 1) {
            return true;
        } else {
            if (d.removeFirst() == d.removeLast()) {
                return isPalindromeHelper(d);
            } else {
                return false;
            }
        }
    }

    /**
     * OffByOne Palindrome
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque d = wordToDeque(word);
        return isPalindromeHelper(d, cc);
    }

    private boolean isPalindromeHelper(Deque d, CharacterComparator cc) {
        if (d.size() <= 1) {
            return true;
        } else {
            if (cc.equalChars((char)d.removeFirst(), (char)d.removeLast())) {
                return isPalindromeHelper(d, cc);
            } else {
                return false;
            }
        }
    }

}
