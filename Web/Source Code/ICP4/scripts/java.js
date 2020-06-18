function Enter(){
    //This function is to get the username that was entered and call the function getInfo to get the information
    username = document.getElementById("username").value;
    //console.log(username);
    getInfo(username);
}


function getInfo(user) {
    // create a new XMLHttpRequest class and send a GET request using it. 
    var xhttp = new XMLHttpRequest();
    // application/json is to allow us access the json's information.
    xhttp.overrideMimeType("application/json");
    xhttp.open('GET', "https://api.github.com/users/"+user, true);
    xhttp.onload = function () {
        // if the response is successful call the function showInfo to get the infromatin and show it in the web page
        if (xhttp.status == 200) {
            showInfo(JSON.parse(xhttp.responseText));

        } else {
            // call the function that shows no information if the account is not exist.
            noUserFound(user);
        }
    };
    xhttp.send();
}


function showInfo(user) {
    // This function is to show the user information
    $("#login").text(user.login);
    $(".avatar").html("<img height='500' width='500' src='" + user.avatar_url + "'/>");
    var link = "<a target='_blank' href='" + user.html_url + "'> URL </a>";
    $(".information").html("<label><u><strong>User Information</strong></u></label>" 
        +"<br/><br/><label class='text-danger'><strong>Login Name : </strong></label>" + user.name
        +"<br/><label class='text-danger'><strong>This account was created at : </strong></label>" + user.created_at
        +"<br/><label class='text-danger'> <strong>Login ID : </strong></label>" + user.id
        +"<br/> <label class='text-danger'><strong>GitHub URL : </strong></label>" + link
        +"<br/> <label class='text-danger'><strong>GitHub Public Repos Count : </strong></label>"+ user.public_repos);
    $("#profile").show();
}

function noUserFound(username) {
    // clean the content and display a message to the user if the account is not exist
    $("#login").text("Sorry, The user '" + username + "' is not exist");
    $(".avatar").text('');
    $(".information").html('');
    $("#profile").show();
}
