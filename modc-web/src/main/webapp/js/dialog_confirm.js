jQuery(function ($) {
	$('a.confirm').click(function (e) {
		e.preventDefault();
		var actionUrl = $(this).attr('href');
		//get which dialog message is:
		var msg = $(this).parent('div').find('.msg_content').html();
		//get which dialog div is:
		var dialog_div = $(this).parent('div').find('#confirm_dialog');
		//pass the dialog div object and confirm message
		// calling the confirm function
		// use a callback function to perform the "yes" action
		confirm(dialog_div, msg, function () {
			//alert(actionUrl);
			window.location.href =  actionUrl;
		});
	});
});

function confirm(dialog_div, message, callback) {
	dialog_div.modal({
		closeHTML: "<a href='#' title='Close' class='modal-close'><img src='../images/close.png' alt='Close window'/></a>",
		position: ["20%",],
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
			$('.message', dialog.data[0]).append(message);
			// if the user clicks "yes"
			$('.yes', dialog.data[0]).click(function () {
				// call the callback
				if ($.isFunction(callback)) {
					callback.apply();
				}
				// close the dialog
				$.modal.close();
			});
		}
	});
}