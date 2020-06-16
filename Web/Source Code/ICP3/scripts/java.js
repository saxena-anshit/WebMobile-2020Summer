/* This function is get the element from the page and change it to Rock, Paper, or Scissor 
    it does Math.random and then the result will be inner to the element and store it in a varable called DefaultChoice, 
    so it can be compared to the other functions
*/

function getChoice(){
    var x = document.getElementById("getresult")
    var DefaultChoice = Math.random();
    if (DefaultChoice < 0.34){
        x.innerText = "Rock";
        DefaultChoice = "Rock";
    }
    else if (DefaultChoice < 0.67){
        x.innerText = "Paper";
        DefaultChoice = "Paper";
    }
    else {
        x.innerText = "Scissor";
        DefaultChoice = "Scissors";
    }
    return DefaultChoice
}

/* This function is to compare the DefaultChoice with the Rock and then the result will be inner to the page */
    function Rock(){
        var x = document.getElementById("note");
        var sys = getChoice(); /* This is to get the DefaultChoice and store it in sys */
        var result;
        if(sys == "Rock"){
            result = "It's a tie!"
        }
        else if (sys == "Paper"){
            result = "You lost! Try again."
        }
        else{
            result = "WOW! You won"
        }
        x.innerText = result
    }

/* Same like the Rock function but will do the Paper */
function Paper(){
    var x = document.getElementById("note");
    var sys = getChoice(); /* This is to get the DefaultChoice and store it in sys */
    var result;
    if(sys == "Rock"){
        result = "WOW! You won"
    }
    else if (sys == "Paper"){
        result = "It's a tie!"
    }
    else{
        result = "You lost! Try again."
    }
    x.innerText = result
}

/* Same like the Rock function but will do the Scissor */
function Scissor(){
    var x = document.getElementById("note");
    var sys = getChoice(); /* This is to get the DefaultChoice and store it in sys */
    var result;
    if(sys == "Rock"){
        result = "You lost! Try again."
    }
    else if (sys == "Paper"){
        result = "WOW! You won"
    }
    else{
        result = "It's a tie!"
    }
    x.innerText = result

}