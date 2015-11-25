function showError(error, title) {
    BootstrapDialog.alert({
        type: BootstrapDialog.TYPE_DANGER,
        title: title || "ERROR!",
        message: error
    });
}

var verify = (function () {
    function expect(result) {
        return {
            toEqual: function (target) {
                if (JSON.stringify(result) === JSON.stringify(target)) {
                    BootstrapDialog.alert({
                        type: BootstrapDialog.TYPE_SUCCESS,
                        title: "Success",
                        message: "Well done!"
                    });
                } else {
                    showError(
                        JSON.stringify(result) + " did not equal " + JSON.stringify(target),
                        "Wrong result"
                    );
                }
            }
        };
    }

    var verifiers = {
        "1": function (result) {
            expect(result).toEqual([
                "Twiter User 1",
                "Twiter User 2",
                "Twiter User 3",
                "Twiter User 4"
            ]);
        }
    };

    return function (exerciseId, result) {
        var verifier = verifiers[exerciseId];
        if (!verifier) {
            throw Error("Verifier for the exercise can not be found");
        }

        verifier(result);
    };
})();

function renderTitleAndCode(exerciseId, title, codeTextArea, code) {
    $(".title").html(exerciseId + ": " + title);
    codeTextArea.innerHTML = code;
}
$(function () {
    var hashMatch = location.hash.match(/#(.*)/i);
    var exerciseId = hashMatch && hashMatch[1] && hashMatch[1] !== "" ?
        hashMatch[1] : "1";

    var codeTextArea = document.querySelector('textarea.code');

    var title = $("#exercise-" + exerciseId + "-title").html();
    var code = $("#exercise-" + exerciseId + "-code").html();

    if (!code || !title) {
        showError("Exercise does not exist");
        return;
    }
    renderTitleAndCode(exerciseId, title, codeTextArea, code);

    var codeMirror = CodeMirror.fromTextArea(codeTextArea, {
        lineNumbers: true
    });

    $(".go").on("click", function () {
        codeMirror.save();

        try {
            var result = eval($(codeTextArea).val());

            verify(exerciseId, result);
        } catch (e) {
            showError(e.message);
        }
    });
});