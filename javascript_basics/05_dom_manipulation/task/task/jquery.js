var shoppingCartApp = function() {
	var _products;
	var app = {};
	
	// populate the product table during initialization. Generate rows using the provided template in the html file.
	app.init = function(products) {
		_products = products;
	};
	
	// Display the shopping cart popup if there are items in the cart, otherwise show the message.
	app.showCart = function() {
	};
	
	// Close the shopping cart.
	app.hideCart = function() {
	};
	
	// Add the product to the shopping cart table. Use the provided template in the html file.
	app.addProductToCart = function(button) {
	};
	
	// Remove the product from the shopping cart table.
	app.removeProductFromCart = function(button) {
	};
	
	return app;
}();