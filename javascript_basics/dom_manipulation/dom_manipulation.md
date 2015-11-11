# DOM manipulation #

## Window object ##

<b>Window</b> object is the main entry point for Javascript API of a browser. The object represents the browser window or a frame.

Global object provides top-level context to the executing code. Before passing control to your custom Javascript code every browser creates the <b>Window</b> object. Properties of the object are global and accessible from everywhere if not hidden by a variable of the same name.

<b>Window</b> object provides a set of properties and methods that allow you to perform different tasks where only some of them are related to the browser window.

### Timers ###

<b>Window</b> object allows you to register functions that can be invoked at defined time intervals or once.

	<!DOCTYPE HTML>
	<html>
		<head>
			<title>Document</title>
		</head>
		<body>
			<script>
				// this will trigger the specified function every 1000 ms
				// variable 'handler' holds the value that can be used to cancel the invocation
				var handler = setInterval(function() {
					document.body.innerHTML = '<h1>Current time is: ' + new Date().toTimeString() + '</h1>';
				},
				1000);
				
				// this will be triggered only once after 10 s
				setTimeout(function() {
					// stop the execution of the registered function
					clearInterval(handler);
				},
				10000);
			</script>
		</body>
	</html>

### Location and history ###

The <code>location</code> property of the <b>Window</b> object gives you information about the URL of the currently loaded document.

	// accessing the instance of the Location object. 
	// the object contains properties that represent individual components of the current
	// window URL: protocol, host, hostname, port, pathname, search, hash 
	location;

	// read the complete URL text
	location.href;
	
	// go to google.com and leave the current document in the browsing history
	location.assign('http://google.com');

	// go to google.com and remove the current document from the browsing history
	location.replace('http://google.com');

	// just assign the new URL directly to the location property (same as calling assign)
	location = 'http://google.com';

	// you can navigate using relative URLs
	location = 'shop/index';

	// scroll to the element with id 'marker' on the current page
	location = '#marker';

To access the browsing history of a window use the <code>history</code> property. <b>History</b> object contains a list of documents and document states.

	// go back one document (same as clicking on Back button in the browser)
	history.back();

	// go forward one document (same as clicking on Forward button in the browser)
	history.forward();

	// go forward 2 documents (same as clicking on Forward button twice)
	// negative values will navigate backwards
	history.go(2);

### Dialog boxes ###

<b>Window</b> object provides three methods for displaying simple dialog boxes. These three methods are blocking which means that any code that comes after the invocation will not be executed until the dialog is closed.

	// opens a dialog that prompts the user to enter a string value and remembers
	// the value in the variable 'text'
	var text = prompt('Please enter a value...');

	// opens a dialog and returns a boolean value based on user selection (OK, Cancel)
	var ok = confirm('Do you wish to proceed?');

	// displays a message to the user	
	alert('This is a message!');

Good web design dictates that these methods are used very rarely, if at all, and should be used either for debugging purposes or in the early stage of development. Custom modal windows are used in every modern web application.

### Multiple windows ###

Your Javascript code can also create new windows and interact with them. For every new window a corresponding <b>Window</b> object is created and is accessible by the code that created it.

	// creates a new blank window
	// second parameter (ommited in this invocation) defaults to '_blank' which means that
	// a new unnamed window is created
	var w = window.open('about:blank');

	// call a method on the created window object
	w.alert('Hello');
	
	// access the opener of the window (current window)
	w.opener;

	// close the opened window
	w.close();

Windows can contain nested windows which are represented through <code>iframe</code> elements. Each nested window has a reference to it's own <b>Window</b> object.

	<!DOCTYPE HTML>
	<html>
		<head>
			<title>Document</title>
		</head>
		<body>
			<h1>This is the parent</h1>
			<iframe id="iframe-1" src="about:blank"></iframe>
			<script>
				// get the window of the iframe
				var iframeWindow = document.getElementById('iframe-1').contentWindow;
				
				iframeWindow.alert('Alert from iframe window');
				
				// call a method from the parent window
				iframeWindow.parent.alert('Alert from the parent window');
			</script>
		</body>
	</html>

