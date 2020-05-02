$(document).ready(function() {
	$("#phoneButton").click(function() {
		const dni = $("#dni").val();
		const phone = $("#phone").val();

		$.ajax({
			type: 'PATCH',
			url: `api/contact/${dni}/phone/${phone}`,
			success: function() {
				window.location.href = "index.html";
			}
		 });
	});
});