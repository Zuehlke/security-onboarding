# DOM manipulation #

## What is DOM? ##

DOM (document object model) is an interface that provides a representation of an HTML / XML document as a structured group of nodes and objects. 

    <!DOCTYPE HTML>
	<html>
	<head>
		<title>Document</title>
	</head>
	<body>
		<h3>What is Lorem ipsum?</h3>
		<p>
			Lorem Ipsum is <span>simply dummy text</span> of the printing and typesetting industry. 
			Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...
		</p>
		<h3>Where does it come from?</h3>
		<p>
			Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a 
			piece of classical Latin literature from 45 BC, making it over 2000 years old.
		</p>
	</body>
	</html>


All web browsers provide their own implementation of the DOM. As the browser parses the HTML for displaying it also generates the model.

![DOM tree](/dom_tree.png)

Image above is an example of a DOM tree composed of nodes.

## DOM API ##

Javascript code executed in the browser can manipulate the object representation through the DOM interface. To access the DOM we use the <code>document</code> property of the <code>window</code> global object.

	//directly accessing the property of the window object   
	document;

	//accessing through the self-referencing window property
	window.document;

Global object provides top-level context to the executing code. Before passing control to your custom javascript code every browser creates the <code>window</code> object. Object properties are global and accessible from everywhere if not hidden by a variable of the same name.

	<!DOCTYPE HTML>
	<html>
		<head>
			<title>Document</title>
		</head>
		<body>
			<h3>What is Lorem ipsum?</h3>
			<p>
				Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been...
			</p>
			<script>
				document.writeln('<h3>Where does it come from?</h3>');
				document.writeln('<p>');
				document.writeln('Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical...');
				document.writeln('</p>');
			</script>
		</body>
	</html>

The <code>document</code> object enables us to modify the content to the document dynamically. The example above shows how to directly write content into a document. 

Common use case of the <code>document</code> object is locating and modifying a node inside the DOM tree.

	<!DOCTYPE HTML>
	<html>
		<head>
			<title>Document</title>
		</head>
		<body>
			<h3>What is Lorem ipsum?</h3>
			<p id="paragraph"></p>
			<script>
				// get the html element that we want to modify
				var paragraph = document.getElementById('paragraph');
				var text = 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been...';
				
				// add a text child node to the paragraph
				paragraph.appendChild(document.createTextNode(text));
				
				// this will also do the trick
				//paragraph.innerHTML = text;
			</script>
		</body>
	</html>

Look-up can be performed based on different attributes of HTML elements.

	// get element with id 'container'
	document.getElementById('container');

	// get all <p> elements
	document.getElementsByTagName('p');

	// get all elements with class 'box'
	document.getElementsByClassName('box');

	// get all elements with name 'Author'
	document.getElementsByName('Author');

	// use css selectors for advanced look-up queries
	// find first element with class 'box' that is descendent of the element with id 'container'
	document.querySelector('#container .box');

	// get all elements that match the selector 
	document.querySelectorAll('#container .box');

	// getElementsByTagName, getElementsByClassName, querySelector and querySelectorAll work also
	// on HTML elements
	var container = document.getElementById('container');
	var boxes = container.querySelectorAll('.box'); 

The same can be done in jQuery: 

    //get element with the id 'lastname'
    $("#lastname");
    
    //get elements with class 'intro'
    $(".intro");
    
    //select ll elements with the class "intro" or "demo"
    $(".intro,.demo");
    
    //select all <p> elements that are a direct child of a 'demo' element
    $(".demo > p")

    //set the text to 'Hi!' on elements with class intro
    $(".intro").text("Hi!");

    //add 'myClass' to the element with id 'lastname'
    $("#lastname").addClass("myClass");


Almost every HTML element in DOM inherits the <code>HTMLElement</code> interface which provides methods for extracting and manipulating the element attributes. 

	var element = document.getElementById('container');

	// get all attributes
	var attributes = element.attributes;
 	
	// get attribute with name 'data-location'
	element.getAttribute('data-location');

	// check if the attribute exists
	element.hasAttribute('data-location');

	// set attribute
	element.setAttribute('data-location', 'Susesi');

	// remove attribute
	element.removeAttribute('data-location');

	// get element class name(s)
	element.className;

