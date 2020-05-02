$(document).ready(function() {
	$.getJSON("api/contact", function(contacts) {
		for(let i = 0; i < contacts.length; i++) {
			const contact = contacts[i];

			const newRow = `
				<tr>
					<td>${contact.id}</td>
					<td>${contact.name}</td>
					<td>${contact.price}</td>
					<td>${contact.quantity}</td>
					<td>${contact.category}</td>
					<td>${contact.platform}</td>
				</tr>
			`;

			$('#result > tbody:last-child').append(newRow);
		}
	});
});