//change the source to whatever you want.

const code = "ddddqqqqqqqqqqqqqqqqqqqqqqqqqdteqo3";

//64k stack size 
let STACK = [];

const LOOP_START = 'e'; //start the loop
const COPY       = 'z'; //copy current byte to clipboard
const INCREMENT  = 'q'; //increment current position on stack by one
const DECREMENT  = 'p'; //decrement current position on stack by one
const POINTER_L  = 'b'; //move the pointer to the left
const POINTER_R  = 'd'; //move the pointer to the right
const OUTPUT     = 'o'; //output the current byte
const PASTE      = '2'; //set the current byte to the clipboard
const LOOP_END   = '3'; //end the loop

const CLIPBOARD_IF_LO = '9'; //decrement if lower than clipboard
const CLIPBOARD_IF_UP = '6'; //increment if lower than clipboard

const DASH_UP = 'y'; //increase the value at pointer by 5
const DASH_DOWN = 'k'; //same thing as above but minus 5

const GOTO_HOME = '5'; //goto the HOME address

const ACTION_POINTER_ZERO = '0'; //set the value of the pointer pos to 0
const ACTION_POINTER_ADDR_64 = 't'; //set the stack value of pointer pos to 64 (@)

//address for the amount of times to loop through
const LOOP_TIMES_ADDR = 4;

const HOME_ADDRESS = 0;

let pointer = 0;
let loopTimes = 0;
let loopTimesCurrent = 0;
let gotoMark = 0;

let looping = false;

let clipboard = 0;

const instructions = code.match(/./g);
let outputString = "";

//js is dumb
STACK = Array.apply(null, Array(65536)).map(Number.prototype.valueOf, 0);

for(let i1 = 0; i1 < instructions.length; i1++) {
	loopTimes = STACK[LOOP_TIMES_ADDR];
	const c = instructions[i1];
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
			outputString = outputString + String.fromCharCode(STACK[pointer]);
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
			STACK[pointer] = (STACK[pointer] - 5);
			break;
		case DASH_UP:
			STACK[pointer] = (STACK[pointer] + 5);
			break;
	}
}

console.log(outputString);
