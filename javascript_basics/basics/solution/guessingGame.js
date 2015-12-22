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

        for (var i = 0; i < guesses.length; i++) {
            var guessValue = guesses[i];

            // Check if the type of the item is a number, and skip if it isn't.
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
        // !guess will prevent undefined, null or "", but we must allow zero
        if (!guess && guess !== 0) {
            throw new Error("You must enter something! Nothing is not an answer!");
        }
    }

    function CastToNumberOrThrow(guess) {
        var guessAsNumber = Number(guess);
        if (isNaN(guessAsNumber)) {
            throw new Error("The answer is a number.");
        }

        return guessAsNumber;
    }

    function IsGuessCorrect(guessAsNumber) {
        return guessAsNumber === answer;
    }

    function IsNumber(guessValue) {
        return typeof guessValue === "number";
    }

    function game() {
        do {
            var guess = prompt("What is the answer to everything?", "");

            try {
                alert(respondTo(guess));
            } catch (e) {
                // Notify user of error
                alert(e.message);
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