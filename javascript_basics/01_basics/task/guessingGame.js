var guessingGame = function () {
    var numberOfTries = 10;

    var answer = 42;

    var guesses = [];

    var guessCorrect = false;

    function isLimitReached() {
        return guesses.length === numberOfTries;
    }

    function respondTo(guess) {
        guesses.push(guess);

        EnsureGuessNotNullOrEmpty(guess);

        // We check if the answer is a number and give a hint if it isn't.
        var guessAsNumber = CastToNumberOrThrow(guess);
        guesses[guesses.length - 1] = guessAsNumber;

        // Check if the answer is correct and if not give a hint what was the closest guess.
        if (!IsGuessCorrect(guessAsNumber)) {
            return "Your closest guess so far was: " + closest();
        }

        guessCorrect = true;
        return "Good guess! " + 4 + 2 + " is the answer to everything!";
    }

    function closest() {
        var curr = guesses[0];
        var diff = Math.abs(answer - curr);
                
        if (isNaN(diff)) {
            diff = answer;
        }

        // TODO: Fix this for loop.
        for (var i = 0; i <= guesses.length; i++) {
            var guessValue = guesses[i];

            // Check if the type of item is a number, and skip if it isn't.
            if (!IsNumber(guessValue)) {
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

    function EnsureGuessNotNullOrEmpty(guess) {
        // TODO: Check if guess is undefined, null or an empty string and throw an exception if it is.
    }

    function CastToNumberOrThrow(guess) {
        // TODO: Return guess as number. Try to cast or throw an exception if the value is not a null.
        var guessAsNumber = guess;

        return guessAsNumber;
    }

    function IsGuessCorrect(guessAsNumber) {
        // TODO: Check if the guess is correct.
        return true;
    }

    function IsNumber(guessValue) {
        // TODO: Check if the type of the guessValue is a number
        return true;
    }

    function game() {
        do {
            var guess = prompt("What is the answer to everything?", "");

            try {
                alert(respondTo(guess));
            } catch (e) {
                // TODO: Handle caught exceptions in a way meaningful for the user.
            }

            if (isLimitReached()) {
                alert("You reached your limit of " + numberOfTries + " guesses and cannot guess any more.");
                break;
            }

        } while (!guessCorrect);
    }

    return { start: game };
}

guessingGame().start();