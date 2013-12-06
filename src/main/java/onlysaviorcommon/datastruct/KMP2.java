package onlysaviorcommon.datastruct;

/**
 * @file	KMP.java
 * @synopsis 	An implementation of KMP matching, derived from
 *		a modification of the Match.java program.
 *		Again, the goal is to find an occurrence of a pattern P in a text T.
 *		CONVENTION:
 *		The first index (i=0) of arrays are not used.
 *
 * @author	Chee Yap
 * @date	Apr 25, 2001 (for Basic Algorithms class)
 */

public class KMP2 {

    // Members:
    char[] T;	// This is the text
    char[] P;	// This is pattern
    int [] fail;	// Failure function for pattern

    // Constructors:

    KMP2(char[] p, char[] t) {
        P = p; T = t;
        computeFail();
    } //

    // Methods:

    /******************************************************
     Routine to compute the failure function
     ******************************************************/
    public void computeFail() {
        // init:
        fail = new int[P.length];
        fail[0] = 0;
        // loop:
        for (int k=2; k< fail.length; k++) {
            int kk = fail[k-1];
            while (kk>0 && (P[kk + 1] != P[k]))
                kk = fail[kk];
            fail[k] = 1 + kk;
        }
        print(fail);
    } // computeFail(P)

    /******************************************************
     THIS IS THE MAIN ROUTINE
     ******************************************************/
    public int find(int start) {
        // init:
        int j = start; // text index
        int k = 0; // pattern index
        // loop:
        while (j < T.length && k < P.length) {
            //if (k >= P.length) return(j - k + 1);
            if ((T[j] == P[k])) {
                j++; k++;
            } else if(k == 0) {
                j++;
            } else {
                k = fail[k];	// k could become 0
            }
        } // while

        if (k == P.length)
            return j -k + 1;
        // Not found:
        return(-1);
    } // find()

    /******************************************************
     prints data
     ******************************************************/
    void output() {
        System.out.print("> Pattern = \"");
        for (int i=0; i< P.length; i++)
            System.out.print(P[i]);
        System.out.print("\"\n> Text    = \"");
        for (int i=0; i< T.length; i++)
            System.out.print(T[i]);
        System.out.println("\"");
    } // output()

    void print(int[] arr) {
        for(int a : arr) {
            System.out.print(a);
        }
        System.out.println();
    }


    /******************************************************
     Main method
     ******************************************************/
    public static void main( String[] args) {

        // sample input with 6 keys
        //	(the first is a dummy key)

        // char[] p = {'0', 'o', 'u'};
        char[] p = {'a', 'b', 'c','a','b','c'};
        char[] t = {
                'a', 'b', 'e', ' ', 'a', 'b', 'c', 'f',
                'a', ' ', 'a', 'b', 'c', 'a', 'b', 'c'};

        // construct a KMP object
        KMP2 m = new KMP2(p, t);
        m.output();  	// print data

        // find all matches
        int f = m.find(0);
        if (f<1)
            System.out.println(">>  No match found");
        else {
            while (f>=1) {
                System.out.println(">>  Match found at position " + f);
                f = m.find(f+1);
            }//while
        }//else
    } // main

}//class KMP
