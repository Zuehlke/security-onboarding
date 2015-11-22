var shoppingCartApp = function() {
	var _products;
	var app = {};
	
	app.init = function(products) {
		_products = products;		
		populateProductsTable();
	};
	
	app.showCart = function() {
		if (getCartTable().find('button[data-product-id]').length > 0) {
			getCartElement().show();
		}
		else {
			alert('There are no items in the cart.');
		}
	};
	
	app.hideCart = function() {
		getCartElement().hide();
	};
	
	app.addProductToCart = function(button) {
		var productId = parseInt($(button).attr('data-product-id'));
		var product = getProductById(productId);
	
		var table = getCartTable();
	
		// check if product has already been added
		var existingButton = table.find('button[data-product-id="' + productId + '"]');
		
		if (existingButton.length > 0) {
			var amountCell = existingButton.parent().prev();
			amountCell.html(parseInt(amountCell.html()) + 1);
			return;
		}
		
		// add a new row
		var newRow = getNewCartRow();
		newRow.children()
			.first().html(product.Name)
			.next().html(1)
			.next().children()
			.first().attr('data-product-id', product.Id);
		
		table.append(newRow);
	};
	
	app.removeProductFromCart = function(button) {
		var row = button.closest('tr');
		row.remove();
	};
	
	function populateProductsTable() {
		var template = getNewProductRow();
	
		_products.forEach(function(product) {
			createProductRow(product, template);
		});
	}
	
	function getProductsTable() {
		return $('#product-table');
	}
	
	function getNewProductRow() {
		return $($('#product-row-template').html());
	}
	
	function createProductRow(product, template) {
		var productRow = template.clone();
		
		productRow.children()
			.first().html(product.Name)
			.next().find('button')
			.attr('data-product-id', product.Id);
		
		getProductsTable().append(productRow);
	}
	
	function getCartElement() {
		return $('.shopping-cart');
	}
	
	function getCartTable() {
		return $('#shopping-cart-table');
	}
	
	function getNewCartRow() {
		return $($('#shopping-cart-item-template').html());
	}
	
	function getProductById(id) {
		return _products.filter(function(product) {
			return product.Id === id; 
		})[0];	
	}
	
	return app;
}();