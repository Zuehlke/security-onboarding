## JS standard library ##

## Array manipulation ##

    //Add items to array
    var arr = [];
    arr.push(1);
    arr.push(2);
    arr.push(3);
    console.log(arr);


    //Removes the last element from an array and returns that element.
    console.log(arr.pop());  // 3
    console.log(arr); 		// [1,2]


	//.shift() removes the first element from an array and returns that element.
    var myArray = [1,2,3];    
    myArray.shift();
    console.log(myArray); //[2, 3]
    

    //.unshift() adds one or more elements to the front of an array and returns the new length of the array.
    var myArray = [1,2,3];
    myArray.unshift(0);
    console.log(myArray); //[0, 1, 2, 3]

	
	//.splice() removes a certain number of items from the given index
    var myArray = [0,1,2,3,4,5,6,7,8,9];
    var splice = myArray.splice(3,5);
    
    console.log(splice);	// will print out 3,4,5,6,7
    console.log(myArray);   // will print out 0,1,2,8,9


## String manipulation ##

    //indexOf() method returns the index of (the position of) 
	//the first occurrence of a specified text in a string:
    var str = "Please locate where 'locate' occurs!";
    var pos = str.indexOf("locate");


    //The charAt() method returns the character at a specified index (position) in a string:
    var str = "HELLO WORLD";
    str.charAt(0); // returns H


    //concat() joins two or more strings:
    var text1 = "Hello";
    var text2 = "World";
    text3 = text1.concat("	",text2); //"Hello	World"


    //Converting to Upper and Lower Case
    var text1 = "Hello World!"; 
    var text2 = text1.toUpperCase();
    var text2 = text1.toLowerCase();

    //slice() extracts a part of a string and returns the extracted part in a new string.
    var str = "Apple, Banana, Kiwi";
    var res = str.slice(7,13);

	//If a parameter is negative, the position is counted from the end of the string.
	var res = str.slice(-12,-6); 


    //A string can be converted to an array with the split() method.
    var txt = "a,b,c,d,e";   // String
    txt.split(",");  // Split on commas


## Regex ##

	//The match() method searches a string for a match against a regular expression, 
	//Returns the matches, as an Array object.
	var str = "The rain in SPAIN stays mainly in the plain"; 
    var res = str.match(/ain/g);

## Math Functions ##

    var x = Math.PI;	// Returns PI
    
    var y = Math.sqrt(16);  // Returns the square root of 16
    
    Math.random();   				// returns a random number
    
    Math.min(0, 150, 30, 20, -8, -200);  // returns -200
    
    Math.max(0, 150, 30, 20, -8, -200);  // returns 150
    
    Math.round(4.7);	// returns 5
    
    Math.round(4.4);	// returns 4
    
    Math.ceil(4.4); 	// returns 5
    
    Math.floor(4.7);	// returns 4

## Date Functions ##

	//getTime() returns the number of milliseconds since January 1, 1970:
    var d = new Date();
    var time = d.getTime();


<table>
	<thead>
		<tr>
		  <th>Method</th>
		  <th>Description</th>
		</tr>
	</thead>
	<tbody>
		<tr>
		  <td>getDate()</td>
		  <td>Get the day as a number (1-31)</td>
		</tr>
		<tr>
		  <td>getDay()</td>
		  <td>Get the weekday as a number (0-6)</td>
		</tr>
		<tr>
		  <td>getFullYear()</td>
		  <td>Get the four digit year (yyyy)</td>
		</tr>
		<tr>
		  <td>getHours()</td>
		  <td>Get the hour (0-23)</td>
		</tr>
		<tr>
		  <td>getMilliseconds()</td>
		  <td>Get the milliseconds (0-999)</td>
		</tr>
		<tr>
		  <td>getMinutes()</td>
		  <td>Get the minutes (0-59)</td>
		</tr>
		<tr>
		  <td>getMonth()</td>
		  <td>Get the month (0-11)</td>
		</tr>
		<tr>
		  <td>getSeconds()</td>
		  <td>Get the seconds (0-59)</td>
		</tr>
		<tr>
		  <td>getTime()</td>
		  <td>Get the time (milliseconds since January 1, 1970)</td>
		</tr>
	</tbody>
</table>

## Best Practices ##

You may come upon the eval function. It is used to evaluate any string as JavaScript code. Example:

	var someText = "4 + 5";
	var a = eval(someText); // Here a === 9

The eval() function is used to run text as code. In almost all cases, it should not be necessary to use it.

Because it allows arbitrary code to be run, it also represents a security problem.

> **Avoid using eval() function!**