## Event bubbling and capturing ##

**Event bubbling** states that after an event triggers on the deepest possible element, it then triggers on parents in nesting order.

    <div class="d1">1  <!-- the topmost -->
	    <div class="d2">2
		    <div class="d3">3 <!-- the innermost -->
		    </div>
	    </div>
    </div>

The bubbling guarantees that click on Div 3 will trigger onclick first on the innermost element 3 (also caled the target), then on the element 2, and the last will be element 1.

![Event Bubbling](/event-order-bubbling.gif)

The deepest element which triggered the event is called the target or, the originating element.

A handler may decide that event is fully processed and stop the bubbling.


1. For W3C-compliant browsers: **event.stopPropagation()**
2. For IE<9: **event.cancelBubble = true**


In all browsers, except IE<9, there are two stages of event processing.

**The event first goes down - that’s called capturing, and then bubbles up**. This behavior is standartized in W3C specification.

**All methods of event handling ignore the capturing phase**. 

Using **addEventListener with last argument true is only the way to catch the event at capturing**.

![Event Bubbling Order](/event-order-w3c.gif)

According to this model, the event:

1. Captures down - through 1 -> 2 -> 3.
2. Bubbles up - through 3 -> 2 -> 1.

The addEventListener() method attaches an event handler to the specified element.

    //execute displayDate() on the click event
    document.getElementById("myBtn").addEventListener("click", function() {
		console.log( "click" );
    });

The same in jQuery:

    $("#myBtn").on( "click", function() {
    	console.log( "click" );
    });
    
## Objects ##

The standard way to create an "object type" is to use an object constructor function:

    function person(first, last, age, eye) {
	    this.firstName = first;
	    this.lastName = last;
	    this.age = age;
	    this.eyeColor = eye;
    }
    var myFather = new person("John", "Doe", 50, "blue");

The above function (person) is an object constructor.

Each object has an internal link to another object called its prototype. That prototype object has a prototype of its own, and so on until an object is reached with null as its prototype. null, by definition, has no prototype, and acts as the final link in this prototype chain.

When trying to access a property of an object, the property will not only be sought on the object but on the prototype of the object, the prototype of the prototype, and so on until either a property with a matching name is found or the end of the prototype chain is reached.

![Prototypical inheritance](/prototype-inheritance.png)

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

When an inherited function is executed, the value of this points to the inheriting object, not to the prototype object where the function is an own property.

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

## AJAX ##

AJAX stands for Asynchronous JavaScript and XML. In a nutshell, it is the use of the XMLHttpRequest object to communicate with server-side scripts. 

It can send as well as receive information in a variety of formats, including JSON, XML, HTML, and even text files. 

AJAX’s most appealing characteristic, however, is its "asynchronous" nature, which means it can do all of this without having to refresh the page. This lets you update portions of a page based upon user events.

    var xhttp = new XMLHttpRequest();

	    xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("demo").innerHTML = xhttp.responseText;
		    }
	    }

    xhttp.open("GET", "ajax_info.txt", true);
    xhttp.send();

The open() parameters are:

1. method: the type of request: GET or POST
2. url
3. async: true (asynchronous) or false (synchronous)

jQuery example of AJAX:

    $.ajax({
	    url: 'getTwitterFollowers.php',
	    type: 'GET',
	    data: 'twitterUsername=jquery4u',
	    success: function(data) {
	    	//do something
	    },
	    error: function(e) {
	    	//do something else
	    }
    });

## Debugging ##

All major browsers have built in debugging tools.

In the debugger window, you can set breakpoints in the JavaScript code. At each breakpoint, JavaScript will stop executing, and let you examine JavaScript values.

Alternatively you can use the **debugger** keyword. The debugger keyword stops the execution of JavaScript, and calls (if available) the debugging function. This has the same function as setting a breakpoint in the debugger.