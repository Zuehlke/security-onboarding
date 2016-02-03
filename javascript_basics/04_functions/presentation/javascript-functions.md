# Javascript functions

## Function basics
### Declaring and using a function

	function add(a, b) {
		return a + b;
	}

	add(2, 2) === 4;

> Note: semicolon is not required after a function declaration

### Function declarations may be nested

	function arraySum(numbers) {
		var total = 0;
		for(var i = 0; i < numbers.length; i++) {
			total += add(total, numbers[i];
		}
	
		function add(a, b) {
			return a + b;
		}
	}


### Anonymous functions (function literals)
Functions is not given a name, but can be used as a ***function literal*** and stored in a variable in the same way as any other data type would be (e.g. number or a string)

	var add = function (a, b) {
		return a + b;
	};
	
	add(2, 2) === 4;

> Note: semicolon is required when assigning a function literal to a variable (as it would be with any other type of assignment)

## Scopes
The only way of creating a scope for variables in javascript is by declaring a function.

- variables declared in the body of the function are locally scoped in that function.
- new local scope is created for each function call
		
		var someDummyGlobalVariable = 1; //this variable belongs to global scope
		
		function add(a, b) {
			var addResult = a + b; //this variable belongs to the local scope of add function
			return addResult;
		}

> Pitfall: flow control structures do **NOT** have their local scopes
> 
	if(true) {
		var iShouldBeLocal = "right?";
	}
	iShouldBeLocal !== undefined; //Variable is defined!
>
	while(true) {
		var iShouldBeLocalToo = "right?";
	 	break;
	}
	iShouldBeLocalToo !== undefined; //Variable is defined!
>
	for(var i = 0; i < 5; i++) {
	 	....
	}	 
	i !== undefined; //Variable is defined!
	i === 5;
>


## Closures
Each function declaration additionally creates a **closure**, which means that from within a function, variables of the outer scope are accessible:

- for reading

		var b = 2;
		var addWithSideEffect = function (a) {
			return a + b;
		};
		
		addWithSideEffect(2) === 4;

- and writing

		var result;
		var addWithSideEffect = function (a, b) {
			result = a + b;
		};

		addWithSideEffect(2, 2);
		result === 4;


## Higher order functions
Since functions can be stored in variables and used as literals, functions can also be:

- passed as input parameters to other functions

		/** Register event handler to be executed when some event happens **/
		function handleButtonClick() {
		  alert("Are you sure?");
		}
		$element.on("click",  handleButtonClick);
		
		//or (using a more concise syntax) by passing an anonymous function as parameter
		$element.on("click", function() {
		  alert("Are you sure?");
		});



		/** For each element in an array **/
		var arr = [1, 2, 3];
		arr.forEach(function(el) {
		  console.log(el);
		}); //prints out 123


		/** do something after some time passes */
		setTimeout(function() {
			alert("I get executed after 1s");
		}, 1000);

- returned as a result of a function

		function multiplier(factor) {
		  return function(number) {
		    return number * factor;
		  };
		}
		
		var twice = multiplier(2);
		twice(5) === 10;

### Quiz time:
Quiz 1:

	function wrapValue(n) {
	  var localVariable = n;
	  return function() { return localVariable; };
	}
	
	var wrap1 = wrapValue(1);
	var wrap2 = wrapValue(2);
	console.log(wrap1()); // ???
	console.log(wrap2()); // ???
Quiz 2:

	var a = 0;
	
	setTimeout(function() {
		a++;
	}, 1);
	
	setTimeout(function() {
		a++;
	}, 2);
	
	setTimeout(function() {
		console.log(a);
	}, 3); // ???
	
### Interactive exercises

The following excercises should be opened in a web browser.
When opened: an in-browser code editor will be show together with the explanation of the goal of the exercise and some hints regarding to what needs to be done

- [Exercise 1](../tasks/index.html#1)
- [Exercise 2](../tasks/index.html#2)


**Best Practice: Avoid Globals**

You run the danger of your code being overwritten by any other JavaScript added to the page after yours if you declare global variables.

## Revealing Module Pattern
When organizing javascript code in a large code base, in order to avoid polluting global namespace with global variables and for maintenance reasons it is required to separate the code into separate smaller units (*modules*) avoiding usage of global variables, because each module has:

-  locally scoped variables/functions (avoiding global namespace pollution)
-  while having access to other modules *public* variables/functions

		//forming a module pattern

		//step 1:
		//declare anonymous function
		function() {}; 
		
		//step 2:
		//declare anonymous function and call it
		(function() {})(); 
		
		//step 3: 
		//when calling the anonymous function, pass in dependancies as parameters
		(function(dep1, dep2, dep3) {
			//now you can have locally scoped variables...
			var localVariable;
			
			function locallyDeclaredFunction() {/* some code here...*/}
			
			//and code operating using the dependancies available...
			
		})(dep1, dep2, dep3); //pass some globally defined dependencies
		
		//step 4:
		//make public variables and/or functions available to other modules
		var myModule = (function(otherModule1, otherModule2, otherModule3) {
		    var localVariable;
			var publicVariable;
			
			function locallyDeclaredFunction() {/* some code here...*/}
			function publicFunction() {/* some code here...*/}
			
			//...code doing sth with dependancies available
			
			//return what you want to export as public
			return [
				publicVariable,
				publicFunction
			];
		})(otherModule1, otherModule2, otherModule3); //name your module for other modules to use it as dependency
	
		//some other module using 'myModule'
		(function(myModule) { //use myModule as dependancy
		    //... do sth with myModule
		})(myModule);

- Practical examples of usage of module pattern can be found in the following files:
	- [Module pattern usage example 1](../../05_dom_manipulation/exercise/task/native.js)
	- [Module pattern usage example 2](../tasks/assets/main.js)


### Quiz time solutions

- Solution for quiz 1: Console would log: 1 and then 2
- Solution for quiz 1: Console would log: 2

