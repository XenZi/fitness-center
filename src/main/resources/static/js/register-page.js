const btnRegisterUser = document.querySelector(".btn-register-user");
const btnRegisterTrainer = document.querySelector(".btn-register-trainer");
const userRegistration = document.querySelector(".user-registration");
const trainerRegistration = document.querySelector(".trainer-registration");
const timezoneInput = document.querySelector('#timezone');
timezoneInput.value = Intl.DateTimeFormat().resolvedOptions().timeZone;
btnRegisterTrainer.addEventListener("click", () => {
    userRegistration.classList.add("d-none");
    btnRegisterUser.classList.remove("btn-active");
    trainerRegistration.classList.remove("d-none");
    btnRegisterTrainer.classList.add("btn-active");
});

btnRegisterUser.addEventListener("click", () => {
    trainerRegistration.classList.add("d-none");
    userRegistration.classList.remove("d-none");
    btnRegisterTrainer.classList.remove("btn-active");
    btnRegisterUser.classList.add("btn-active");
});
