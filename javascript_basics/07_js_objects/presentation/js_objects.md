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

Another way to avoid global variables would be using the module pattern. (See the best practices presentation)


## This keyword in functions ##

Inside a function, the value of this depends on how the function is called.

In this case, the value of this is not set by the call. Since the code is not in strict mode, the value of this must always be an object so it defaults to the global object.

    function f1(){
      return this;
    }
    
    f1() === window; // global object


In strict mode, the value of this remains at whatever it's set to when entering the execution context. If it's not defined, it remains undefined. It can also be set to any value, such as null or 42 or "I am not this".

    (function(){
	    "use strict"
	    function f2(){
	     	return this;
	    }
	    
	    console.log(f2() === undefined);
    })();


When a function is called as a method of an object, its this is set to the object the method is called on.

In the following example, when o.f() is invoked, inside the function this is bound to the o object.

    var o = {
      prop: 37,
      f: function() {
    	return this.prop;
      }
    };
    
    console.log(o.f()); // logs 37

Note that this behavior is not at all affected by how or where the function was defined. 	

    var o = {prop: 37};
    
    function independent() {
      return this.prop;
    }
    
    o.f = independent;
    
    console.log(o.f()); // logs 37

## Explicitly setting this when calling functions ##

Where a function uses the this keyword in its body, its value can be bound to a particular object in the call using the call or apply methods that all functions inherit from Function.prototype.

    function add(c, d){
      return this.a + this.b + c + d;
    }
    
    var o = {a:1, b:3};
    
With **.call()** the first parameter is the object to use as 'this', subsequent parameters are passed as arguments in the function call.

    add.call(o, 5, 7); // 1 + 3 + 5 + 7 = 16
    

With **.apply()** the first parameter is the object to use as 'this', the second is an array whose members are used as the arguments in the function call.

    add.apply(o, [10, 20]); // 1 + 3 + 10 + 20 = 34


The **.bind()** method creates a new function that, when called, has its this keyword set to the provided value, with a 
given sequence of arguments preceding any provided when the new function is called.

    function add(c, d){
      	return this.a + this.b + c + d;
    }
    
    var o = {a:1, b:3};
    
    var boundFunction = add.bind(o, 5, 7); 
    boundFunction();

## Exercise ##

Define a **repeatify** function on the String object. The function accepts an integer that specifies how many times the string has to be repeated. The function returns the string repeated the number of times specified.

Example:

    //Should print 'hellohellohello'
    console.log('hello'.repeatify(3));
