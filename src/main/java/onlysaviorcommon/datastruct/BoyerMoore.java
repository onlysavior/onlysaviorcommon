package onlysaviorcommon.datastruct;

/**
 * Created with IntelliJ IDEA.
 * User: yanye.lj
 * Date: 13-12-6
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public class BoyerMoore {

    private static final int MAX_CHAR = 256;
    private int[] goodShift;
    private int[] badShift;

    private char[] pattern;
    private char[] text;

    public BoyerMoore(String text, String pattern) {
        this.text = text.toCharArray();
        this.pattern = pattern.toCharArray();

        goodShift = new int[pattern.length()];
        for(int i = 0; i < pattern.length(); i++) {
            goodshift(this.pattern,i);
        }

        badShift = new int[MAX_CHAR];
    }

    public int find() {
        int offset = 0;
        int scan = 0;
        int last = pattern.length - 1;
        int maxoffset = text.length - pattern.length;


        for(int i = 0; i < last; i++){
            badShift[pattern[i]] = last - i;
        }


        // needle can still be inside haystack
        while(offset <= maxoffset){

            // start at end of pattern and match backwards
            for(scan = last; pattern[scan] == text[scan+offset]; scan--){

                // we have a match
                if(scan == 0){
                    return offset;
                }
            }


            // shift as much as possible based on the good and bad shift
            offset += Math.max(badShift[text[offset + last]],
                    goodShift[last - scan]);

        }
        // indicates no match
        return -1;
    }

    private int goodshift(char[] pattern, int match) {
        if(match >= pattern.length || match < 0) {
            return -1;
        }

        if(match == 0)
            return 1;

        int max = pattern.length - 1;
        int offset = max;
        int last = match - 1;

        while (offset > 0) {
            --offset;  //back one step
            for(int i = 0; (offset - i >= 0) && pattern[(max - i)] == pattern[(offset - i)] ; i++){

                if( (offset - i) == 0 ){
                    return max - offset;
                }

                if(i == last){
                    // next char must be missmatch for this to count
                    if( pattern[(max - match)] != pattern[(offset - match)] ){
                        return max - offset;
                    }else{
                        break;
                    }
                }
            }
        }
        return pattern.length;
    }
}
