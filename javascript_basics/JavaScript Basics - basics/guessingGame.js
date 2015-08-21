// Global variables
var numberOfTries = 10;

var answer = 42;

var guesses = [];

var guessCorrect = false;

function isLimitReached() {
    return guesses.length === numberOfTries;
}

function respondTo(guess) {
    guesses.push(guess);

    // !guess will prevent undefined, null and "", but we must allow zero
    if (!guess && guess !== 0) {
        throw new Error("You must enter something! Nothing is not an answer!");
    }
    
    // We check if the answer is a number and give a hint if it isn't.
    var castedToNumber = Number(guess);
    if (isNaN(castedToNumber)) {
        throw new Error("The answer is a number.");
    }

    // Check if the answer is correct and give a hint what was the closest guess.
    if (castedToNumber !== answer) {
        guesses.push(castedToNumber);
        return "Your closest guess so far was: " + closest();
    }

    guessCorrect = true;
    return "Good guess! " + 4 + 2 + " is the answer to everything!";
}

function closest() {
    var curr = guesses[0];
    var diff = Math.abs(answer - curr);

    for (var i = 0; i < guesses.length; i++) {
        var guessValue = guesses[i];

        // Check if the typeof item is a number, and skip if it isn't.
        if (typeof guessValue !== "number") {
            continue;
        }

        var newdiff = Math.abs(answer - guessValue);
        if (newdiff < diff) {
            diff = newdiff;
            curr = guessValue;
        }
    }

    return curr;
}

function game() {
    do {
        var guess = prompt("What is the answer to everything?", "");

        try {
            alert(respondTo(guess));
        } catch (e) {
            alert(e.message);
        }

        if (isLimitReached()) {
            alert("You reached your limit of 5 guesses and cannot guess anymore.");
            break;
        }

    } while (!guessCorrect);    
}

game();