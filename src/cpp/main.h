//
// Created by alexander on 8/4/20.
//

#ifndef E62QPODB593CPP_MAIN_H
#define E62QPODB593CPP_MAIN_H

#endif //E62QPODB593CPP_MAIN_H

#define LOOP_START  'e' //start the loop
#define COPY        'z' //copy current byte to clipboard
#define INCREMENT   'q' //increment current position on stack by one
#define DECREMENT   'p' //decrement current position on stack by one
#define POINTER_L   'b' //move the pointer to the left
#define POINTER_R   'd' //move the pointer to the right
#define OUTPUT      'o' //output the current byte
#define PASTE       '2' //set the current byte to the clipboard
#define LOOP_END    '3' //end the loop

#define CLIPBOARD_IF_LO  '9' //decrement if lower than clipboard
#define CLIPBOARD_IF_UP  '6' //increment if lower than clipboard

#define DASH_UP  'y' //increase the value at pointer by 5
#define DASH_DOWN  'k' //same thing as above but minus 5

#define GOTO_HOME  '5' //goto the HOME address

#define ACTION_POINTER_ZERO  '0' //set the value of the pointer pos to 0
#define ACTION_POINTER_ADDR_64  't' //set the stack value of pointer pos to 64 (@)

//address for the amount of times to loop through
#define LOOP_TIMES_ADDR 4

#define HOME_ADDRESS  0