/*
 * This is a JavaScript Scratchpad.
 *
 * Enter some JavaScript, then Right Click or choose from the Execute Menu:
 * 1. Run to evaluate the selected text (Ctrl+R),
 * 2. Inspect to bring up an Object Inspector on the result (Ctrl+I), or,
 * 3. Display to insert the result in a comment after the selection. (Ctrl+L)
 */

var connection = new WebSocket('ws://localhost:8080/connect');
var keepOpen = false;

connection.onopen = function () {
  console.log('Connection opened');
  while(keepOpen){
   setTimeout(function(){console.log('...\n')},5000);
  }
};

connection.onerror = function (error) {
  console.log('WebSocket Error ' + error);
};

connection.onmessage = function (e) {
  console.log('Server: ' + e.data);
};



setTimeout(function(){keepOpen = false;},20000);


