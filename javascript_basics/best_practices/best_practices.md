## Best Practices ##

## HTML best practices ##

**Write Standards-Compliant Markup**

HTML, by nature, is a forgiving language that allows poor code to execute and render to varying levels of accuracy.
 
Successful rendering, however, does not mean that our code is semantically correct or guarantee that it will validate as standards compliant. 

In addition, poor code is unpredictable, and you can’t be certain what you’re going to get when it renders. 

We have to pay close attention when writing HTML and be sure to nest and close all elements correctly, to use IDs and classes appropriately, and to always validate our code.


Bad code:

	<p id="intro">New items on the menu today include <strong>caramel apple cider and breakfast crepes</p>.</strong> 
	
	<p id="intro">The caramel apple cider is delicious.

Good code:

	<p class="intro">New items on the menu today include <strong>caramel apple cider and breakfast crepes</strong>.</p> 
	
	<p class="intro">The caramel apple cider is delicious.</p>


**Make Use of Semantic Elements**

The library of elements in HTML is fairly large, with well over 100 elements available for use.

Deciding which elements to use to describe different content may be difficult, but these elements are the backbone of semantics. 

We need to research and double-check our code to ensure we are using the proper semantic elements. 

Users will thank us in the long run for building a more accessible website, and your HTML will arguably be easier to style. 

If you are ever unsure of your code, find a friend to help out and perform routine code reviews.

Bad code:

	<span class="heading">
		<strong>Welcome Back</span>
	</strong> 
	<br>
	<br>
	 	It has been a while. What have you been up to lately? 
	<br>
	<br>


Good code:

	<h1>Welcome Back</h1> 
	<p>It has been a while. What have you been up to lately?</p>


**Use the Proper Document Structure**

As previously mentioned, HTML is a forgiving language and, therefore, pages will render without the use of the <!DOCTYPE html> doctype or <html>, <head>, and <body>elements. 

Without a doctype and these structural elements, pages will not render properly in every browser.

We must always be sure to the use proper document structure, including the <!DOCTYPE html> doctype, and the <html>, <head>, and <body> elements.

Doing so keeps our pages standards compliant and fully semantic, and helps guarantee they will be rendered as we wish.

Bad code:

	<html>
		 <h1>Hello World</h1> 
		 <p>This is a web page.</p>
	</html>

Good code:

	<!DOCTYPE html> 
	<html>
		 <head> 
			<title>Hello World</title>
		 </head> 
		<body> 
			<h1>Hello World</h1> 
			<p>This is a web page.</p> 
		</body> 
	</html>


**Keep the Syntax Organized**

As pages grow, managing HTML can become quite a task.

Thankfully there are a few quick rules that can help us keep our syntax clean and organized. These include the following:

- Use lowercase letters within element names, attributes, and values
- Indent nested elements
- Strictly use double quotes, not single or completely omitted quotes
- Remove the forward slash at the end of self-closing elements
- Omit the values on Boolean attributes

Observing these rules will help keep our code neat and legible. Looking at the two sets of HTML here, the good code is easier to digest and understand.

Bad code:

	<aside>
	<h3>Chicago</h3>
	<h5 hidden="hidden">City in Illinois</h5>
	<img src="chicago.jpg" alt="Chicago is the third most populous city in the United States">	
	<ul>
	<li>234 square miles</li>
	<li>2.715 million residents</li>
	</ul>
	</aside>

Good code:

	<aside>
	    <h3>Chicago</h3>
	    <h5 hidden="hidden">City in Illinois</h5>
	    <img src="chicago.jpg" alt="Chicago is the third most populous city in the United States">
	
	    <ul>
	        <li>234 square miles</li>
	        <li>2.715 million residents</li>
	    </ul>
	</aside>


## CSS best practices ##

**Organize Code with Comments**

CSS files can become quite extensive, spanning hundreds of lines. 

These large files can make finding and editing our styles nearly impossible. Let’s keep our styles organized in logical groups. 

Then, before each group, let’s provide a comment noting what the following styles pertain to.

Bad code:

	header { ... } 
	article { ... } 
	.btn { ... }


