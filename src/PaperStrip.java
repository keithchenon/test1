public class PaperStrip {
    public static int minPieces(int[] original, int[] desired) {
        int cut =0;
        for ( int i =0; i<desired.length;i++){
            boolean diff =false;
            for ( int j=0; j<original.length;j++){
                if (desired[i]== original[j]){
                    continue;
                } else {
                    diff = true;
                    cut++;
                    break;
                }
            }
            diff=false;
        }
    return cut;
    }

    public static void main(String[] args) {
        int[] original = new int[] { 1, 4, 3, 2 };
        int[] desired = new int[] { 1, 2, 4, 3 };
        System.out.println(PaperStrip.minPieces(original, desired));
    }
}