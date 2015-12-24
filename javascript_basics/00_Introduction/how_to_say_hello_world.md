# How to run a simple Hello World #

In order to run JavaScript you need to do a few steps:

1. Create a "UI", with HTML that defines structure and CSS that define styles
2. Create some JavaScript, either in-line or by referencing a script file

## Simple Hello world page with inline styles and scripts ##
Look at this code:

	<!DOCTYPE html>
	
	<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="utf-8" />
	    <title>Hello World Page</title>
	
	    <style>
	        div {
	            background-color: black;
	            color: green;
	        }
	    </style>
	</head>
	
	<body>
	    <div>
	        <script>document.write("Hello World !");</script>
	    </div>    
	</body>
	</html>


There are a few things you can observe here.

- The XML structure of the file itself is actually HTML. The name of the elements is strictly defined by the HTML specification.
- Everything inside the `<style></style>` element is CSS. You can have multiple `<style>` elements.
- Everything inside the `<script></script>` element is JavaScript. You can have multiple `<script>` elements.

## Simple Hello world page with referenced styles and scripts ##

Usually you would not include styles and scripts in-line in the HTML itself, but would reference them from external files. In order to achieve this you need a few steps.

1. Create a `scripts.js` file with following content:

		document.getElementsByTagName("div")[0].textContent = "Hello World!";

2. Create a `styles.css` file with following content:

		div {
	        background-color: black;
	        color: green;
	    }

3. Create a `hello_world.html` file with following content:

		<!DOCTYPE html>
		
		<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
		<head>
		    <meta charset="utf-8" />
		    <title>Hello World Page</title>
		
		    <link rel="stylesheet" type="text/css" href="styles.css">
		</head>
		
		<body>
		    <div></div>
		
		    <script src="script.js"></script>
		</body>
		</html>

There are two new things you can observe here.

- The styles are no longer inside a `<style>` element, but are referenced from the `styles.css` file using this line of code:

		<link rel="stylesheet" type="text/css" href="styles.css">

- The scripts are not inside the `<script>` element, but are referenced from the `script.js` file using following line of code:

		<script src="script.js"></script>

You can find a working example of both in-line and referenced files in the **hello_world** folder. Have fun!