$(function () {
	$(".alert-message .close").live("click", function (event) {
		$(this).closest(".alert-message").remove();
		event.preventDefault();
	});
});