## What is DOM? ##

DOM (document object model) is an interface and a representation of an HTML / XML document as a structured group of nodes and objects. 

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

Image above is an example of a DOM tree. The tree is composed of nodes and all of them inherit from the <b>Node</b> type. Partial DOM class hierarchy is shown below.

![DOM class hirarchy](/dom_class_hierarchy.png)

Root of the DOM tree that we saw above is an instance of <b>HTMLDocument</b> type. All html elements are represented by a corresponding subtype of the <b>HTMLElement</b> type. Instances of the <b>Text</b> type are located at the leaf level of the tree.

## DOM API ##

To access the DOM we use the <code>document</code> property of the <b>Window</b> global object.

	//directly accessing the property of the window object   
	document;

	//accessing through the self-referencing window property
	window.document;

### Finding elements ###
 
Look-up of HTML elements can be performed based on different attributes.

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

### DOM traversal ###

<b>Node</b> interface provides properties that allow you to locate structurally related parts of the document (parents, siblings, children). 

	var element = document.getElementById('container');

	// get parent node
	element.parentNode;

	// get all child nodes
	element.childNodes;

	// first, last child node
	element.firstChild;
	element.lastChild;

	// next, previous node sibling
	element.nextSibling;
	element.previousSibling;

	// to avoid Text and Comment nodes we usually want to iterate through Element types
	
	// get all child elements
	element.children;

	// first, last child element
	element.firstElementChild;
	element.lastElementChild;

	// next, previous element sibling
	element.nextElementSibling;
	element.previousElementSibling;

### Manipulating element attributes ###

<b>Element</b> interface which provides methods and properties for extracting and manipulating element attributes.

	var element = document.getElementById('container');

	// get all attributes
	var attributes = element.attributes;
 	
	// standard html attributes can be accessed as properties of an element, some of them
	// are exclusive to specific element types
	element.id;
	element.name;	

	var form = document.getElementsByTagName('form')[0];
	form.action = 'http://example.com';
	form.method = 'POST';

	// for custom attributes we can use the get/setAttribute methods. These methods can 
	// also be used to modify standard attributes

	// get attribute with name 'data-location'
	element.getAttribute('data-location');

	// check if the attribute exists
	element.hasAttribute('data-location');

	// set attribute
	element.setAttribute('data-location', 'Susesi');

	// remove attribute
	element.removeAttribute('data-location');

### Manipulating document content ###

Javascript code executed in the browser can manipulate the document content through the DOM interface.

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
							
				// create a new paragraph
				var newParagraph = document.createElement('p');
				
				// another way of specifying element content
				newParagraph.innerHTML = 'This is a new paragraph';
	
				alert('About to add the new paragraph');
				var parent = paragraph.parentNode;
				parent.insertBefore(newParagraph, paragraph);
	
				alert('About to remove the old paragraph');
				parent.removeChild(paragraph);
				
				alert('About to replace the new paragraph');
				parent.replaceChild(document.createTextNode('Replaced.'), newParagraph); 
			</script>
		</body>
	</html>

### CSS classes and style ###

Inline style of HTML elements can be accessed using the <code>style</code> property.

	var element = document.getElementById('container');

	// change the background color
	element.style.backgroundColor = '#00ff00';

	// change font size. CSS syntax contains a hyphen (font-size). 
	// This is translated to lower camelcase in Javascript API.
	element.style.fontSize = '24px';

CSS classes of an element can be accessed / modified using the <code>className</code> and <code>classList</code> properties.

	// set the value of the class attribute. This overrides any currently applied classes.
	element.className = 'active current';

	// add to / remove from class list
	element.classList.add('box');
	element.classList.remove('box');

## TODO: jQuery ##

## TODO: Task ##

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