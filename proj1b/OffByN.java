public class OffByN implements CharacterComparator {
    private int offBy;
    public OffByN(int N) {
        this.offBy = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return Math.abs(diff) == offBy ? true : false;
    }
}
