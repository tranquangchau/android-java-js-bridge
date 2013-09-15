var api = {
	init: function() {
		$('#call-java a').on('click', function() {
			APP.log(new Date().toTimeString());
			
			return false;
		});
	},
	
	log: function(text) {
		$('#log').append('<p>Received ' + text + '</p>');
	}
}

$(document).ready(function() {
	api.init();
});
