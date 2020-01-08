/**
 * Making table of all persons.
 */
function getPersonByPhone(ev) {
    ev.preventDefault();
    var phoneAPI = "/course-assignment-2/api/person/phone/" + $(".container input").val();
    console.log(phoneAPI);
    fetch(phoneAPI)
            .then(res => res.json())
            .then(data => {
                console.log(data);
                $("#content").html("personID: " + data.personID + "<br>Email: "+ data.email + "<br>Name: " + data.firstName +" " +data.lastName);
            });
}

/**
 * adds Modal overlay into body of index.html
 */
function insertModal() {
    $.get("modalPhoneNumber.html", {}, function (results) {
        $("#content").append(results);
    });
}

//PersonByPhoneNumber button eventlistener and other DOM manipulations
insertModal();


