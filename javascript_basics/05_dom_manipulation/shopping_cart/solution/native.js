var shoppingCartApp = function() {
	var _products;
	var app = {};
	
	app.init = function(products) {
		_products = products;		
		populateProductsTable();
	};
	
	app.showCart = function() {
		var table = getCartTable();
		var anyProductRow = table.querySelector('button[data-product-id]');
		if (anyProductRow) {
			getCartElement().style.display = 'block';
		}
		else {
			alert('There are no items in the cart.');
		}
	};
	
	app.hideCart = function() {
		getCartElement().style.display = 'none';
	};
	
	app.addProductToCart = function(button) {
		var productId = parseInt(button.getAttribute('data-product-id'));
		var product = getProductById(productId);
	
		var table = getCartTable();
		
		// check if product has already been added
		var existingButton = table.querySelector('button[data-product-id="' + productId + '"]');
		
		if (existingButton) {
			var amountCell = existingButton.parentNode.previousElementSibling;
			amountCell.innerHTML = parseInt(amountCell.innerHTML) + 1;
			return;
		}
		
		// add a new row
		var newRow = getNewCartRow();
		var nameCell = newRow.firstElementChild;
		nameCell.innerHTML = product.Name;
		var amountCell = nameCell.nextElementSibling;
		amountCell.innerHTML = 1;
		var removeButton = amountCell.nextElementSibling.firstElementChild;
		removeButton.setAttribute('data-product-id', product.Id);
		
		table.appendChild(newRow);
	};
	
	app.removeProductFromCart = function(button) {
		var row = button.parentNode.parentNode;
		row.parentNode.removeChild(row);
	};
	
	function populateProductsTable() {
		var template = getNewProductRow();
	
		_products.forEach(function(product) {
			createProductRow(product, template);
		});
	}
	
	function getProductsTable() {
		return document.getElementById('product-table');
	}
	
	function getNewProductRow() {
		var container = document.getElementById('product-row-template');

		// we need to use a container that is allowed to have <tr> elements as children
		var temp = document.createElement('tbody');
		temp.innerHTML = container.innerHTML;
		return temp.firstElementChild;
	}
	
	function createProductRow(product, template) {
		var productRow = template.cloneNode(true);
		
		var nameCell = productRow.firstElementChild;
		nameCell.innerHTML = product.Name;
		
		var addButton = nameCell.nextElementSibling.getElementsByTagName('button')[0];
		addButton.setAttribute('data-product-id', product.Id);
		
		getProductsTable().appendChild(productRow);
	}
	
	function getCartElement() {
		return document.getElementsByClassName('shopping-cart')[0];
	}
	
	function getCartTable() {
		return document.getElementById('shopping-cart-table');
	}
	
	function getNewCartRow() {
		var container = document.getElementById('shopping-cart-item-template');
		var temp = document.createElement('tbody');
		temp.innerHTML = container.innerHTML;
		return temp.firstElementChild;
	}
	
	function getProductById(id) {
		return _products.filter(function(product) {
			return product.Id === id; 
		})[0];	
	}
	
	return app;
}();