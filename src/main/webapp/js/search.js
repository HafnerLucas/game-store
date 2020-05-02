$(document).ready(function() {
	$.getJSON("api/product", function(products) {
		for(let i = 0; i < products.length; i++) {
			const product = products[i];

			const newRow = `
				<tr>
					<td>${product.id}</td>
					<td>${product.description}</td>
					<td>${product.price}</td>
					<td>${product.quantity}</td>
					<td>${product.category}</td>
					<td>${product.platform}</td>
				</tr>
			`;

			$('#result > tbody:last-child').append(newRow);
		}
	});
});