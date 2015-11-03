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

