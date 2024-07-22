import $ from 'jquery';
import 'bootstrap/dist/css/bootstrap.css';

$(document).ready(function() {

    $(document).on('keypress', function(e) {
        if (e.which === 13) {
            // send the code to the backend
            // ensure the session is then set somehow and linked to this code
            var loginCode = $("#login-code").val();

            // go and verify login code...
            //$(location).attr('href', '/profile');

            $.ajax({
                url: '/authenticate',
                type: 'POST',
                data : JSON.stringify({userCode : loginCode}),
                contentType: 'application/json',
                success: function(response){
                    console.log("<< ", response);

                    // reloadTable(response);
                    // modal.close();
                },
                error: function(error){
                    console.log(error);
                    alert(JSON.parse(error));
                }
            });
        }
    });

});

