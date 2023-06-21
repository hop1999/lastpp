async function getOneUser(id) {
    let url = "/api/users/" + id;
    let response = await fetch(url);
    return await response.json();
}

async function openAndFillInTheModal(form, modal, id) {
    modal.show();
    let user = await getOneUser(id);
    form.id.value = user.id;
    form.firstName.value = user.firstName;
    form.lastname.value = user.lastname;
    form.age.value = user.age;
    form.email.value = user.email;
    form.roles.value = user.roles;
}