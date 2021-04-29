(function ($) {

    $.fn.addValidationCheck = function (urlForCheck, resultLabel) {
        let canRedirect = false;
        this.submit(function (e) {
            if (canRedirect) return;
            e.preventDefault();

            let data = $(this).serializeArray();
            console.log(data);
            console.log(urlForCheck);

            $.ajax({
                url: urlForCheck,
                method: 'GET',
                cache: false,
                type: "text/json",
                data: data,

                success: function (res, textStatus, xhr) {
                    console.log(res);
                    console.log(xhr.status);
                    if (xhr.status === 200) {
                        const result = JSON.parse(JSON.stringify(res));
                        console.log(result);
                        if (!result.status) {
                            let resultText = "";
                            for (let i = 0; i < result.errors.length; i++) {
                                resultText += "<p>" + result.errors[i].name + ": " + result.errors[i].message + "</p>";
                            }
                            $(resultLabel).html(resultText);
                        } else {
                            canRedirect = true;
                            $(this).click();
                        }
                    }
                },
                error: function (result) {
                    alert('Error : Failed to reach API Url or check your connection');
                }
            })
                .always(function () {

                })
                .then(function (evt) {

                });
        });
    }

}(jQuery));