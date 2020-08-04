#include <iostream>
#include <cstdio>
#include <cstdlib>
#include "main.h"

int8_t STACK[65536];

int32_t pointer = 0;
int32_t loopTimes = 0;
int32_t loopTimesCurrent = 0;
int32_t gotoMark = 0;

int8_t looping = false;
int8_t clipboard = 0;

int main(int32_t argc, char*argv[]) {
    
    if(argc != 2) {
        printf("Usage: %s <code>\n", argv[0]);
        exit(1);
    }
    
    std::string code = argv[1];
    
    printf("Running %s with a length of %lu bytes.\n", argv[1], code.length());
    
    char* charArray = argv[1];
    
    for(int i = 0; i < code.length(); ++i) {
        loopTimes = STACK[LOOP_TIMES_ADDR];
        char c = charArray[i];
        switch(c) {
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
                printf("%c", STACK[pointer]);
                break;
            case PASTE:
                STACK[pointer] = clipboard;
                break;
            case CLIPBOARD_IF_UP:
                if(STACK[pointer] < clipboard)
                    STACK[pointer]++;
                break;
            case CLIPBOARD_IF_LO:
                if(STACK[pointer] < clipboard)
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
                gotoMark = i;
                loopTimesCurrent = loopTimes;
                looping = true;
                break;
            case LOOP_END:
                if(!looping) break;
                if(loopTimesCurrent > 0) {
                    i = gotoMark;
                    loopTimesCurrent--;
                }
                else looping = false;
                break;
            case DASH_DOWN:
                STACK[pointer] = STACK[pointer] - 5;
                break;
            case DASH_UP:
                STACK[pointer] = STACK[pointer] + 5;
                break;
        }
    }
    
    printf("\n"); //extra line to make it easier to read the output.
    
    return 0;
}