Good code:

	/* Primary header */ 
	header { ... } 
	
	/* Featured article */
	article { ... } 
	
	/* Buttons */ 
	.btn { ... }


**Write CSS Using Multiple Lines & Spaces**

When writing CSS, it is important to place each selector and declaration on a new line. Then, within each selector we’ll want to indent our declarations.

After a selector and before the first declaration comes the opening curly bracket, `{`, which should have a space before it. Within a declaration, we need to put a space after the colon, `:`, that follows a property and end each declaration with a semicolon, `;`.

Doing so makes the code easy to read as well as edit. When all of the code is piled into a single line without spaces, it’s hard to scan and to make changes.

Bad code:

	a,.btn{background:#aaa;color:#f60;font-size:18px;padding:6px;}

Good code:

	a, .btn { 
	   background: #aaa; 
	   color: #f60; 
	   font-size: 18px; 
	   padding: 6px; 
	 }


**Modularize Styles for Reuse**

CSS is built to allow styles to be reused, specifically with the use of classes. 

For this reason, styles assigned to a class should be modular and available to share across elements as necessary.
If a section of news is presented within a box that includes a border, background color, and other styles, the class of news might seem like a good option. 

However, those same styles may also need to be applied to a section of upcoming events. The class of news doesn’t fit in this case. 

A class of feat-box would make more sense and may be widely used across the entire website.

Bad code:

	.news {
	    background: #eee; 
	    border: 1px solid #ccc;
	    border-radius: 6px; 
	} 
	
	.events { 
	     background: #eee; 
	     border: 1px solid #ccc; 
	     border-radius: 6px; 
	}


Good code:

	.feat-box {
		background: #eee; 
		border: 1px solid #ccc; 
		border-radius: 6px; 
	}


## JavaScript Best Practices ##

**Initialize Variables**

It is a good coding practice to initialize variables when you declare them.

This will:

- Give cleaner code
- Provide a single place to initialize variables
- Avoid undefined values

.

	// Declare and initiate at the beginning
    var firstName = "";
    var lastName = "";
    var price = 0;


**Never Declare Number, String, or Boolean Objects**

Always treat numbers, strings, or booleans as primitive values. Not as objects.

Declaring these types as objects, slows down execution speed, and produces unwanted side effects:

    var x = "John"; 
    var y = new String("John");
    (x === y) // is false because x is a string and y is an object.


**Don't Use new Object()**

    var x1 = {};   // new object
    var x2 = "";   // new primitive string
    var x3 = 0;		// new primitive number
    var x4 = false; // new primitive boolean
    var x5 = [];    // new array object
    var	x6 = /()/;  // new regexp object
    var x7 = function(){}; // new function object


**Beware of Automatic Type Conversions**

    var x = 5 + 7;   // x.valueOf() is 12,  typeof x is a number
    var x = 5 + "7"; // x.valueOf() is 57,  typeof x is a string
    var x = "5" + 7; // x.valueOf() is 57,  typeof x is a string
    var x = 5 - 7;   // x.valueOf() is -2,  typeof x is a number
    var x = 5 - "7"; // x.valueOf() is -2,  typeof x is a number
    var x = "5" - 7; // x.valueOf() is -2,  typeof x is a number
    var x = 5 - "x"; // x.valueOf() is NaN, typeof x is a number

**Use === Comparison**

    0 == "";    // true
    1 == "1";   // true
    1 == true;  // true
    
    0 === "";   // false
    1 === "1";  // false
    1 === true; // false


**Avoid Using eval()**

The eval() function is used to run text as code. In almost all cases, it should not be necessary to use it.

Because it allows arbitrary code to be run, it also represents a security problem.


**Avoid Globals**

You run the danger of your code being overwritten by any other JavaScript added to the page after yours.

Revealing Module Pattern: Keep consistent syntax and mix and match what to make global:

    module = function(){
       var current = null;
       var labels = {
	      'home':'home',
	      'articles':'articles',
	      'contact':'contact'
       };

		var init = function(){
		  //some code
       };

		var show = function(){
      		current = 1;
       };

       var hide = function(){
      		show();
       }

       return { init:init, show:show, current:current }
    }();

    module.init();