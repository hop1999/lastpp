"use strict";
//все юзеры
// showCurrUser()
function showCurrUser() {
    $.get(`/api/auth/`, function (data) {

        let userTbody =
            "<tr><td>" + data.id + "</td>" +
            "<td>" + data.firstName + "</td>" +
            "<td>" + data.lastname + "</td>" +
            "<td>" + data.age + "</td>" +
            "<td>" + data.email + "</td>" +
            "<td>" + data.role + "</td></tr>";
        $("#test_user1").html(userTbody);
    })
}

function currEmail() {
    $.get(`/api/auth/`, function (data) {
        let authUserEmail = data.email;
        $("#currentUserEmail").html(authUserEmail);
    })
}

function currRoles() {
    $.get(`/api/auth/`, function (data) {

        let authUserRoles = data.role;
        $("#currentUserRoles").html(authUserRoles);
    })
}


$(document).ready(function () {
    showCurrUser();
    currEmail();
    currRoles();
})

