# Javascript functions

## Function basics
###Declaring and using a function

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


###Anonymous functions (function literals)
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

## Module pattern

	function() {}; //declare anonymous function
	
	(function() {})(); //declare anonymous function and call it
	
	(function(dep1, dep2, dep3) {
		var localVariable;
		
		function locallyDeclaredFunction() {
		  ...
		}
		
		...code operating using the dependancies available
	})(dep1, dep2, dep3); //pass some globally defined dependencies
	
	var myModule = (function(otherModule1, otherModule2, otherModule3) {
	   var module = {};
	   var localVariable;
	   module.exportedVar = 1;
		
		function locallyDeclaredFunction() {
		  ...
		}
		
		...code operating using the dependancies available
		
		return module;
	})(otherModule1, otherModule2, otherModule3); //name your module for other modules to use it as dependency

	(function(myModule) { //use myModule as dependancy
	    ... do sth with myModule
	})(myModule);
