## Objects ##

There are different ways to create new objects:

    //Using an Object Literal
    var person = {
		firstName:"John",
		lastName:"Doe",
		age:50,
		eyeColor:"blue"
	};


	//Using the JavaScript Keyword new
    var person = new Object();
    person.firstName = "John";
    person.lastName = "Doe";


    //Using an Object Constructor
    function person(first, last, age, eye) {
	    this.firstName = first;
	    this.lastName = last;
	    this.age = age;
	    this.eyeColor = eye;
    }
    var myFather = new person("John", "Doe", 50, "blue");


    //Using the Object.create method
    var animal1 = Object.create(Animal);


Each object has an internal link to another object called its prototype. That prototype object has a prototype of its own, and so on until an object is reached with null as its prototype. null, by definition, has no prototype, and acts as the final link in this prototype chain.

When trying to access a property of an object, the property will not only be sought on the object but on the prototype of the object, the prototype of the prototype, and so on until either a property with a matching name is found or the end of the prototype chain is reached.

![Prototypical inheritance](/prototype-inheritance.png)

When an inherited function is executed, the value of this points to the inheriting object, not to the prototype object where the function is an own property.

    var o = {
      a: 2,
      m: function(b){
    	return this.a + 1;
      }
    };
    
    // p is an object that inherits from o
    var p = Object.create(o);   

	console.log(p.a); //2
	
    p.a = 12;
    console.log(p.m()); // 13


JavaScript objects are mutable: They are addressed by reference, not by value.

    var person = {firstName:"John", lastName:"Doe", age:50}
    
    var x = person;
    x.age = 10; 
	console.log(person.age); //10

## Namespacing ##

JavaScript is designed in such a way that it is very easy to create global variables that can cause collisions with other objects or variables in the global namespace.  

The practice of **namespacing** is usually to create an object literal encapsulating your own functions and variables, so as not to collide with those created by other libraries.

**Object Literal Notation**

    var myApplication = {
      var1: someval,
      var2: someval,
      myFunc: function() {
    	// do stuff
      }
    };
	
	//call myFunc() function
	myApplication.myFunc();

## This keyword in context of calling object methods

## Explicitly setting this when calling functions

function text(setOnThis) {
    this.someProperty = setOnThis;
}

var myObject = {};

text.apply(myObject, ["someValueToSet"]);
text.call(myObject, "someValueToSet");


var boundFunction = text.bind(myObject);

boundFunction("setsThisValueToMyObject");


//TODO: Have it in functions and best-practices already, should be moved to best practices?
**Module pattern**

The logic is shielded from the global scope by a function wrapper (usually self-invoking) which returns an object representing the module’s public interface. 

By immediately invoking the function and assigning the result to a namespace variable, we lock up the module’s API in the namespace. 

Additionally any variables not included in the return value will remain forever private, visible only to the public functions that reference them.

    var myApp = (function() {
	 
	    var id= 0;
	 
	    return {
	        next: function() {
	            return id++;    
	        },
	 
	        reset: function() {
	            id = 0;     
	        }
	    };  
	})(); 


## Exercise ##

Define a **repeatify** function on the String object. The function accepts an integer that specifies how many times the string has to be repeated. The function returns the string repeated the number of times specified.

Example:

    //Should print 'hellohellohello'
    console.log('hello'.repeatify(3));
