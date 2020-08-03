import org.jetbrains.annotations.NotNull;

/**
 * https://www.asciitable.com/
 * 
 * this does NOT SUPPORT NESTED LOOPS!
 * 
 * loops in this language are do-while, they will execute then check the condition.
 * 
 * @author Alexander
 */
public class E62qpodb593 {
    
    //64k stack size
    private static final byte[] STACK = new byte[65536];
    
    private static final char LOOP_START = 'e'; //start the loop
    private static final char COPY       = 'z'; //copy current byte to clipboard
    private static final char INCREMENT  = 'q'; //increment current position on stack by one
    private static final char DECREMENT  = 'p'; //decrement current position on stack by one
    private static final char POINTER_L  = 'b'; //move the pointer to the left
    private static final char POINTER_R  = 'd'; //move the pointer to the right
    private static final char OUTPUT     = 'o'; //output the current byte
    private static final char PASTE      = '2'; //set the current byte to the clipboard
    private static final char LOOP_END   = '3'; //end the loop
    
    private static final char CLIPBOARD_IF_LO = '9'; //decrement if lower than clipboard
    private static final char CLIPBOARD_IF_UP = '6'; //increment if lower than clipboard
    
    private static final char DASH_UP = 'y'; //increase the value at pointer by 5
    private static final char DASH_DOWN = 'k'; //same thing as above but minus 5
    
    private static final char GOTO_HOME = '5'; //goto the HOME address
    
    private static final char ACTION_POINTER_ZERO = '0'; //set the value of the pointer pos to 0
    private static final char ACTION_POINTER_ADDR_64 = 't'; //set the stack value of pointer pos to 64 (@)
    
    //address for the amount of times to loop through
    private static final int LOOP_TIMES_ADDR = 4;
    
    private static final int HOME_ADDRESS = 0;
    
    //the address for the stack
    private static int pointer = 0;
    
    //even though this is an int, it caps at 127 loops.
    private static int loopTimes = 0;
    private static int loopTimesCurrent = 0;
    private static int gotoMark = 0;
    
    private static boolean looping = false;
    
    //clipboard data
    private static byte clipboard = 0;
    
    //minified hello world
    private static final String MIN = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqzod2qqqqqqqqqqqqqqqqqqqqqqqqqqqqqzod2qqqqqqqzood2qqqzodqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqodbbbbbzdddd2qqqqqqqqqqqqqqqodbbzddd2zod2qqqodbbbbbbzdddddd2zodbbbbbbbbzdddddddd2po";
    
    //hello world explained
//    private static final String TEST_STR = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqzod" + //H
//                                                   "2qqqqqqqqqqqqqqqqqqqqqqqqqqqqqzod" + //e
//                                                   "2qqqqqqqzood" + //ll
//                                                   "2qqqzod" + //o
//                                                   "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqod" + //space
//                                                   "bbbbbzdddd2qqqqqqqqqqqqqqqod" + //W
//                                                   "bbzddd2zod" + //o
//                                                   "2qqqod" + //r
//                                                   "bbbbbbzdddddd2zod" + //l
//                                                   "bbbbbbbbzdddddddd2po"; //d
    
//    private static final String TEST_STR = "dddd" + //go to loop address (4)
//                                                   "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq" + //set loop amount to 25 (26 loops) 
//                                                   "d" + //pointer right 1
//                                                   "t" + //set current value to 64
//                                                   "e" + //start loop
//                                                   "q" + //add 1 to the address where 64 was set (64 is @, 65 is A)
//                                                   "o" + //output
//                                                   "3" +
//                                                   "qqqqqq" +
//                                                   "e" +
//                                                   "q" +
//                                                   "o" +
//                                                   "3" +
//                                                   "";
    
    private static final String TEST_STR = "tqqqyoyyyyyypoyqqooqqqoztkkkkotkkkkkkppotyyyyyppo2oqqqokpokkqqotkkkkkkpo";
    
    
    public static void main(String[] args) {
        System.out.printf("Computing string %s with a length of %d bytes.%n", TEST_STR, TEST_STR.length());
        run(TEST_STR);
    }
    
    private static void run(@NotNull String s) {
        char[] charArray = s.toCharArray();
        for(int i1 = 0; i1 < charArray.length; i1++) {
            loopTimes = STACK[LOOP_TIMES_ADDR];
            char c = charArray[i1];
            switch (c) {
                case COPY:
                    clipboard = STACK[pointer];
                    break;
                case INCREMENT:
                    STACK[pointer]++;
                    break;
                case DECREMENT:
                    STACK[pointer]--;
                    break;
                case POINTER_L:
                    pointer--;
                    break;
                case POINTER_R:
                    pointer++;
                    break;
                case OUTPUT:
                    System.out.print((char) STACK[pointer]);
                    break;
                case PASTE:
                    STACK[pointer] = clipboard;
                    break;
                case CLIPBOARD_IF_UP:
                    if (STACK[pointer] < clipboard)
                        STACK[pointer]++;
                    break;
                case CLIPBOARD_IF_LO:
                    if (STACK[pointer] < clipboard)
                        STACK[pointer]--;
                    break;
                case GOTO_HOME:
                    pointer = HOME_ADDRESS;
                    break;
                case ACTION_POINTER_ZERO:
                    STACK[pointer] = 0;
                    break;
                case ACTION_POINTER_ADDR_64:
                    STACK[pointer] = 64;
                    break;
                case LOOP_START:
                    gotoMark = i1;
                    loopTimesCurrent = loopTimes;
                    looping = true;
                    break;
                case LOOP_END:
                    if(!looping) break;
                    if(loopTimesCurrent > 0) {
                        i1 = gotoMark;
                        loopTimesCurrent--;
                    }
                    else looping = false;
                    break;
                case DASH_DOWN:
                    STACK[pointer] = (byte) (STACK[pointer] - 5);
                    break;
                case DASH_UP:
                    STACK[pointer] = (byte) (STACK[pointer] + 5);
                    break;
            }
        }
    }
    
}
